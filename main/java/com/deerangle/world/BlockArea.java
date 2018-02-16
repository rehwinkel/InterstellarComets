package com.deerangle.world;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockArea {

	protected ArrayList<BlockData> blockdata;
	protected BlockPos size;

	public BlockArea(String blockdata) {
		this.blockdata = new ArrayList<BlockData>();
		
		int maxX = 0;
		int maxY = 0;
		int maxZ = 0;

		String bd = blockdata.replaceAll("\\[", "").replaceAll("\\]", "");
		for (String b : bd.split(", ")) {
			BlockData data = BlockData.fromString(b);
			if (data.getPos().getX() > maxX)
				maxX = data.getPos().getX();
			if (data.getPos().getY() > maxY)
				maxY = data.getPos().getY();
			if (data.getPos().getZ() > maxZ)
				maxZ = data.getPos().getZ();
			this.blockdata.add(data);
		}

		size = new BlockPos(maxX, maxY, maxZ);
	}

	public BlockArea() {
		blockdata = new ArrayList<BlockData>();
		size = new BlockPos(0, 0, 0);
	}
	
	@Override
	public String toString() {
		return blockdata.toString();
	}

	public void placeAt(World worldIn, BlockPos startPos) {
		if(!worldIn.isRemote){
			if(blockdata.size() > 0){
				for (BlockData bd : blockdata) {
					worldIn.setBlockState(bd.getPos().add(startPos), bd.toBlockState());
				}
			}
		}
	}

}
