package com.deerangle.block;

import java.util.ArrayList;

import com.deerangle.block.item.ItemBlockCometicBrick;
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
	public static Block mana_concentrator;
	public static Block mana_concentrator_base;
	public static Block block_comet;
	public static Block cometic_brick;
	public static Block cometic_slab;
	public static Block cometic_fireTrap;
	
	private static final ModBlocks instance = new ModBlocks();
	
	public static void load(){
		pedestal = new BlockPedestal();
		block_comet = new BlockComet();
		mana_concentrator = new BlockManaConcentrator();
		mana_concentrator_base = new BlockManaConcentratorBase();
		cometic_brick = new BlockCometicBrick();
		cometic_slab = new BlockCometicSlab();
		cometic_fireTrap = new BlockCometicFireTrap();
		
		MinecraftForge.EVENT_BUS.register(instance);
	}
	
	@SubscribeEvent
	public void registerBlock(RegistryEvent.Register<Block> event){
		IForgeRegistry<Block> registry = event.getRegistry();

		register(registry, pedestal);
		register(registry, block_comet);
		register(registry, mana_concentrator, null);
		register(registry, mana_concentrator_base);
		register(registry, cometic_brick, new ItemBlockCometicBrick(cometic_brick));
		register(registry, cometic_slab);
		register(registry, cometic_fireTrap);

	}

	private void register(IForgeRegistry<Block> registry, Block block) {
		ItemBlock ib = new ItemBlock(block);
		ib.setRegistryName(block.getRegistryName());
		register(registry, block, ib);
	}
	
	private void register(IForgeRegistry<Block> registry, Block block, ItemBlock item) {
		if(item != null){
			ITEM_BLOCKS.add(item);
		}
		registry.register(block);
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
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(mana_concentrator_base), 0, new ModelResourceLocation(InterstellarComets.MODID + ":mana_concentrator_full", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cometic_brick), 0, new ModelResourceLocation(InterstellarComets.MODID + ":cometic_brick_stone", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cometic_brick), 1, new ModelResourceLocation(InterstellarComets.MODID + ":cometic_brick_brick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cometic_brick), 2, new ModelResourceLocation(InterstellarComets.MODID + ":cometic_brick_cobble", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cometic_brick), 3, new ModelResourceLocation(InterstellarComets.MODID + ":cometic_brick_cracked", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cometic_brick), 4, new ModelResourceLocation(InterstellarComets.MODID + ":cometic_brick_chiseled", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cometic_fireTrap), 0, new ModelResourceLocation(InterstellarComets.MODID + ":cometic_fireTrap", "inventory"));

	}

}
