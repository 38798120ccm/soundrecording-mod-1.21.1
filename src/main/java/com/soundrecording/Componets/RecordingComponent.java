package com.soundrecording.Componets;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.soundrecording.Codecs.DirectionCodec;
import com.soundrecording.Codecs.PositionCodec;
import com.soundrecording.Codecs.SoundCodec;

import java.util.ArrayList;
import java.util.List;

public record RecordingComponent(List<SoundCodec> sound, List<PositionCodec> pos, List<DirectionCodec> dir,
                                 List<Integer> tick, int size) {

    public RecordingComponent() {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0);
    }

    public static final Codec<RecordingComponent> CODEC = RecordCodecBuilder.create(builder ->
        builder.group(
                SoundCodec.CODEC.listOf().fieldOf("sounds").forGetter(RecordingComponent::sound),
                PositionCodec.CODEC.listOf().fieldOf("positions").forGetter(RecordingComponent::pos),
                DirectionCodec.CODEC.listOf().fieldOf("directions").forGetter(RecordingComponent::dir),
                Codec.INT.listOf().fieldOf("ticks").forGetter(RecordingComponent::tick),
                Codec.INT.fieldOf("size").forGetter(RecordingComponent::size)
        ).apply(builder, RecordingComponent::new)
    );

    public int lastTick(){
        return (size>0? tick().get(size-1): 0);
    }
}


