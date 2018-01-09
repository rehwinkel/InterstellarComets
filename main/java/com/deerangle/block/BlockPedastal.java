package com.deerangle.block;

import com.deerangle.main.InterstellarComets;
import com.deerangle.main.ModTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
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
					player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
				} else {
					ItemStack stack = player.getHeldItem(hand).copy();
					stack.setCount(1);
					itemHandler.insertItem(0, stack, false);
					
					ItemStack stack2 = player.getHeldItem(hand).copy();
					stack2.setCount(stack2.getCount() - 1);
					player.setHeldItem(hand, stack2);
				}
				tile.markDirty();
			}else{
				//open gui
				System.out.println("OPEN GUI!");
			}
		}
		return true;
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
