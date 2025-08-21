package com.fractial.codec.world.item.crafting;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

import static net.minecraft.world.item.crafting.RecipeSerializer.register;

public interface CodecRecipeSerializer<T extends Recipe<?>> {
    RecipeSerializer<ShapedRecipe> CODEC_SHAPED_RECIPE = register("codec_crafting_shaped", new CodecShapedRecipe.Serializer());
}
