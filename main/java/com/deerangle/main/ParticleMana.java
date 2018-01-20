package com.deerangle.main;

import org.lwjgl.util.vector.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleExplosion;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleMana extends Particle {

	ResourceLocation texture = new ResourceLocation(InterstellarComets.MODID + ":textures/particles/mana.png");
	private Vector3f destination;
	private Vector3f position;
	
	public ParticleMana(World worldIn, double startX, double startY, double startZ) {
		this(worldIn, startX, startY, startZ, startX, startY, startZ);
	}
	
	public ParticleMana(World world, double startX, double startY, double startZ, double destX, double destY, double destZ) {
		this(world, startX, startY, startZ, destX, destY, destZ, 0, 0, 0);
	}

	public ParticleMana(World world, double startX, double startY, double startZ, double destX, double destY, double destZ, double velX, double velY, double velZ) {
		super(world, startX, startY, startZ, 0, 0, 0);
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString());
		this.setParticleTexture(sprite);
		
        this.destination = new Vector3f((float) destX, (float) destY, (float) destZ);
        
        this.motionX = velX;
        this.motionY = velY;
        this.motionZ = velZ;
        
        float f = 1;
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;
        this.particleScale = 1;
        this.particleMaxAge = 2000;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.position = new Vector3f((float) posX, (float) posY, (float) posZ);
		
		Vector3f dir = new Vector3f();
		Vector3f.sub(destination, position, dir);
		if(dir.length() > 0){
			dir.normalise();
		}
		this.motionX = (this.motionX + (double) dir.getX() * 0.2)/2;
        this.motionY = (this.motionY +(double) dir.getY() * 0.2)/2;
        this.motionZ = (this.motionZ +(double) dir.getZ() * 0.2)/2;

		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;

		endLife();
	}
	
	private void endLife() {
		this.position = new Vector3f((float) posX, (float) posY, (float) posZ);
		Vector3f dir = new Vector3f();
		Vector3f.sub(destination, position, dir);
		
		if(dir.length() < 0.15){
			this.setExpired();
		}
	}

	@Override
	public int getFXLayer() {
		return 1;
	}
	
	@Override
	public void setParticleTexture(TextureAtlasSprite texture) {
		this.particleTexture = texture;
	}

}
