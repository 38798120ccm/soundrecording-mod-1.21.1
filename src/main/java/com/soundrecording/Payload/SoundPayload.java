package com.soundrecording.Payload;

import com.soundrecording.SoundRecordingMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SoundPayload(Identifier soundId, float volume, float pitch, double x, double y, double z) implements CustomPayload {
    public static final CustomPayload.Id<SoundPayload> ID = new Id<>(Identifier.of(SoundRecordingMod.MOD_ID, "sound-payload"));

    public static final PacketCodec<RegistryByteBuf, SoundPayload> PACKET_CODEC = PacketCodec.tuple(
            Identifier.PACKET_CODEC, SoundPayload::soundId,
            PacketCodecs.FLOAT, SoundPayload::volume,
            PacketCodecs.FLOAT, SoundPayload::pitch,
            PacketCodecs.DOUBLE, SoundPayload::x,
            PacketCodecs.DOUBLE, SoundPayload::y,
            PacketCodecs.DOUBLE, SoundPayload::z,
            SoundPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() { return ID; }
}
