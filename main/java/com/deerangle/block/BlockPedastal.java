package com.deerangle.block;

import com.deerangle.item.ModItems;
import com.deerangle.main.InterstellarComets;
import com.deerangle.main.ModTabs;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class BlockPedastal extends Block {

	public BlockPedastal() {
		super(Material.ROCK);
		this.setRegistryName("pedastal");
		this.setUnlocalizedName("pedastal");
		this.setCreativeTab(ModTabs.tab_main);
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1.5F);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote){
			System.out.println("clicked!");
			TileEntityPedastal tile = (TileEntityPedastal) worldIn.getTileEntity(pos);
			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
			if (!player.isSneaking()) {
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
				tile.markDirty();
			}else{
				//open gui
				System.out.println("OPEN GUI!");
			}
		}
		return true;
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
		return new TileEntityPedastal();
	}
	
}
