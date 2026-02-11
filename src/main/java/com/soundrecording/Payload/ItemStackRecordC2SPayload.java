package com.soundrecording.Payload;

import com.soundrecording.SoundRecordingMod;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ItemStackRecordC2SPayload(SoundPayload soundPayload, int slotId, int tick) implements CustomPayload {
    public static final Id<ItemStackRecordC2SPayload> ID = new Id<>(Identifier.of(SoundRecordingMod.MOD_ID, "itemstackrecord-payload"));
    public static final PacketCodec<RegistryByteBuf, ItemStackRecordC2SPayload> PACKET_CODEC =
            PacketCodec.tuple(
                    SoundPayload.PACKET_CODEC, ItemStackRecordC2SPayload::soundPayload,
                    PacketCodecs.INTEGER, ItemStackRecordC2SPayload::slotId,
                    PacketCodecs.INTEGER, ItemStackRecordC2SPayload::tick,
                    ItemStackRecordC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
