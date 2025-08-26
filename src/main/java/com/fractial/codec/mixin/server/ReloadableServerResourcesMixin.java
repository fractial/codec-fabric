package com.fractial.codec.mixin.server;

import com.fractial.codec.server.IReloadableServerResources;
import com.fractial.codec.world.item.ItemStackManager;
import net.minecraft.commands.Commands;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@Mixin(ReloadableServerResources.class)
public class ReloadableServerResourcesMixin implements IReloadableServerResources {
    @Shadow @Final private RecipeManager recipes;
    @Mutable
    @Unique
    @Final
    private ItemStackManager items;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/RecipeManager;<init>(Lnet/minecraft/core/HolderLookup$Provider;)V", shift = At.Shift.BEFORE))
    private void init(LayeredRegistryAccess<RegistryLayer> layeredRegistryAccess,
                      HolderLookup.Provider provider,
                      FeatureFlagSet featureFlagSet,
                      Commands.CommandSelection commandSelection,
                      List<Registry.PendingTags<?>> list,
                      int i,
                      CallbackInfo ci
    ) {
        System.out.println("RECIPES: " + this.recipes);
        this.items = new ItemStackManager(provider);
    }

    @Unique
    public ItemStackManager codec$getItemStackManager() {
        return this.items;
    }

    @Inject(method = "listeners", at = @At("RETURN"), cancellable = true)
    public void listeners(CallbackInfoReturnable<List<PreparableReloadListener>> cir) {
        List<PreparableReloadListener> list = cir.getReturnValue();
        List<PreparableReloadListener> newList = new ArrayList<>(list);
        newList.addFirst(this.items);
        System.out.println(newList);
        cir.setReturnValue(Collections.unmodifiableList(newList));
    }

    @Inject(method = "loadResources", at = @At("RETURN"))
    private static void loadResources(ResourceManager resourceManager, LayeredRegistryAccess<RegistryLayer> layeredRegistryAccess, List<Registry.PendingTags<?>> list, FeatureFlagSet featureFlagSet, Commands.CommandSelection commandSelection, int i, Executor executor, Executor executor2, CallbackInfoReturnable<CompletableFuture<ReloadableServerResources>> cir) throws ExecutionException, InterruptedException {
        System.out.println("LISTENERS: " + cir.getReturnValue().thenAccept(reloadableServerResources -> System.out.println("LISTENERS: " + reloadableServerResources.listeners())));
    }
}
