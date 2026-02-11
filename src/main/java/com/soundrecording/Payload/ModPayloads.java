package com.soundrecording.Payload;

import com.soundrecording.Componets.ModComponents;
import com.soundrecording.Componets.RecordingComponent;
import com.soundrecording.SoundRecordingMod;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModPayloads {
    public static void initialize() {
        PayloadTypeRegistry.playC2S().register(ItemStackRecordC2SPayload.ID, ItemStackRecordC2SPayload.PACKET_CODEC);

        RegisterItemStackRecordC2SPayload();
    }

    static void RegisterItemStackRecordC2SPayload(){
        ServerPlayNetworking.registerGlobalReceiver(ItemStackRecordC2SPayload.ID, (payload, context) -> {
            SoundPayload soundPayload = payload.soundPayload();
            payload.itemStack().set(ModComponents.RECORDING_COMPONENT, recordSound2Component(
                    payload.itemStack().get(ModComponents.RECORDING_COMPONENT), soundPayload.soundId(),
                    soundPayload.x(), soundPayload.y(), soundPayload.z(),
                    payload.tick(), soundPayload.volume(), soundPayload.pitch()
            ));
            SoundRecordingMod.LOGGER.info("ItemStackRecordC2SPayload recorded");
        });
    }

    static RecordingComponent recordSound2Component(RecordingComponent rc, Identifier identifier,
                                             double x, double y, double z, int tick, double volume, double pitch){
        if(rc == null){
            rc = new RecordingComponent();
        }

        ArrayList<Identifier> id = new ArrayList<>(rc.identifiers());
        ArrayList<Double> xlist = new ArrayList<>(rc.x());
        ArrayList<Double> ylist = new ArrayList<>(rc.y());
        ArrayList<Double> zlist = new ArrayList<>(rc.z());
        ArrayList<Integer> ticklist = new ArrayList<>(rc.tick());
        ArrayList<Double> volumelist = new ArrayList<>(rc.volume());
        ArrayList<Double> pitchlist = new ArrayList<>(rc.pitch());

        id.add(identifier);
        xlist.add(x);
        ylist.add(y);
        zlist.add(z);
        ticklist.add(tick);
        volumelist.add(volume);
        pitchlist.add(pitch);

        return new RecordingComponent(id, xlist, ylist, zlist, ticklist, volumelist, pitchlist, rc.size()+1);
    }
}


