package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record IsSoundAroundComponent(boolean issoundaround) {
    public static final Codec<IsSoundAroundComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.BOOL.fieldOf("isDirectional").forGetter(IsSoundAroundComponent::issoundaround)
            ).apply(builder, IsSoundAroundComponent::new)
    );
}
