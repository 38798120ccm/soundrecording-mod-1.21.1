package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record TickComponent(int tick) {
    public static final Codec<TickComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.INT.fieldOf("tick").forGetter(TickComponent::tick)
            ).apply(builder, TickComponent::new)
    );
}
