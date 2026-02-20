package com.soundrecording.Events;

import com.soundrecording.Codecs.DirectionCodec;
import com.soundrecording.Codecs.PositionCodec;
import com.soundrecording.Codecs.SoundCodec;
import com.soundrecording.Componets.ModComponents;
import com.soundrecording.Items.MP4Player.MP4PlayerStatus;
import com.soundrecording.Items.ModItems;
import com.soundrecording.Payload.ItemStackRecordC2SPayload;
import com.soundrecording.SoundRecordingMod;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundInstanceListener;
import net.minecraft.client.sound.WeightedSoundSet;
import net.minecraft.item.ItemStack;

public class SoundListener implements SoundInstanceListener {
    @Override
    public void onSoundPlayed(SoundInstance sound, WeightedSoundSet soundSet, float range){
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if(player == null){return;}
        if(player.getInventory().contains(stack -> stack.isOf(ModItems.MP4PLAYER))){
            for (int i = 0; i < player.getInventory().size(); i++) {
                ItemStack stack = player.getInventory().getStack(i);
                if(!stack.isOf(ModItems.MP4PLAYER)){continue;}
                if(stack.get(ModComponents.STATUS_COMPONENT).status() != MP4PlayerStatus.Recording.ordinal()){continue;}
                if(stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack() == ItemStack.EMPTY){continue;}
                sendSoundPayloadC2S(sound, player, i, stack);
            }
        }
    }

    void sendSoundPayloadC2S(SoundInstance sound, ClientPlayerEntity player, int slotId, ItemStack stack){
        ItemStackRecordC2SPayload payload = new ItemStackRecordC2SPayload(
                new SoundCodec(sound.getId(), sound.getSound().getIdentifier(), sound.getVolume(), sound.getPitch(),
                        sound.getSound().getRegistrationType().name(), sound.getSound().isStreamed(), sound.getSound().getAttenuation()),
                new PositionCodec(sound.getX() - player.getX(), sound.getY() - player.getY(), sound.getZ() - player.getZ()),
                new DirectionCodec(player.getYaw(), player.getPitch()), slotId, stack.get(ModComponents.TICK_COMPONENT).tick());
        ClientPlayNetworking.send(payload);
    }
}
