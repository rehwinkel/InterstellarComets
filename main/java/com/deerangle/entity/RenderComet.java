package com.deerangle.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderComet extends Render<EntityComet> {

	protected RenderComet(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityComet entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		System.out.println("Am i rendering?");
	}
	
	@Override
	public RenderManager getRenderManager() {
		return super.getRenderManager();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityComet entity) {
		return null;
	}

}
