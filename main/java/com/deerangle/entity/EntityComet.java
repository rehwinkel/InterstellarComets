package com.deerangle.entity;

import net.minecraft.client.renderer.entity.RenderEntityItem;
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
		this.setSize(0.8F, 0.8F);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	protected void entityInit() {
		System.out.println("wüoghjweg");
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		
	}
	
}
