package com.fractial.codec.world.item;

import com.fractial.codec.core.registries.CodecRegistries;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ItemStackManager extends SimplePreparableReloadListener<Map<ResourceLocation, ItemStack>> {
    private static final FileToIdConverter RECIPE_LISTER = FileToIdConverter.registry(CodecRegistries.CODEC_ITEM);
    private final HolderLookup.Provider registries;
    private static Map<ResourceLocation, ItemStack> items = Map.of();

    public ItemStackManager(HolderLookup.Provider provider) {
        this.registries = provider;
    }

    @Override
    protected void apply(Map<ResourceLocation, ItemStack> map, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        System.out.println("SET");
        items = ImmutableMap.copyOf(map);
    }

    @Nullable
    public static ItemStack getItemStack(ResourceLocation resourceLocation) {
        System.out.println("GET");
        return items.get(resourceLocation);
    }

    @Nullable
    public static ResourceLocation getResourceLocation(ItemStack itemStack) {
        return items.entrySet().stream().filter(entry -> ItemStack.isSameItemSameComponents(entry.getValue(), itemStack)).map(Map.Entry::getKey).findFirst().orElse(null);
    }

    @Nullable
    public static Map<ResourceLocation, ItemStack> getAllItems() {
        return items;
    }

    @Override
    protected @NotNull Map<ResourceLocation, ItemStack> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        System.out.println("ITEM STACK MANAGER PREPARE");
        SortedMap<ResourceLocation, ItemStack> sortedMap = new TreeMap<>();
        SimpleJsonResourceReloadListener.scanDirectory(
                resourceManager, RECIPE_LISTER, this.registries.createSerializationContext(JsonOps.INSTANCE), ItemStack.CODEC, sortedMap
        );
        items = sortedMap;
        return sortedMap;
    }
}
