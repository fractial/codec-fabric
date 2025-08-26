package com.fractial.codec.server.commands.arguments;

import com.fractial.codec.mixin.core.registries.RegistriesAccessor;
import com.fractial.codec.world.item.ItemStackManager;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.stream.Stream;

import static net.minecraft.commands.arguments.ResourceKeyArgument.getRegistryKey;

public class CodecResourceKeyArgument {
    private static final DynamicCommandExceptionType ERROR_INVALID_ITEM = new DynamicCommandExceptionType(
            object -> Component.translatableEscape("item.notFound", object)
    );

    public static ItemStack getItemStack(CommandContext<CommandSourceStack> commandContext, String string) throws CommandSyntaxException {
        ResourceKey<ItemStack> resourceKey = getRegistryKey(commandContext, string, RegistriesAccessor.createRegistryKey("codec_item"), ERROR_INVALID_ITEM);
        return ItemStackManager.getItemStack(resourceKey.location());
    }

    public static Stream<String> getItems(CommandContext<CommandSourceStack> commandContext) {
        return ItemStackManager.getAllItems().keySet().stream().map(ResourceLocation::toString);
    }
}
