package com.deerangle.block.entity;

import com.deerangle.block.ModBlocks;
import com.deerangle.main.InterstellarComets;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityManaConcentrator extends TileEntity implements ITickable, ICapabilityProvider {

	private ItemStackHandler inventory = new ItemStackHandler(2);
	
	public boolean isActive = false;

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(hasCapability(capability, facing)){
			return (T) inventory;
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public void update() {
		BlockPos[] poses = new BlockPos[] { this.pos.add(0, -1, 2), this.pos.add(0, -1, -2), this.pos.add(2, -1, 0), this.pos.add(-2, -1, 0) };
		
		for(BlockPos p : poses){
			TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(p);
			if(te != null && te.occupier == null){
				te.occupier = this;
				changeOccupier(te);
			}
		}
		
		boolean pass = true;
		for(BlockPos p : poses){
			if(world.getBlockState(p).getBlock() != ModBlocks.pedestal){
				pass = false;
			}else{
				TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(p);
				if(te.occupier != this){
					pass = false;
				}else{
					IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
					if(!itemHandler.getStackInSlot(0).getItem().equals(Item.getItemFromBlock(ModBlocks.block_comet))){
						pass = false;
					}
				}
			}
		}
		isActive = pass;
	}
	
	public void onDestory() {
		this.isActive = false;
		BlockPos[] poses = new BlockPos[] { this.pos.add(0, -1, 2), this.pos.add(0, -1, -2), this.pos.add(2, -1, 0), this.pos.add(-2, -1, 0) };
		
		for(BlockPos p : poses){
			TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(p);
			if(te != null && te.occupier == this){
				te.occupier = null;
				changeOccupier(te);
			}
		}
	}

	private void changeOccupier(TileEntityPedestal te) {
		InterstellarComets.wrapper.sendToAll(new PacketOccupyPedastal(te, te.occupier));
	}
	
}
