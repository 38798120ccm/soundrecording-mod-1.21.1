package com.soundrecording.Codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.soundrecording.SoundRecordingMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PositionCodec(double x, double y, double z) implements CustomPayload {

    public static final Codec<PositionCodec> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.DOUBLE.fieldOf("posX").forGetter(PositionCodec::x),
                    Codec.DOUBLE.fieldOf("posY").forGetter(PositionCodec::y),
                    Codec.DOUBLE.fieldOf("posZ").forGetter(PositionCodec::z)
            ).apply(builder, PositionCodec::new)
    );

    public static final CustomPayload.Id<PositionCodec> ID = new CustomPayload.Id<>(Identifier.of(SoundRecordingMod.MOD_ID, "position-payload"));

    public static final PacketCodec<RegistryByteBuf, PositionCodec> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.DOUBLE, PositionCodec::x,
            PacketCodecs.DOUBLE, PositionCodec::y,
            PacketCodecs.DOUBLE, PositionCodec::z,
            PositionCodec::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}

