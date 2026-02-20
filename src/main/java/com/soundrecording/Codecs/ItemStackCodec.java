package com.soundrecording.Codecs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.soundrecording.SoundRecordingMod;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ItemStackCodec(ItemStack itemStack) implements CustomPayload{

    public static final Codec<ItemStackCodec> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    ItemStack.CODEC.fieldOf("itemStack").forGetter(ItemStackCodec::itemStack)
            ).apply(builder, ItemStackCodec::new)
    );

    public static final CustomPayload.Id<ItemStackCodec> ID = new CustomPayload.Id<>(Identifier.of(SoundRecordingMod.MOD_ID, "itemstack-payload"));

    public static final PacketCodec<RegistryByteBuf, ItemStackCodec> PACKET_CODEC =
            PacketCodec.tuple(ItemStack.PACKET_CODEC, ItemStackCodec::itemStack, ItemStackCodec::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
