package com.fractial.codec.world.item;

import com.fractial.codec.core.registries.CodecRegistries;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ItemStackManager extends SimpleJsonResourceReloadListener<ItemStack> {
    private static Map<ResourceLocation, ItemStack> items = Map.of();

    public ItemStackManager(HolderLookup.Provider provider) {
        super(provider, ItemStack.CODEC, CodecRegistries.CODEC_ITEM);
    }

    @Override
    protected void apply(Map<ResourceLocation, ItemStack> map, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        items = ImmutableMap.copyOf(map);
    }

    @Nullable
    public static ItemStack get(ResourceLocation resourceLocation) {
        return items.get(resourceLocation);
    }

    @Nullable
    public static Map<ResourceLocation, ItemStack> getAllItems() {
        return items;
    }
}
