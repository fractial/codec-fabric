package com.fractial.codec.mixin.world.item.crafting;

import net.minecraft.world.item.crafting.SingleItemRecipe;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SingleItemRecipe.class)
public class SingleItemRecipeMixin {
    @Mixin(SingleItemRecipe.Serializer.class)
    public static class SerializerMixin {

    }
}
