package com.deerangle.main;

import com.deerangle.block.ModBlocks;
import com.deerangle.block.entity.TileEntityManaConcentrator;
import com.deerangle.block.entity.TileEntityPedestal;
import com.deerangle.entity.ModEntities;
import com.deerangle.item.ModItems;
import com.deerangle.network.PacketRequestUpdatePedestal;
import com.deerangle.network.PacketSpawnCustomParticle;
import com.deerangle.network.PacketUpdatePedestal;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(name = InterstellarComets.NAME, modid = InterstellarComets.MODID, version = InterstellarComets.VERSION)
public class InterstellarComets {
	public static final String NAME = "Interstellar Comets";
	public static final String MODID = "interstellarcomets";
	public static final String VERSION = "1.12.2-1.0.1.18";

	@Instance
	public static InterstellarComets instance;
	
	@SidedProxy(clientSide = "com.deerangle.main.ClientProxy", serverSide = "com.deerangle.main.ServerProxy")
	public static ServerProxy proxy;
	
	public static SimpleNetworkWrapper wrapper;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		ModItems.load();
		ModBlocks.load();
		ModEntities.load();
		
		GameRegistry.registerTileEntity(TileEntityPedestal.class, "pedastal");
		GameRegistry.registerTileEntity(TileEntityManaConcentrator.class, "mana_concentrator");
		
		proxy.preinit(event);
		wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		wrapper.registerMessage(new PacketUpdatePedestal.Handler(), PacketUpdatePedestal.class, 0, Side.CLIENT);
		wrapper.registerMessage(new PacketRequestUpdatePedestal.Handler(), PacketRequestUpdatePedestal.class, 1, Side.SERVER);
		wrapper.registerMessage(new PacketSpawnCustomParticle.Handler(), PacketSpawnCustomParticle.class, 2, Side.CLIENT);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		
	}
}
