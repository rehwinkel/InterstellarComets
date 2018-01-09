package com.deerangle.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockTest extends Block {

	public BlockTest(Material tnt) {
		super(tnt);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		this.createExplosion(worldIn, playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, 100F, true);
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	public Explosion createExplosion(World world, @Nullable Entity entityIn, double x, double y, double z, float strength, boolean isSmoking) {
		return this.newExplosion(world, entityIn, x, y, z, strength, false, isSmoking);
	}
	
	public Explosion newExplosion(World world, @Nullable Entity entityIn, double x, double y, double z, float strength, boolean isFlaming, boolean isSmoking) {
		Explosion explosion = new Explosion(world, entityIn, x, y, z, strength, isFlaming, isSmoking);
		if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(world, explosion))
			return explosion;
		explosion.doExplosionA();
		explosion.doExplosionB(true);
		return explosion;
	}
}
