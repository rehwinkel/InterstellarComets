package com.deerangle.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleExplosion;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleMana extends Particle {

	ResourceLocation texture = new ResourceLocation(InterstellarComets.MODID + ":textures/particles/mana.png");
	
	public ParticleMana(World worldIn, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString());
		this.setParticleTexture(sprite);
		
		this.motionX = 1 * 0.05000000074505806D;
        this.motionY = 1 * 0.05000000074505806D;
        this.motionZ = 1 * 0.05000000074505806D;
        float f = 1;
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;
        this.particleScale = this.rand.nextFloat() * this.rand.nextFloat() * 6.0F + 1.0F;
        this.particleMaxAge = (int)(16.0D / ((double)this.rand.nextFloat() * 0.8D + 0.2D)) + 2;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
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
