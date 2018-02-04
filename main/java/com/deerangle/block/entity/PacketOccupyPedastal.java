package com.deerangle.block.entity;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOccupyPedastal implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class Handler implements IMessageHandler<PacketOccupyPedastal, IMessage> {

		@Override
		public IMessage onMessage(PacketOccupyPedastal message, MessageContext ctx) {
			return null;
		}
		
	}
	
}
