package com.fractial.codec.mixin.world.item.crafting;

import com.fractial.codec.world.item.CodecItemStack;
import com.fractial.codec.world.item.ItemStackManager;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ShapelessRecipe.class)
public class ShapelessRecipeMixin {
    @Mixin(ShapelessRecipe.Serializer.class)
    public static class SerializerMixin {
        @Shadow
        private static final MapCodec<ShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(ShapelessRecipe::group),
                                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(ShapelessRecipe::category),
                                Codec.either(CodecItemStack.CODEC, ItemStack.STRICT_CODEC)
                                        .fieldOf("result")
                                        .xmap(
                                                either -> either.map(
                                                        itemStack -> itemStack,
                                                        itemStack -> itemStack
                                                ),
                                                Either::right
                                        )
                                        .forGetter(shapelessRecipe -> ((ShapelessRecipeAccessor) shapelessRecipe).result()),
                                Ingredient.CODEC.listOf(1, 9).fieldOf("ingredients").forGetter(shapelessRecipe -> shapelessRecipe.placementInfo().ingredients())
                        )
                        .apply(instance, ShapelessRecipe::new)
        );
    }
}
