package com.fractial.codec.mixin.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ShapedRecipe.class)
public interface ShapelessRecipeAccessor {
    @Accessor("pattern")
    ShapedRecipePattern pattern();

    @Accessor("result")
    ItemStack result();
}
