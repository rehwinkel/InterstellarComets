package com.deerangle.network;

import com.deerangle.block.entity.TileEntityPedestal;
import com.deerangle.block.entity.render.TileEntityPedestalRenderer;
import com.deerangle.entity.EntityComet;
import com.deerangle.entity.RenderComet;
import com.deerangle.particle.ParticleMana;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityCow;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends ServerProxy {
	
	@Override
	public void preinit(FMLPreInitializationEvent event) {
		super.preinit(event);
		RenderingRegistry.registerEntityRenderingHandler(EntityComet.class, RenderComet::new);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new TileEntityPedestalRenderer());
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
	
	@Override
	public void postinit(FMLPostInitializationEvent event) {
		super.postinit(event);
	}

	public void spawnParticle(double startX, double startY, double startZ, double destX, double destY, double destZ) {
		Minecraft mc = Minecraft.getMinecraft();
		ParticleMana particle = new ParticleMana(mc.world, startX, startY, startZ, destX, destY, destZ, 0, 1.2, 0);
		mc.effectRenderer.addEffect(particle);
	}
	
}
