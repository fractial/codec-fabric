package com.fractial.codec.server.commands.arguments;

import com.fractial.codec.core.registries.CodecRegistries;
import com.fractial.codec.server.IMinecraftServer;
import com.fractial.codec.server.IReloadableServerResources;
import com.fractial.codec.world.item.ItemStackManager;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

import static net.minecraft.commands.arguments.ResourceKeyArgument.getRegistryKey;

public class CodecResourceKeyArgument {
    private static final DynamicCommandExceptionType ERROR_INVALID_ITEM = new DynamicCommandExceptionType(
            object -> Component.translatableEscape("item.notFound", object)
    );

    public static ItemStack getItemStack(CommandContext<CommandSourceStack> commandContext, String string) throws CommandSyntaxException {
        ItemStackManager itemStackManager = ((IReloadableServerResources)((IMinecraftServer) commandContext.getSource().getServer()).codec$getResources()).codec$getItemStackManager();
        ResourceKey<ItemStack> resourceKey = getRegistryKey(commandContext, string, CodecRegistries.ITEM, ERROR_INVALID_ITEM);
        return itemStackManager.get(resourceKey.location());
    }

//    public static Map<ResourceLocation, ItemStack> getItems(CommandContext<CommandSourceStack> commandContext) {
//        return ((IReloadableServerResources)((IMinecraftServer) commandContext.getSource().getServer()).codec$getResources()).codec$getItemStackManager();
//    }
}
