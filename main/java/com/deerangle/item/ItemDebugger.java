package com.deerangle.item;

import java.util.ArrayList;

import com.deerangle.main.ModTabs;
import com.deerangle.world.BlockArea;
import com.deerangle.world.DungeonBlockArea;
import com.sun.jna.platform.win32.WinDef.WPARAM;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemDebugger extends Item {

	public ItemDebugger() {
		this.setRegistryName("debugger");
		this.setUnlocalizedName("debugger");
		this.setCreativeTab(ModTabs.tab_main);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos pos0 = pos;
		BlockPos pos1 = pos.add(-10, 5, -10);
		
		ArrayList<String> blocks = new ArrayList<String>();
		
		BlockPos[] states = getBlocksBetween(worldIn, pos0, pos1);
		BlockPos startPos = states[0];
		for(int i = 1; i < states.length; i++){
			BlockPos b_pos = states[i];
			IBlockState b = worldIn.getBlockState(b_pos);
			
			Block block = b.getBlock();
			int meta = block.getMetaFromState(b);
			if(block != Blocks.GLASS){
				blocks.add(block.getRegistryName().toString() + ";" + meta + "@" + b_pos.add(-startPos.getX(), -startPos.getY(), -startPos.getZ()).toLong());
			}
		}
		
		DungeonBlockArea area = new DungeonBlockArea(blocks.toString());
		System.out.println(area.toString());
		
		return EnumActionResult.SUCCESS;
	}
	
	public BlockPos[] getBlocksBetween(World world, BlockPos pos0, BlockPos pos1){
		int startX = pos0.getX() < pos1.getX() ? pos0.getX() : pos1.getX();
		int startY = pos0.getY() < pos1.getY() ? pos0.getY() : pos1.getY();
		int startZ = pos0.getZ() < pos1.getZ() ? pos0.getZ() : pos1.getZ();
		int endX = pos0.getX() > pos1.getX() ? pos0.getX() : pos1.getX();
		int endY = pos0.getY() > pos1.getY() ? pos0.getY() : pos1.getY();
		int endZ = pos0.getZ() > pos1.getZ() ? pos0.getZ() : pos1.getZ();
		ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
		blocks.add(new BlockPos(startX, startY, startZ));
		for(int x = startX; x <= endX; x++){
			for(int y = startY; y <= endY; y++){
				for(int z = startZ; z <= endZ; z++){
					blocks.add(new BlockPos(x, y, z));
				}
			}
		}
		
		BlockPos[] values = new BlockPos[blocks.size()];
		for(int i = 0; i < values.length; i++){
			values[i] = blocks.get(i);
		}
		
		return values;
	}
}
