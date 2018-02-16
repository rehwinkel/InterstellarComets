package com.deerangle.world;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockData {

	private BlockPos pos;
	private Block block;
	private int meta;
	
	public BlockData(BlockPos pos, Block block, int meta) {
		this.pos = pos;
		this.block = block;
		this.meta = meta;
	}
	
	@Override
	public String toString() {
		return block.getRegistryName().toString() + ";"  + meta + "@" + pos.toLong();
	}

	public static BlockData fromString(String blockIn) {
		String p1 = blockIn.split("@")[0];
		Block b = Block.REGISTRY.getObject(new ResourceLocation(p1.split(";")[0]));
		int meta = Integer.parseInt(p1.split(";")[1]);
		BlockPos p = BlockPos.fromLong(Long.parseLong(blockIn.split("@")[1]));
		return new BlockData(p, b, meta);
	}

	public BlockPos getPos() {
		return pos;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public int getMeta() {
		return meta;
	}

	public void setMeta(int meta) {
		this.meta = meta;
	}

	public IBlockState toBlockState() {
		return block.getStateFromMeta(meta);
	}
	
}
