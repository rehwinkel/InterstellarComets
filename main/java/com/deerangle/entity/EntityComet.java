package com.deerangle.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityComet extends Entity {

	public EntityComet(World worldIn) {
		super(worldIn);
		this.setSize(1, 1);
	}

	@Override
	protected void entityInit() {
		System.out.println("wüoghjweg");
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}
	
}
