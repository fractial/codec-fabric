package com.fractial.codec.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class CodecShapedRecipe extends ShapedRecipe {
    public CodecShapedRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification) {
        super(group, category, pattern, result, showNotification);
    }

    public CodecShapedRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result) {
        super(group, category, pattern, result, true);
    }
}
