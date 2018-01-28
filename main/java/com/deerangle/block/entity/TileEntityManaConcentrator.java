package com.deerangle.block.entity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityManaConcentrator extends TileEntity {

	private ItemStackHandler inventory = new ItemStackHandler(2);

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
	
}
