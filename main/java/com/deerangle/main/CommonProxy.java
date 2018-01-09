package com.deerangle.main;

import com.deerangle.block.ModBlocks;
import com.deerangle.entity.ModEntities;
import com.deerangle.item.ModItems;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preinit(FMLPreInitializationEvent event) {
		ModItems.load();
		ModBlocks.load();
		ModEntities.load();
	}

	public void init(FMLInitializationEvent event) {
		
	}

	public void postinit(FMLPostInitializationEvent event) {
		
	}
	
}
