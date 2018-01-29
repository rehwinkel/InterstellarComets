package com.deerangle.item;

import com.deerangle.main.InterstellarComets;
import com.deerangle.main.ModTabs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemConcentratedManaCrystal extends Item {

	public ItemConcentratedManaCrystal() {
		super();
		this.setUnlocalizedName("concentrated_mana_crystal");
		this.setRegistryName("concentrated_mana_crystal");
		this.setCreativeTab(ModTabs.tab_main);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
