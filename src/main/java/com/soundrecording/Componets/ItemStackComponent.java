package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;

public record ItemStackComponent(ItemStack itemStack) {

    public static final Codec<ItemStackComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    ItemStack.CODEC.fieldOf("itemStack").forGetter(ItemStackComponent::itemStack)
            ).apply(builder, ItemStackComponent::new)
    );
}
