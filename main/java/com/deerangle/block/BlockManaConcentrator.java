package com.deerangle.block;

import com.deerangle.block.entity.TileEntityManaConcentrator;
import com.deerangle.main.ModTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockManaConcentrator extends Block {

	public BlockManaConcentrator() {
		super(Material.ROCK);
		this.setRegistryName("mana_concentrator");
		this.setUnlocalizedName("mana_concentrator");
		this.setCreativeTab(ModTabs.tab_main);
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1.5F);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityManaConcentrator();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return this.hasTileEntity();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
}
