package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record DirectionComponent(float yaw, float pitch){
    public static final Codec<DirectionComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.FLOAT.fieldOf("yaw").forGetter(DirectionComponent::yaw),
                    Codec.FLOAT.fieldOf("pitch").forGetter(DirectionComponent::pitch)
            ).apply(builder, DirectionComponent::new)
    );
}
