package com.deerangle.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.Entity;

public class ModelComet extends ModelBase {

	ModelRenderer block1;
	
	public ModelComet() {
		this.block1 = new ModelRenderer(this, 0, 0);
        this.block1.addBox(-8.0F, 1.0F, -8.0F, 16, 16, 16, 1F);
        this.block1.setRotationPoint(0.0F, 0.0F, 0.0F);
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.block1.render(scale);
	}
	
}
