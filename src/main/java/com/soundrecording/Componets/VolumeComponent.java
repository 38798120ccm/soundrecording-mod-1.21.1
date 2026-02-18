package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record VolumeComponent(float volume) {
    public static final Codec< VolumeComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.FLOAT.fieldOf("volume").forGetter(VolumeComponent::volume)
            ).apply(builder, VolumeComponent::new)
    );
}
