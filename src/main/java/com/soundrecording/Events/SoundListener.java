package com.soundrecording.Events;

import com.soundrecording.Componets.ModComponents;
import com.soundrecording.Items.MP4Player.MP4PlayerStatus;
import com.soundrecording.Items.ModItems;
import com.soundrecording.Payload.ItemStackRecordC2SPayload;
import com.soundrecording.Payload.SoundPayload;
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
            for (ItemStack stack : player.getInventory().main) {
                if(!stack.isOf(ModItems.MP4PLAYER)){continue;}
                if(stack.get(ModComponents.STATUS_COMPONENT).status() != MP4PlayerStatus.Recording.ordinal()){continue;}
                if(stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack() == ItemStack.EMPTY){continue;}
                ItemStackRecordC2SPayload itemStackRecordC2SPayload =
                        new ItemStackRecordC2SPayload(
                                new SoundPayload(sound.getId(), sound.getVolume(), sound.getPitch(),
                                        sound.getX(), sound.getY(), sound.getZ()),
                                stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack(), stack.get(ModComponents.TICK_COMPONENT).tick()
                        );
                ClientPlayNetworking.send(itemStackRecordC2SPayload);



//                stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack().set(ModComponents.RECORDING_COMPONENT,
//                        recordSound2Component(
//                        stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack().get(ModComponents.RECORDING_COMPONENT),
//                        sound.getId(), sound.getX(), sound.getY(), sound.getZ(),
//                        stack.get(ModComponents.TICK_COMPONENT).tick(),
//                        sound.getVolume(), sound.getPitch(), sound.isRepeatable(),
//                        sound.getRepeatDelay(), (sound.getAttenuationType() == SoundInstance.AttenuationType.LINEAR)? 1: 0, sound.isRelative()
//                ));
//                //SoundRecordingMod.LOGGER.info("x: " + String.valueOf(sound.getX()) + "y: " + String.valueOf(sound.getY()));
            }
        }
    }
}
