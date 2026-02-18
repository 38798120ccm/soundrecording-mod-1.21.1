package com.soundrecording.Componets;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public record RecordingComponent(List<Identifier> identifiers, List<PositionComponent> pos, List<DirectionComponent> dir,
                                 List<Integer> tick, List<Double> volume, List<Double> pitch, int size) {
//, List<Boolean> repeat, List<Integer> repeatDelay, List<Integer> attenuationtype, List<Boolean> relative

    public RecordingComponent() {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
    }

    public static final Codec<RecordingComponent> CODEC = RecordCodecBuilder.create(builder ->
        builder.group(
                Identifier.CODEC.listOf().fieldOf("identifiers").forGetter(RecordingComponent::identifiers),
                PositionComponent.CODEC.listOf().fieldOf("positions").forGetter(RecordingComponent::pos),
                DirectionComponent.CODEC.listOf().fieldOf("directions").forGetter(RecordingComponent::dir),
                Codec.INT.listOf().fieldOf("ticks").forGetter(RecordingComponent::tick),
                Codec.DOUBLE.listOf().fieldOf("volumes").forGetter(RecordingComponent::volume),
                Codec.DOUBLE.listOf().fieldOf("pitches").forGetter(RecordingComponent::pitch),
                Codec.INT.fieldOf("size").forGetter(RecordingComponent::size)
        ).apply(builder, RecordingComponent::new)
    );

    public int LastTick(){
        return tick().get(size-1);
    }
}


