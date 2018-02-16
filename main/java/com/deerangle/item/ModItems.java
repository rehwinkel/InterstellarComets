package com.deerangle.item;

import com.deerangle.main.InterstellarComets;
import com.deerangle.main.ModTabs;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
	public static Item debugger;
	public static Item concentrated_mana_crystal;
	
	private static final ModItems instance = new ModItems();
	
	public static void load(){
		debugger = new ItemDebugger();
		concentrated_mana_crystal = new ItemConcentratedManaCrystal();
		
		MinecraftForge.EVENT_BUS.register(instance);
	}
	
	@SubscribeEvent
	public void registerItem(RegistryEvent.Register<Item> event){
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(debugger);
		registry.register(concentrated_mana_crystal);
	}
	
	@SubscribeEvent
	public void registerModel(ModelRegistryEvent event){
		ModelLoader.setCustomModelResourceLocation(debugger, 0, new ModelResourceLocation(InterstellarComets.MODID + ":debugger", "inventory"));
		ModelLoader.setCustomModelResourceLocation(concentrated_mana_crystal, 0, new ModelResourceLocation(InterstellarComets.MODID + ":concentrated_mana_crystal", "inventory"));
	}
	
}
