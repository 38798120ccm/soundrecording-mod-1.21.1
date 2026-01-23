package com.soundrecording.Componets;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public record RecordingComponent(List<Identifier> identifiers, List<Double> x, List<Double> y, List<Double> z, List<Integer> tick,
                                 List<Double> volume, List<Double> pitch, List<Boolean> repeat, List<Integer> repeatDelay, List<Integer> attenuationtype, List<Boolean> relative,int size) {

    public static final Codec<RecordingComponent> CODEC = RecordCodecBuilder.create(builder ->
        builder.group(
                Identifier.CODEC.listOf().fieldOf("identifiers").forGetter(RecordingComponent::identifiers),
                Codec.DOUBLE.listOf().fieldOf("relative_positions_x").forGetter(RecordingComponent::x),
                Codec.DOUBLE.listOf().fieldOf("relative_positions_y").forGetter(RecordingComponent::y),
                Codec.DOUBLE.listOf().fieldOf("relative_positions_z").forGetter(RecordingComponent::z),
                Codec.INT.listOf().fieldOf("ticks").forGetter(RecordingComponent::tick),
                Codec.DOUBLE.listOf().fieldOf("volumes").forGetter(RecordingComponent::volume),
                Codec.DOUBLE.listOf().fieldOf("pitches").forGetter(RecordingComponent::pitch),
                Codec.BOOL.listOf().fieldOf("repeats").forGetter(RecordingComponent::repeat),
                Codec.INT.listOf().fieldOf("repeatdelays").forGetter(RecordingComponent::repeatDelay),
                Codec.INT.listOf().fieldOf("attenuationtypes").forGetter(RecordingComponent::attenuationtype),
                Codec.BOOL.listOf().fieldOf("relatives").forGetter(RecordingComponent::relative),
                Codec.INT.fieldOf("size").forGetter(RecordingComponent::size)
        ).apply(builder, RecordingComponent::new)
    );
}


