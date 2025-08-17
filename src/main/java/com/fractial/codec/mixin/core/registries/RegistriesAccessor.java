package com.fractial.codec.mixin.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Registries.class)
public interface RegistriesAccessor {
    @Invoker("createRegistryKey")
    static <T> ResourceKey<Registry<T>> createRegistryKey(String string) {
        throw new AssertionError();
    }
}
