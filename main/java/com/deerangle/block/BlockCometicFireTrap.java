package com.deerangle.block;

import com.deerangle.main.ModTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCometicFireTrap extends Block {

	public BlockCometicFireTrap() {
		super(Material.ROCK);
		this.setRegistryName("cometic_fireTrap");
		this.setUnlocalizedName("cometic_fireTrap");
		this.setCreativeTab(ModTabs.tab_main);
		this.setHarvestLevel("pickaxe", 1);
		this.setHardness(2F);
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entity) {
		entity.setFire(10);
		super.onEntityWalk(worldIn, pos, entity);
	}
}
