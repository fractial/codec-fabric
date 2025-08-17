package com.fractial.codec.world.item;

import com.fractial.codec.core.registries.CodecRegistries;
import com.google.common.collect.ImmutableMap;
import com.mojang.logging.LogUtils;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Map;

public class ItemStackManager extends SimpleJsonResourceReloadListener<ItemStack> {
    private static final Logger LOGGER = LogUtils.getLogger();
    private Map<ResourceLocation, ItemStack> items = Map.of();
    private final HolderLookup.Provider registries;

    public ItemStackManager(HolderLookup.Provider provider) {
        super(provider, ItemStack.CODEC, CodecRegistries.ITEM);
        this.registries = provider;
    }

    @Override
    protected void apply(Map<ResourceLocation, ItemStack> map, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        this.items = ImmutableMap.copyOf(map);
    }

    @Nullable
    public ItemStack get(ResourceLocation resourceLocation) {
        return this.items.get(resourceLocation);
    }
}
