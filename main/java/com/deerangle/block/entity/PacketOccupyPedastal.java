package com.deerangle.block.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import scala.collection.parallel.ParIterableLike.Min;

public class PacketOccupyPedastal implements IMessage {

	public BlockPos te_pos;
	public BlockPos occupier_pos;
	public boolean occupier_null;
	
	public PacketOccupyPedastal(TileEntityPedestal te, TileEntityManaConcentrator occupier) {
		te_pos = te.getPos();
		if(occupier == null){
			occupier_null = true;
			occupier_pos = new BlockPos(0, 0, 0);
		}else{
			occupier_null = false;
			occupier_pos = occupier.getPos();
		}
	}
	
	public PacketOccupyPedastal() {
		
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		te_pos = BlockPos.fromLong(buf.readLong());
		occupier_pos = BlockPos.fromLong(buf.readLong());
		occupier_null = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(te_pos.toLong());
		buf.writeLong(occupier_pos.toLong());
		buf.writeBoolean(occupier_null);
	}

	public static class Handler implements IMessageHandler<PacketOccupyPedastal, IMessage> {

		@Override
		public IMessage onMessage(PacketOccupyPedastal message, MessageContext ctx) {
			Minecraft mc = Minecraft.getMinecraft();
			TileEntityPedestal te = (TileEntityPedestal) mc.world.getTileEntity(message.te_pos);
			if(te != null){
				if(!message.occupier_null){
					TileEntityManaConcentrator occupier = (TileEntityManaConcentrator) mc.world.getTileEntity(message.occupier_pos);
					te.occupier = occupier;
				}else{
					te.occupier = null;
				}
			}
			return null;
		}
		
	}
	
}
