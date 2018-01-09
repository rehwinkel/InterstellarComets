package com.deerangle.block;

import com.deerangle.main.ModTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockComet extends Block {

	public BlockComet() {
		super(Material.ROCK);
		this.setRegistryName("block_comet");
		this.setUnlocalizedName("block_comet");
		this.setCreativeTab(ModTabs.tab_main);
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(2F);
	}

}
