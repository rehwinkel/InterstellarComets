package com.deerangle.block;

import java.util.ArrayList; 

import com.deerangle.item.ModItems;
import com.deerangle.main.InterstellarComets;
import com.deerangle.main.ModTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	
	public static Block pedestal;
	public static Block block_comet;
	
	private static final ModBlocks instance = new ModBlocks();
	
	public static void load(){
		pedestal = new BlockPedestal();
		block_comet = new BlockComet();
		
		MinecraftForge.EVENT_BUS.register(instance);
	}
	
	@SubscribeEvent
	public void registerBlock(RegistryEvent.Register<Block> event){
		IForgeRegistry<Block> registry = event.getRegistry();

		register(registry, pedestal);
		register(registry, block_comet);
	}
	
	private void register(IForgeRegistry<Block> registry, Block block) {
		register(registry, block, new ItemBlock(block));
	}

	private void register(IForgeRegistry<Block> registry, Block block, ItemBlock shit) {
		registry.register(block);
		ItemBlock ib = new ItemBlock(block);
		ib.setRegistryName(block.getRegistryName());
		ITEM_BLOCKS.add(ib);
	}

	private ArrayList<ItemBlock> ITEM_BLOCKS = new ArrayList<ItemBlock>();
	
	@SubscribeEvent
	public void registerItem(RegistryEvent.Register<Item> event){
		IForgeRegistry<Item> registry = event.getRegistry();
		
		for(ItemBlock ib : ITEM_BLOCKS){
			registry.register(ib);
		}
	}
	
	@SubscribeEvent
	public void registerModel(ModelRegistryEvent event){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(pedestal), 0, new ModelResourceLocation(InterstellarComets.MODID + ":pedestal", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block_comet), 0, new ModelResourceLocation(InterstellarComets.MODID + ":block_comet", "inventory"));
	}

}
