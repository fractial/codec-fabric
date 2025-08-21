package com.fractial.codec.mixin.server;

import com.fractial.codec.Codec;
import com.fractial.codec.server.IMinecraftServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ReloadableServerResources;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin implements IMinecraftServer {
    @Inject(method = "getServerModName", at = @At("HEAD"), cancellable = true, remap = false)
    private void getServerModName(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(Codec.MOD_ID);
    }

    @Override
    @Nullable
    @Deprecated
    public ReloadableServerResources codec$getResources() {
        try {
            Field resources = MinecraftServer.class.getDeclaredField("resources");
            resources.setAccessible(true);
            Method managers = resources.getType().getDeclaredMethod("managers");
            managers.setAccessible(true);
            Object reloadableResources = resources.get(this);
            return (ReloadableServerResources) managers.invoke(reloadableResources);
        } catch (Exception e) {
            return null;
        }
    }
}
