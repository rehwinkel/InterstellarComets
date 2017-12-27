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
	
	public static Item shit;
	
	private static final ModItems instance = new ModItems();
	
	public static void load(){
		shit = new Item().setRegistryName("shit").setUnlocalizedName("shit").setCreativeTab(ModTabs.tab_main);
		
		MinecraftForge.EVENT_BUS.register(instance);
	}
	
	@SubscribeEvent
	public void registerItem(RegistryEvent.Register<Item> event){
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(shit);
	}
	
	@SubscribeEvent
	public void registerModel(ModelRegistryEvent event){
		ModelLoader.setCustomModelResourceLocation(shit, 0, new ModelResourceLocation(InterstellarComets.MODID + ":shit", "inventory"));
	}
	
}
