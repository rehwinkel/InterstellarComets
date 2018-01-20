package com.deerangle.network;

import com.deerangle.block.TileEntityPedestal;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdatePedestal implements IMessage {

	public BlockPos pos;
	public ItemStack stack;
	public long placeTime;

	public PacketUpdatePedestal(BlockPos pos, ItemStack stack, long placeTime) {
		this.pos = pos;
		this.stack = stack;
		this.placeTime = placeTime;
	}
	
	public PacketUpdatePedestal(TileEntityPedestal entity) {
		this(entity.getPos(), entity.inventory.getStackInSlot(0), entity.placeTime);
	}
	
	public PacketUpdatePedestal() {
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		stack = ByteBufUtils.readItemStack(buf);
		placeTime = buf.readLong();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		ByteBufUtils.writeItemStack(buf, stack);
		buf.writeLong(placeTime);
	}

	public static class Handler implements IMessageHandler<PacketUpdatePedestal, IMessage> {

		@Override
		public IMessage onMessage(PacketUpdatePedestal message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				TileEntityPedestal te = (TileEntityPedestal) Minecraft.getMinecraft().world.getTileEntity(message.pos);
				te.inventory.setStackInSlot(0, message.stack);
				te.placeTime = message.placeTime;
			});
			return null; //returned package
		}
		
	}
	
}
