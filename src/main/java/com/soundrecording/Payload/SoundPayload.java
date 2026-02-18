package com.soundrecording.Payload;

import com.soundrecording.SoundRecordingMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SoundPayload(Identifier soundId, float volume, float pitch, PositionPayload pos, DirectionPayload dir) implements CustomPayload {
    public static final CustomPayload.Id<SoundPayload> ID = new Id<>(Identifier.of(SoundRecordingMod.MOD_ID, "sound-payload"));

    public static final PacketCodec<RegistryByteBuf, SoundPayload> PACKET_CODEC = PacketCodec.tuple(
            Identifier.PACKET_CODEC, SoundPayload::soundId,
            PacketCodecs.FLOAT, SoundPayload::volume,
            PacketCodecs.FLOAT, SoundPayload::pitch,
            PositionPayload.PACKET_CODEC, SoundPayload::pos,
            DirectionPayload.PACKET_CODEC, SoundPayload::dir,
            SoundPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() { return ID; }
}
