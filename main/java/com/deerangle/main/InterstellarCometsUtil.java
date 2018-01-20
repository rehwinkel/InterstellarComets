package com.deerangle.main;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class InterstellarCometsUtil {

	public static void dropAllItems(World world, IItemHandler itemHandler, BlockPos pos) {
		for(int i = 0; i < itemHandler.getSlots(); i++){
			ItemStack stack = itemHandler.getStackInSlot(i);
			if(!stack.isEmpty()){
				EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5, stack);
				world.spawnEntity(item);
			}
		}
	}

}
