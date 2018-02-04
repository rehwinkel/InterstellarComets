package com.deerangle.block;

import com.deerangle.block.entity.TileEntityPedestal;
import com.deerangle.item.ModItems;
import com.deerangle.main.InterstellarComets;
import com.deerangle.main.InterstellarCometsUtil;
import com.deerangle.main.ModTabs;
import com.deerangle.network.PacketOccupyPedastal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class BlockPedestal extends Block {

	public BlockPedestal() {
		super(Material.ROCK);
		this.setRegistryName("pedestal");
		this.setUnlocalizedName("pedestal");
		this.setCreativeTab(ModTabs.tab_main);
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1.5F);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 1 - 0.0625D, 0.5625D, 1 - 0.0625D);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return this.isOpaqueCube(state);
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return this.isFullCube(state);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote){
			TileEntityPedestal tile = (TileEntityPedestal) worldIn.getTileEntity(pos);
			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
			if (!itemHandler.getStackInSlot(0).isEmpty()) {
				if(player.getHeldItem(hand).isEmpty()){
					player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
				}
			} else {
				if(true/*player.getHeldItem(hand).getItem() == Item.getItemFromBlock(ModBlocks.block_comet)*/){
					itemHandler.insertItem(0, getStackWithCount(player.getHeldItem(hand), 1), false);
					player.setHeldItem(hand, getStackWithCount(player.getHeldItem(hand), player.getHeldItem(hand).getCount() - 1));
				}
			}
			InterstellarComets.wrapper.sendToAll(new PacketOccupyPedastal(tile, tile.occupier));
			tile.markDirty();
		}
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityPedestal tile = (TileEntityPedestal) worldIn.getTileEntity(pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		InterstellarCometsUtil.dropAllItems(worldIn, itemHandler, pos);
		super.breakBlock(worldIn, pos, state);
	}
	
	private ItemStack getStackWithCount(ItemStack stack, int count){
		ItemStack stack2 = stack.copy();
		stack2.setCount(count);
		if (count > 0){
			return stack2;
		}else{
			return ItemStack.EMPTY;
		}
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return this.hasTileEntity();
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPedestal();
	}
	
}
