package com.deerangle.main;

import com.deerangle.block.ModBlocks;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {

	public static void load() {
		GameRegistry.addSmelting(new ItemStack(ModBlocks.block_comet, 0), new ItemStack(ModBlocks.cometic_brick, 2), 0);
	}
}
