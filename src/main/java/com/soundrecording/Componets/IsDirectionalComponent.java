package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record IsDirectionalComponent(boolean isDirectional) {
    public static final Codec<IsDirectionalComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.BOOL.fieldOf("isDirectional").forGetter(IsDirectionalComponent::isDirectional)
            ).apply(builder, IsDirectionalComponent::new)
    );
}
