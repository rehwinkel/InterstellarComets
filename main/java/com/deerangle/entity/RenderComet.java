package com.deerangle.entity;

import com.deerangle.main.InterstellarComets;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderComet extends Render<EntityComet> {

	public RenderComet(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityComet entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityComet entity) {
		return new ResourceLocation(InterstellarComets.MODID + ":textures/entity/comet/comet.png");
	}

}
