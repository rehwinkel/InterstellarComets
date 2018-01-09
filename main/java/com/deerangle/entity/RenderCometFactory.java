package com.deerangle.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderItemFrame;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCometFactory implements IRenderFactory<EntityComet> {

	@Override
	public Render<? super EntityComet> createRenderFor(RenderManager manager) {
		return new RenderComet(manager);
	}

}
