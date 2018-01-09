package com.deerangle.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityComet extends Entity {

	public EntityComet(World worldIn, int x, int y, int z) {
		this(worldIn);
		this.setPosition(x, y, z);
	}

	public EntityComet(World worldIn) {
		super(worldIn);
		this.setSize(1, 1);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		//System.out.println(getPosition());
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		System.out.println(getPosition());
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
