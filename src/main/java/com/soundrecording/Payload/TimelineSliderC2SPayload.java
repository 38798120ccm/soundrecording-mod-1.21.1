package com.soundrecording.Payload;

import com.soundrecording.SoundRecordingMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record TimelineSliderC2SPayload(float percent, int prestatus, int id) implements CustomPayload {
    public static final Id<TimelineSliderC2SPayload> ID = new Id<>(Identifier.of(SoundRecordingMod.MOD_ID, "timelinesliderc2s-payload"));

    public static final PacketCodec<RegistryByteBuf, TimelineSliderC2SPayload> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.FLOAT, TimelineSliderC2SPayload::percent,
                    PacketCodecs.VAR_INT, TimelineSliderC2SPayload::prestatus,
                    PacketCodecs.VAR_INT, TimelineSliderC2SPayload::id,
                    TimelineSliderC2SPayload::new);
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
