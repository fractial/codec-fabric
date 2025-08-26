package com.fractial.codec.world.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;

public class CodecItemStack {
    public static final MapCodec<ItemStack> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            ResourceLocation.CODEC.fieldOf("name").forGetter(ItemStackManager::getResourceLocation),
                            ExtraCodecs.intRange(1, 99).optionalFieldOf("count", 1).forGetter(ItemStack::getCount)
                    )
                    .apply(instance, ((id, count) -> {
                        ItemStack itemStack = ItemStackManager.getItemStack(id);
                        System.out.println("CHECK");
                        if (itemStack == null) throw new IllegalArgumentException("Unknown item: " + id);
                        itemStack.setCount(count);
                        return itemStack;
                    }))
    );

    public static final Codec<ItemStack> CODEC = Codec.lazyInitialized(MAP_CODEC::codec);
}
