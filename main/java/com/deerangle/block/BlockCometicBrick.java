package com.deerangle.block;

import com.deerangle.main.ModTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

public class BlockCometicBrick extends Block {

	private String[] types = new String[] { "" };
	public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockCometicBrick.EnumType.class);

	public BlockCometicBrick() {
		super(Material.ROCK);
		this.setHardness(2.0F);
		this.setCreativeTab(ModTabs.tab_main);
		this.setHarvestLevel("pickaxe", 1);
		this.setRegistryName("cometic_brick");
		this.setUnlocalizedName("cometic_brick");
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumType.STONE));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (int i = 0; i < types.length; i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumType e = (EnumType) state.getValue(TYPE);
		return e.getId();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, EnumType.fromId(meta));
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	public enum EnumType implements IStringSerializable {
		STONE(0, "stone"),
		BRICK(1, "brick"),
		COBBLE(2, "cobble"),
		CRACKED(3, "cracked"),
		CHISELED(4, "chiseled");

		private String name;
		private int id;

		private EnumType(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public static EnumType fromId(int meta) {
			for(EnumType e : values()){
				if(e.getId() == meta){
					return e;
				}
			}
			return STONE;
		}

		@Override
		public String getName() {
			return name;
		}

		public int getId() {
			return id;
		}

	}

}
