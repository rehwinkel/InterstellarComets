package com.deerangle.entity;

import com.deerangle.main.InterstellarComets;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderComet extends Render<EntityComet> {

	private ModelComet model;
	
	public RenderComet(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityComet entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		this.model.render(entity, 0, 0, 0, entityYaw, 0, 1.0F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityComet entity) {
		return new ResourceLocation(InterstellarComets.MODID + ":textures/entity/comet/comet.png");
	}

}
