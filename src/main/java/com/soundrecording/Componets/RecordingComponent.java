package com.soundrecording.Componets;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public record RecordingComponent(List<SoundEvent> soundEvents, List<BlockPos> blockPos, List<Integer> tick,
                                 List<Double> volume, List<Double> pitch, int size) {

    public static final Codec<RecordingComponent> CODEC = RecordCodecBuilder.create(builder ->
        builder.group(
                SoundEvent.CODEC.listOf().fieldOf("sound_type").forGetter(RecordingComponent::soundEvents),
                BlockPos.CODEC.listOf().fieldOf("relative_position").forGetter(RecordingComponent::blockPos),
                Codec.INT.listOf().fieldOf("tick").forGetter(RecordingComponent::tick),
                Codec.DOUBLE.listOf().fieldOf("volume").forGetter(RecordingComponent::volume),
                Codec.DOUBLE.listOf().fieldOf("pitch").forGetter(RecordingComponent::pitch),
                Codec.INT.fieldOf("size").forGetter(RecordingComponent::size)
        ).apply(builder, RecordingComponent::new)
    );

}
