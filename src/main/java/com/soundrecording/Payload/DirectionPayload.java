package com.soundrecording.Payload;

import com.soundrecording.Componets.DirectionComponent;
import com.soundrecording.SoundRecordingMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DirectionPayload(float yaw, float pitch) implements CustomPayload {

    public static final CustomPayload.Id<DirectionPayload> ID = new Id<>(Identifier.of(SoundRecordingMod.MOD_ID, "direction-payload"));

    public static final PacketCodec<RegistryByteBuf, DirectionPayload> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT, DirectionPayload::yaw,
            PacketCodecs.FLOAT, DirectionPayload::pitch,
            DirectionPayload::new
    );

    public DirectionComponent toComponent(){
        return new DirectionComponent(this.yaw, this.pitch);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
