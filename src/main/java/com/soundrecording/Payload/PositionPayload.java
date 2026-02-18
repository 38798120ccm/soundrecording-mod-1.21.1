package com.soundrecording.Payload;

import com.soundrecording.Componets.PositionComponent;
import com.soundrecording.SoundRecordingMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PositionPayload(double x, double y, double z) implements CustomPayload {

    public static final CustomPayload.Id<PositionPayload> ID = new Id<>(Identifier.of(SoundRecordingMod.MOD_ID, "position-payload"));

    public static final PacketCodec<RegistryByteBuf, PositionPayload> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.DOUBLE, PositionPayload::x,
            PacketCodecs.DOUBLE, PositionPayload::y,
            PacketCodecs.DOUBLE, PositionPayload::z,
            PositionPayload::new
    );

    public PositionComponent toComponent()
    {
        return new PositionComponent(this.x, this.y, this.z);
    }
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
