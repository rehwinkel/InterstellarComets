package com.deerangle.main;

import com.deerangle.item.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModTabs {

	public static CreativeTabs tab_main = new CreativeTabs(InterstellarComets.MODID + "_main") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.shit);
		}
	};
	
}
