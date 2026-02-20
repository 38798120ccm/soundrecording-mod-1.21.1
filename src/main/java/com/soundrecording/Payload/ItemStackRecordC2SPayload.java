package com.soundrecording.Payload;

import com.soundrecording.Codecs.DirectionCodec;
import com.soundrecording.Codecs.PositionCodec;
import com.soundrecording.Codecs.SoundCodec;
import com.soundrecording.SoundRecordingMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ItemStackRecordC2SPayload(SoundCodec soundPayload, PositionCodec posPayload,
                                        DirectionCodec dirPayload, int slotId, int tick) implements CustomPayload {
    public static final Id<ItemStackRecordC2SPayload> ID = new Id<>(Identifier.of(SoundRecordingMod.MOD_ID, "itemstackrecord-payload"));
    public static final PacketCodec<RegistryByteBuf, ItemStackRecordC2SPayload> PACKET_CODEC =
            PacketCodec.tuple(
                    SoundCodec.PACKET_CODEC, ItemStackRecordC2SPayload::soundPayload,
                    PositionCodec.PACKET_CODEC, ItemStackRecordC2SPayload::posPayload,
                    DirectionCodec.PACKET_CODEC, ItemStackRecordC2SPayload::dirPayload,
                    PacketCodecs.INTEGER, ItemStackRecordC2SPayload::slotId,
                    PacketCodecs.INTEGER, ItemStackRecordC2SPayload::tick,
                    ItemStackRecordC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
