package com.deerangle.world;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import com.deerangle.block.BlockComet;
import com.deerangle.block.BlockCometicBrick;
import com.deerangle.block.ModBlocks;

import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent.Tick;
import scala.actors.threadpool.Arrays;

public class DungeonBlockArea extends BlockArea {

	public DungeonBlockArea(String blockdata) {
		super(blockdata);
	}

	public DungeonBlockArea() {
		super();
	}

	@Override
	public void placeAt(World worldIn, BlockPos startPos) {
		if (!worldIn.isRemote) {
			if (blockdata.size() > 0) {
				for (BlockData bd : blockdata) {
					if (bd.getBlock() == ModBlocks.cometic_brick && bd.getMeta() == 1) {
						worldIn.setBlockState(bd.getPos().add(startPos), ModBlocks.cometic_brick.getStateFromMeta(Math.random() > 0.7 ? 3 : 1));
					} else if (bd.getBlock() == Blocks.MOB_SPAWNER) {
						worldIn.setBlockState(bd.getPos().add(startPos), Blocks.MOB_SPAWNER.getDefaultState());
						placeRandomSpawner(worldIn, bd.getPos().add(startPos));
					} else {
						worldIn.setBlockState(bd.getPos().add(startPos), bd.toBlockState());
					}
				}
			}
		}
	}
	
	public void placeWithConnectAt(World worldIn, BlockPos startPos, int[] connections){
		placeAt(worldIn, startPos);
		placeDoors(worldIn, startPos, connections);
	}

	private void placeDoors(World worldIn, BlockPos start, int[] connections) {
		for(int i = 0; i < 4; i++){
			placeDoor(worldIn, i, start, ArrayUtils.contains(connections, i));
		}
	}

	private void placeDoor(World worldIn, int dir, BlockPos start, boolean open) {
		if (open) {
			switch (dir) {
			case 0:
				// NORTH
				worldIn.setBlockToAir(start.add(5, 1, 0));
				worldIn.setBlockToAir(start.add(5, 2, 0));
				worldIn.setBlockState(start.add(5, 3, 1), ModBlocks.cometic_brick.getStateFromMeta(4));
				worldIn.setBlockState(start.add(4, 1, 1), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(6, 1, 1), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(4, 2, 1), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(6, 2, 1), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(6, 3, 1), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(4, 3, 1), ModBlocks.cometic_brick.getStateFromMeta(1));
			case 1:
				// EAST
				worldIn.setBlockToAir(start.add(10, 1, 5));
				worldIn.setBlockToAir(start.add(10, 2, 5));
				worldIn.setBlockState(start.add(9, 3, 5), ModBlocks.cometic_brick.getStateFromMeta(4));
				worldIn.setBlockState(start.add(9, 1, 4), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(9, 1, 6), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(9, 2, 4), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(9, 2, 6), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(9, 3, 6), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(9, 3, 4), ModBlocks.cometic_brick.getStateFromMeta(1));
				break;
			case 2:
				// SOUTH 2
				worldIn.setBlockToAir(start.add(5, 1, 10));
				worldIn.setBlockToAir(start.add(5, 2, 10));
				worldIn.setBlockState(start.add(5, 3, 9), ModBlocks.cometic_brick.getStateFromMeta(4));
				worldIn.setBlockState(start.add(4, 1, 9), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(6, 1, 9), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(4, 2, 9), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(6, 2, 9), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(6, 3, 9), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(4, 3, 9), ModBlocks.cometic_brick.getStateFromMeta(1));
				break;
			case 3:
				// WEST
				worldIn.setBlockToAir(start.add(0, 1, 5));
				worldIn.setBlockToAir(start.add(0, 2, 5));
				worldIn.setBlockState(start.add(1, 3, 5), ModBlocks.cometic_brick.getStateFromMeta(4));
				worldIn.setBlockState(start.add(1, 1, 4), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(1, 1, 6), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(1, 2, 4), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(1, 2, 6), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(1, 3, 6), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(1, 3, 4), ModBlocks.cometic_brick.getStateFromMeta(1));
				break;
			}
		} else {
			switch (dir) {
			case 0:
				// NORTH
				worldIn.setBlockState(start.add(5, 1, 0), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(5, 2, 0), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockToAir(start.add(5, 3, 1));
				worldIn.setBlockToAir(start.add(4, 1, 1));
				worldIn.setBlockToAir(start.add(6, 1, 1));
				worldIn.setBlockToAir(start.add(4, 2, 1));
				worldIn.setBlockToAir(start.add(6, 2, 1));
				worldIn.setBlockToAir(start.add(6, 3, 1));
				worldIn.setBlockToAir(start.add(4, 3, 1));
			case 1:
				// EAST
				worldIn.setBlockState(start.add(10, 1, 5), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(10, 2, 5), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockToAir(start.add(9, 3, 5));
				worldIn.setBlockToAir(start.add(9, 1, 4));
				worldIn.setBlockToAir(start.add(9, 1, 6));
				worldIn.setBlockToAir(start.add(9, 2, 4));
				worldIn.setBlockToAir(start.add(9, 2, 6));
				worldIn.setBlockToAir(start.add(9, 3, 6));
				worldIn.setBlockToAir(start.add(9, 3, 4));
				break;
			case 2:
				// SOUTH 2
				worldIn.setBlockState(start.add(5, 1, 10), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(5, 2, 10), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockToAir(start.add(5, 3, 9));
				worldIn.setBlockToAir(start.add(4, 1, 9));
				worldIn.setBlockToAir(start.add(6, 1, 9));
				worldIn.setBlockToAir(start.add(4, 2, 9));
				worldIn.setBlockToAir(start.add(6, 2, 9));
				worldIn.setBlockToAir(start.add(6, 3, 9));
				worldIn.setBlockToAir(start.add(4, 3, 9));
				break;
			case 3:
				// WEST
				worldIn.setBlockState(start.add(0, 1, 5), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockState(start.add(0, 2, 5), ModBlocks.cometic_brick.getStateFromMeta(1));
				worldIn.setBlockToAir(start.add(1, 3, 5));
				worldIn.setBlockToAir(start.add(1, 1, 4));
				worldIn.setBlockToAir(start.add(1, 1, 6));
				worldIn.setBlockToAir(start.add(1, 2, 4));
				worldIn.setBlockToAir(start.add(1, 2, 6));
				worldIn.setBlockToAir(start.add(1, 3, 6));
				worldIn.setBlockToAir(start.add(1, 3, 4));
				break;
			}
		}
	}

	private void placeRandomSpawner(World worldIn, BlockPos pos) {
		String[] mobs = new String[] { "minecraft:zombie" };
		int r = worldIn.rand.nextInt(mobs.length);
		placeSpawner(worldIn, pos, mobs[r]);
	}

	private void placeSpawner(World worldIn, BlockPos pos, String mob) {
		TileEntityMobSpawner te = (TileEntityMobSpawner) worldIn.getTileEntity(pos);
		MobSpawnerBaseLogic logic = te.getSpawnerBaseLogic();
		NBTTagCompound spawn = new NBTTagCompound();
		spawn.setString("id", mob);
		logic.setNextSpawnData(new WeightedSpawnerEntity(1, spawn));
	}

}
