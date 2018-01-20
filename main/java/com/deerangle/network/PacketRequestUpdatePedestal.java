package com.deerangle.network;

import com.deerangle.block.entity.TileEntityPedestal;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRequestUpdatePedestal implements IMessage {

	private BlockPos pos;
	private int dimension;

	public PacketRequestUpdatePedestal(BlockPos pos, int dimension) {
		this.pos = pos;
		this.dimension = dimension;
	}

	public PacketRequestUpdatePedestal(TileEntityPedestal te) {
		this(te.getPos(), te.getWorld().provider.getDimension());
	}

	public PacketRequestUpdatePedestal() {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		buf.writeInt(dimension);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		dimension = buf.readInt();
	}

	public static class Handler implements IMessageHandler<PacketRequestUpdatePedestal, IMessage> {

		@Override
		public IMessage onMessage(PacketRequestUpdatePedestal message, MessageContext ctx) {
			World world = FMLCommonHandler.instance().getMinecraftServerInstance().worlds[message.dimension];
			TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(message.pos);
			if (te != null) {
				return new PacketUpdatePedestal(te);
			} else {
				return null;
			}
		}
	}

}
