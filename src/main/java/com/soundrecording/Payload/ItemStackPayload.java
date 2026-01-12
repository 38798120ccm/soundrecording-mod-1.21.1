package com.soundrecording.Payload;

import com.soundrecording.SoundRecordingMod;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ItemStackPayload(ItemStack itemStack) implements CustomPayload {
    public static final Identifier ItemStack_PAYLOAD_ID = Identifier.of(SoundRecordingMod.MOD_ID, "ItemStackPayload");
    public static final Id<ItemStackPayload> ID = new Id<>(ItemStack_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, ItemStackPayload> CODEC =
            PacketCodec.tuple(ItemStack.PACKET_CODEC, ItemStackPayload::itemStack, ItemStackPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return null;
    }
}
