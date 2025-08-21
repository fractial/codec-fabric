package com.fractial.codec.mixin.world.item.crafting;

import com.fractial.codec.world.item.ItemStackManager;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ShapedRecipe.class)
public class ShapedRecipeMixin {

    @Mixin(ShapedRecipe.Serializer.class)
    public static class SerializerMixin {
        @Shadow
        public static final MapCodec<ShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(ShapedRecipe::group),
                                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(ShapedRecipe::category),
                                ShapedRecipePattern.MAP_CODEC.forGetter(shapedRecipe -> ((ShapedRecipeAccessor) shapedRecipe).pattern()),
                                Codec.either(ResourceLocation.CODEC, ItemStack.STRICT_CODEC)
                                                .fieldOf("result")
                                                        .xmap(
                                                                either -> either.map(
                                                                        ItemStackManager::get,
                                                                        itemStack -> itemStack
                                                                ),
                                                                Either::right
                                                        )
                                                                .forGetter(shapedRecipe -> ((ShapedRecipeAccessor) shapedRecipe).result()),
                                Codec.BOOL.optionalFieldOf("show_notification", true).forGetter(ShapedRecipe::showNotification)
                        )
                        .apply(instance, ShapedRecipe::new)
        );
    }
}
