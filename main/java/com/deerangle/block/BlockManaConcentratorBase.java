package com.deerangle.block;

import com.deerangle.block.entity.TileEntityManaConcentrator;
import com.deerangle.main.ModTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockManaConcentratorBase extends Block {

	public BlockManaConcentratorBase() {
		super(Material.ROCK);
		this.setRegistryName("mana_concentrator_base");
		this.setUnlocalizedName("mana_concentrator_base");
		this.setCreativeTab(ModTabs.tab_main);
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1.5F);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		worldIn.setBlockState(pos.add(0, 1, 0), ModBlocks.mana_concentrator.getDefaultState());
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityManaConcentrator te = (TileEntityManaConcentrator) worldIn.getTileEntity(pos.add(0, 1, 0));
		te.onDestory();
		worldIn.setBlockToAir(pos.add(0, 1, 0));
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 1.0, 0.875);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return this.isOpaqueCube(state);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return this.isFullBlock(state);
	}

}
