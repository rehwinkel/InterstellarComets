package com.deerangle.block;

import java.util.List;

import com.deerangle.block.entity.TileEntityManaConcentrator;
import com.deerangle.main.ModTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockManaConcentrator extends Block {

	public BlockManaConcentrator() {
		super(Material.ROCK);
		this.setRegistryName("mana_concentrator");
		this.setUnlocalizedName("mana_concentrator");
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1.5F);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if(!worldIn.isRemote){
			TileEntityManaConcentrator te = (TileEntityManaConcentrator) worldIn.getTileEntity(pos);
			te.onDestory();
			worldIn.setBlockToAir(pos.add(0, -1, 0));
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		super.dropBlockAsItemWithChance(worldIn, pos.add(0, -1, 0), state, chance, fortune);
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(ModBlocks.mana_concentrator_base));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 0.375, 0.875);
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
