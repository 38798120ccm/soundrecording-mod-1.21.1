package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.soundrecording.Items.MP4Player.MP4PlayerStatus;
import net.minecraft.item.ItemStack;

public record MP4PlayerComponent(ItemStack itemStack, int tick, int status) {

    public static final Codec<MP4PlayerComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    ItemStack.CODEC.fieldOf("microSD").forGetter(MP4PlayerComponent::itemStack),
                    Codec.INT.fieldOf("tick").forGetter(MP4PlayerComponent::tick),
                    Codec.INT.fieldOf("status").forGetter(MP4PlayerComponent::status)
            ).apply(builder, MP4PlayerComponent::new)
    );
}

