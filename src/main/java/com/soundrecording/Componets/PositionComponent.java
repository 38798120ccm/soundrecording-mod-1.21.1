package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.soundrecording.Payload.PositionPayload;
import net.minecraft.item.ItemStack;
import net.minecraft.util.dynamic.Codecs;

public record PositionComponent(double x, double y, double z) {

    public static final Codec<PositionComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.DOUBLE.fieldOf("posX").forGetter(PositionComponent::x),
                    Codec.DOUBLE.fieldOf("posY").forGetter(PositionComponent::y),
                    Codec.DOUBLE.fieldOf("posZ").forGetter(PositionComponent::z)
            ).apply(builder, PositionComponent::new)
    );
}

