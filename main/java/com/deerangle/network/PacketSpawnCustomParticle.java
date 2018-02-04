package com.deerangle.network;

import com.deerangle.particle.ParticleMana;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSpawnCustomParticle implements IMessage {

	public double startX;
	public double startY;
	public double startZ;
	public double destX;
	public double destY;
	public double destZ;

	public PacketSpawnCustomParticle() {
		this.startX = 0;
		this.startY = 0;
		this.startZ = 0;
		this.destX = 0;
		this.destY = 0;
		this.destZ = 0;
	}
	
	public PacketSpawnCustomParticle(double startX, double startY, double startZ, double destX, double destY, double destZ) {
		this.startX = startX;
		this.startY = startY;
		this.startZ = startZ;
		this.destX = destX;
		this.destY = destY;
		this.destZ = destZ;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		startX = buf.readDouble();
		startY = buf.readDouble();
		startZ = buf.readDouble();
		destX = buf.readDouble();
		destY = buf.readDouble();
		destZ = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(startX);
		buf.writeDouble(startY);
		buf.writeDouble(startZ);
		buf.writeDouble(destX);
		buf.writeDouble(destY);
		buf.writeDouble(destZ);
	}

	public static class Handler implements IMessageHandler<PacketSpawnCustomParticle, IMessage> {

		@Override
		public IMessage onMessage(PacketSpawnCustomParticle message, MessageContext ctx) {
			Minecraft mc = Minecraft.getMinecraft();
			
			//ParticleMana particle = new ParticleMana(mc.world, message.startX, message.startY, message.startZ, message.destX, message.destY, message.destZ, 0, 1.2, 0);
			//mc.effectRenderer.addEffect(particle);
			return null;
		}
		
	}
	
}
