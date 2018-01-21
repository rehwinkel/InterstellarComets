package com.deerangle.main;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleExplosion;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ParticleMana extends Particle {

	ResourceLocation texture = new ResourceLocation(InterstellarComets.MODID + ":textures/particles/mana.png");
	private Vector3f destination;
	private Vector3f position;
	private Vector3f start;

	public ParticleMana(World worldIn, double startX, double startY, double startZ) {
		this(worldIn, startX, startY, startZ, startX, startY, startZ);
	}

	public ParticleMana(World world, double startX, double startY, double startZ, double destX, double destY, double destZ) {
		this(world, startX, startY, startZ, destX, destY, destZ, 0, 0, 0);
	}

	public ParticleMana(World world, double startX, double startY, double startZ, double destX, double destY, double destZ, double velX, double velY, double velZ) {
		super(world, startX, startY, startZ, 0, 0, 0);

		this.start = new Vector3f((float) startX, (float) startY, (float) startZ);
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
		dir = Vector3f.sub(destination, position, null);
		if (dir.length() > 0) {
			dir.normalise();
		}
		this.motionX = (this.motionX + (double) dir.getX() * 0.2) / 2;
		this.motionY = (this.motionY + (double) dir.getY() * 0.2) / 2;
		this.motionZ = (this.motionZ + (double) dir.getZ() * 0.2) / 2;

		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		
		endLife();
		updateAlpha();
	}

	private void updateAlpha() {
		float deWey = Vector3f.sub(destination, start, null).length();
		if(deWey == 1){
			System.out.println(0);
		}else{
			System.out.println(deWey);
		}
	}

	private void endLife() {
		this.position = new Vector3f((float) posX, (float) posY, (float) posZ);
		Vector3f dir = new Vector3f();
		Vector3f.sub(destination, position, dir);

		if (dir.length() < 0.15) {
			this.setExpired();
		}
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		GlStateManager.enableBlend();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.2F);
		float f4 = 0.1F * this.particleScale;
		float f5 = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
		float f6 = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
		float f7 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);
		int i = this.getBrightnessForRender(partialTicks);
		int j = i >> 16 & 65535;
		int k = i & 65535;
		Vec3d[] avec3d = new Vec3d[] { new Vec3d((double) (-rotationX * f4 - rotationXY * f4), (double) (-rotationZ * f4), (double) (-rotationYZ * f4 - rotationXZ * f4)), new Vec3d((double) (-rotationX * f4 + rotationXY * f4), (double) (rotationZ * f4), (double) (-rotationYZ * f4 + rotationXZ * f4)),
				new Vec3d((double) (rotationX * f4 + rotationXY * f4), (double) (rotationZ * f4), (double) (rotationYZ * f4 + rotationXZ * f4)), new Vec3d((double) (rotationX * f4 - rotationXY * f4), (double) (-rotationZ * f4), (double) (rotationYZ * f4 - rotationXZ * f4)) };

		if (this.particleAngle != 0.0F) {
			float f8 = this.particleAngle + (this.particleAngle - this.prevParticleAngle) * partialTicks;
			float f9 = MathHelper.cos(f8 * 0.5F);
			float f10 = MathHelper.sin(f8 * 0.5F) * (float) cameraViewDir.x;
			float f11 = MathHelper.sin(f8 * 0.5F) * (float) cameraViewDir.y;
			float f12 = MathHelper.sin(f8 * 0.5F) * (float) cameraViewDir.z;
			Vec3d vec3d = new Vec3d((double) f10, (double) f11, (double) f12);

			for (int l = 0; l < 4; ++l) {
				avec3d[l] = vec3d.scale(2.0D * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale((double) (f9 * f9) - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale((double) (2.0F * f9)));
			}
		}

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		buffer.pos((double) (f5 + avec3d[0].x), (double) (f6 + avec3d[0].y), (double) (f7 + avec3d[0].z)).tex((double) 1, (double) 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double) (f5 + avec3d[1].x), (double) (f6 + avec3d[1].y), (double) (f7 + avec3d[1].z)).tex((double) 1, (double) 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double) (f5 + avec3d[2].x), (double) (f6 + avec3d[2].y), (double) (f7 + avec3d[2].z)).tex((double) 0, (double) 0).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double) (f5 + avec3d[3].x), (double) (f6 + avec3d[3].y), (double) (f7 + avec3d[3].z)).tex((double) 0, (double) 1).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
	}
	
	@Override
	public int getFXLayer() {
		return 2;
	}

}
