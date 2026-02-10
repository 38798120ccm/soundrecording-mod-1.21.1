package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record StatusComponent(int status) {
    public static final Codec<StatusComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.INT.fieldOf("status").forGetter(StatusComponent::status)
            ).apply(builder, StatusComponent::new)
    );
}
