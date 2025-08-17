package com.fractial.codec.core.registries;

import com.fractial.codec.mixin.core.registries.RegistriesAccessor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;

public class CodecRegistries {
    public static final ResourceKey<Registry<ItemStack>> ITEM = RegistriesAccessor.createRegistryKey("item");
}
