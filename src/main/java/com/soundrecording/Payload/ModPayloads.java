package com.soundrecording.Payload;

import com.soundrecording.Componets.*;
import com.soundrecording.SoundRecordingMod;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModPayloads {
    public static void initialize() {
        PayloadTypeRegistry.playC2S().register(ItemStackRecordC2SPayload.ID, ItemStackRecordC2SPayload.PACKET_CODEC);

        RegisterItemStackRecordC2SPayload();
    }

    static void RegisterItemStackRecordC2SPayload(){
        ServerPlayNetworking.registerGlobalReceiver(ItemStackRecordC2SPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                SoundPayload soundPayload = payload.soundPayload();
                ServerPlayerEntity player = context.player();
                ItemStack mp4Stack = player.getInventory().getStack(payload.slotId());
                ItemStack sdStack = mp4Stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack();
                sdStack.set(ModComponents.RECORDING_COMPONENT, recordSound2Component(
                        sdStack.get(ModComponents.RECORDING_COMPONENT), soundPayload.soundId(),
                        soundPayload.pos(), soundPayload.dir(),
                        payload.tick(), soundPayload.volume(), soundPayload.pitch()
                ));

                mp4Stack.set(ModComponents.ITEMSTACK_COMPONENT, new ItemStackComponent(sdStack));
                //SoundRecordingMod.LOGGER.info("ItemStackRecordC2SPayload recorded");
            });
        });
    }

    static RecordingComponent recordSound2Component(RecordingComponent rc, Identifier identifier,
                                             PositionPayload pos, DirectionPayload dir, int tick, double volume, double pitch){
        if(rc == null){
            rc = new RecordingComponent();
        }

        ArrayList<Identifier> id = new ArrayList<>(rc.identifiers());
        ArrayList<PositionComponent> poslist = new ArrayList<>(rc.pos());
        ArrayList<DirectionComponent> dirlist = new ArrayList<>(rc.dir());
        ArrayList<Integer> ticklist = new ArrayList<>(rc.tick());
        ArrayList<Double> volumelist = new ArrayList<>(rc.volume());
        ArrayList<Double> pitchlist = new ArrayList<>(rc.pitch());

        id.add(identifier);
        poslist.add(pos.toComponent());
        dirlist.add(dir.toComponent());
        ticklist.add(tick);
        volumelist.add(volume);
        pitchlist.add(pitch);

        return new RecordingComponent(id, poslist, dirlist, ticklist, volumelist, pitchlist, rc.size()+1);
    }
}


