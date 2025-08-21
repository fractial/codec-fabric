package com.fractial.codec;

import com.fractial.codec.server.IMinecraftServer;
import com.fractial.codec.server.IReloadableServerResources;
import com.fractial.codec.world.item.ItemStackManager;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.impl.resource.loader.FabricRecipeManager;
import net.minecraft.server.commands.AdvancementCommands;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Codec implements ModInitializer {
	public static final String MOD_ID = "codec";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> {
            assert ItemStackManager.getAllItems() != null;
            for (var i : ItemStackManager.getAllItems().keySet()) {
				System.out.println("Item: " + i);
			}
		});
	}
}