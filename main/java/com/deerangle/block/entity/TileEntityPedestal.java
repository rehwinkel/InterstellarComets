package com.deerangle.block.entity;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import com.deerangle.main.InterstellarComets;
import com.deerangle.main.ParticleMana;
import com.deerangle.network.PacketRequestUpdatePedestal;
import com.deerangle.network.PacketUpdatePedestal;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityPedestal extends TileEntity implements ITickable, ICapabilityProvider {

	public ItemStackHandler inventory = new ItemStackHandler() {
		@Override
		protected void onContentsChanged(int slot) {
			if (!world.isRemote) {
				InterstellarComets.wrapper.sendToAll(new PacketUpdatePedestal(TileEntityPedestal.this));
			}
		}
	};
	public long placeTime;

	@Override
	public void onLoad() {
		if (placeTime == 0)
			placeTime = world.getTotalWorldTime();

		if (world.isRemote) {
			InterstellarComets.wrapper.sendToServer(new PacketRequestUpdatePedestal(this));
		}
	}

	@Override
	public void update() {
		//world.spawnParticle(EnumParticleTypes.CLOUD, getPos().getX(), getPos().getY(), getPos().getZ(), 0, 1, 0);
		Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleMana(world, getPos().getX(), getPos().getY(), getPos().getZ()));
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}

		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (hasCapability(capability, facing)) {
			return (T) inventory;
		}

		return super.getCapability(capability, facing);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		placeTime = compound.getLong("placeTime");
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		compound.setLong("placeTime", placeTime);
		return super.writeToNBT(compound);
	}

}
