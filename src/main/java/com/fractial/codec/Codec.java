package com.fractial.codec;

import com.fractial.codec.server.IMinecraftServer;
import com.fractial.codec.server.IReloadableServerResources;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Codec implements ModInitializer {
	public static final String MOD_ID = "codec";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> {
			var items = ((IReloadableServerResources) ((IMinecraftServer) minecraftServer).codec$getResources()).codec$getItemStackManager().getAllItems();

			for (var item : items.keySet()) {
				System.out.println("Item: " + item);
			}
		});
	}
}