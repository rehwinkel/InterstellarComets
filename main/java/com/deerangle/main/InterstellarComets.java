package com.deerangle.main;

import com.deerangle.block.ModBlocks;
import com.deerangle.item.ModItems;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = InterstellarComets.NAME, modid = InterstellarComets.MODID, version = InterstellarComets.VERSION)
public class InterstellarComets {
	public static final String NAME = "Interstellar Comets";
	public static final String MODID = "interstellarcomets";
	public static final String VERSION = "1.0.0";

	@Instance
	public static InterstellarComets instance;
	
	@SidedProxy(clientSide = "com.deerangle.main.ClientProxy", serverSide = "com.deerangle.main.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		ModItems.load();
		ModBlocks.load();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		
	}
}
