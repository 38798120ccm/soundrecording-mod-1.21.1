package com.soundrecording.Events;

import com.soundrecording.Componets.ModComponents;
import com.soundrecording.Componets.RecordingComponent;
import com.soundrecording.Items.MP4Player.MP4PlayerStatus;
import com.soundrecording.Items.ModItems;
import com.soundrecording.SoundRecordingMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundInstanceListener;
import net.minecraft.client.sound.WeightedSoundSet;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

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
                if(!stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack().contains(ModComponents.RECORDING_COMPONENT)){
                    stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack().set(ModComponents.RECORDING_COMPONENT,
                            new RecordingComponent(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                            0));
                }
                stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack().set(ModComponents.RECORDING_COMPONENT,
                        recordSound2Component(
                        stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack().get(ModComponents.RECORDING_COMPONENT),
                        sound.getId(), sound.getX(), sound.getY(), sound.getZ(),
                        stack.get(ModComponents.TICK_COMPONENT).tick(),
                        sound.getVolume(), sound.getPitch(), sound.isRepeatable(),
                        sound.getRepeatDelay(), (sound.getAttenuationType() == SoundInstance.AttenuationType.LINEAR)? 1: 0, sound.isRelative()
                ));
                //SoundRecordingMod.LOGGER.info("x: " + String.valueOf(sound.getX()) + "y: " + String.valueOf(sound.getY()));
            }

        }
    }

    RecordingComponent recordSound2Component(RecordingComponent rc, Identifier identifier,
                                             double x, double y, double z, int tick, double volume, double pitch,
                                             boolean repeat, int repeatdelay, int attenuationtype, boolean relative){
        if(rc == null){
            SoundRecordingMod.LOGGER.info("Returned null in recordSound2Component");
            return null;
        }
        ArrayList<Identifier> id = new ArrayList<>(rc.identifiers());
        ArrayList<Double> xlist = new ArrayList<>(rc.x());
        ArrayList<Double> ylist = new ArrayList<>(rc.y());
        ArrayList<Double> zlist = new ArrayList<>(rc.z());
        ArrayList<Integer> ticklist = new ArrayList<>(rc.tick());
        ArrayList<Double> volumelist = new ArrayList<>(rc.volume());
        ArrayList<Double> pitchlist = new ArrayList<>(rc.pitch());
        ArrayList<Boolean> repeatlist = new ArrayList<>(rc.repeat());
        ArrayList<Integer> repeatDelaylist = new ArrayList<>(rc.repeatDelay());
        ArrayList<Integer> attenuationtypelist = new ArrayList<>(rc.attenuationtype());
        ArrayList<Boolean> relativelist = new ArrayList<>(rc.relative());

        PlayerEntity player = MinecraftClient.getInstance().player;
        id.add(identifier);
        xlist.add(player.getX() - x);
        ylist.add(player.getY() - y);
        zlist.add(player.getZ() - z);
        ticklist.add(tick);
        volumelist.add(volume);
        pitchlist.add(pitch);
        repeatlist.add(repeat);
        repeatDelaylist.add(repeatdelay);
        attenuationtypelist.add(attenuationtype);
        relativelist.add(relative);

        SoundRecordingMod.LOGGER.info("Saved Sound to SDCard");
        return new RecordingComponent(id, xlist, ylist, zlist, ticklist, volumelist, pitchlist,
                repeatlist, repeatDelaylist, attenuationtypelist, relativelist, rc.size()+1);
    }
}
