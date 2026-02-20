package com.soundrecording.Codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.soundrecording.SoundRecordingMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DirectionCodec(float yaw, float pitch) implements CustomPayload{
    public static final Codec<DirectionCodec> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.FLOAT.fieldOf("yaw").forGetter(DirectionCodec::yaw),
                    Codec.FLOAT.fieldOf("pitch").forGetter(DirectionCodec::pitch)
            ).apply(builder, DirectionCodec::new)
    );

    public static final CustomPayload.Id<DirectionCodec> ID = new CustomPayload.Id<>(Identifier.of(SoundRecordingMod.MOD_ID, "direction-payload"));

    public static final PacketCodec<RegistryByteBuf, DirectionCodec> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT, DirectionCodec::yaw,
            PacketCodecs.FLOAT, DirectionCodec::pitch,
            DirectionCodec::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
