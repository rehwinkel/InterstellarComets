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
		super(world, startX, startY, startZ, 0, 0, 0);
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString());
		this.setParticleTexture(sprite);
		
        this.destination = new Vector3f((float) destX, (float) destY, (float) destZ);
        float f = 1;
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;
        this.particleScale = 1;
        this.particleMaxAge = 20;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
		//System.out.println("phwijphrw");
		this.position = new Vector3f((float) posX, (float) posY, (float) posZ);
		Vector3f dir = new Vector3f();
		Vector3f.sub(destination, position, dir);
		dir.normalise();
		
		this.motionX = (double) dir.getX();
        this.motionY = (double) dir.getY();
        this.motionZ = (double) dir.getZ();

		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
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
