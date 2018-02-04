package com.deerangle.block.entity;

import com.deerangle.main.InterstellarComets; 
import com.deerangle.network.PacketRequestUpdatePedestal;
import com.deerangle.network.PacketSpawnCustomParticle;
import com.deerangle.network.PacketUpdatePedestal;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
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
	public boolean enabled = false;
	public BlockPos target;

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
		if(this.enabled){
			double startX = getPos().getX() + 0.5 + (world.rand.nextDouble() - 0.5) * 0.4;
			double startY = getPos().getY() + 0.7;
			double startZ = getPos().getZ() + 0.5 + (world.rand.nextDouble() - 0.5) * 0.4;
			double destX = target.getX() + 0.5;
			double destY = target.getY() + 0.3;
			double destZ = target.getZ() + 0.5;
			//ParticleMana particle = new ParticleMana(world, startX, startY, startZ, destX, destY, destZ, 0, 1.2, 0);
			//Minecraft.getMinecraft().effectRenderer.addEffect(particle);
			InterstellarComets.wrapper.sendToAll(new PacketSpawnCustomParticle(startX, startY, startZ, destX, destY, destZ));
		}
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
