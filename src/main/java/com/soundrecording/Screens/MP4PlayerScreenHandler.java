package com.soundrecording.Screens;

import com.soundrecording.Items.MP4Player;
import com.soundrecording.Payload.ItemStackPayload;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

//public class MP4PlayerScreenHandler extends ScreenHandler {
//    private final MP4Player mp4Player;
//
//    // Client Constructor
//    public MP4PlayerScreenHandler(int synvId, PlayerInventory playerInventory, ItemStackPayload payload){
//        this(synvId, playerInventory, (MP4Player) playerInventory.player.getMainHandStack().getItem());
//    }
//
//    // Server Constructor
//    public MP4PlayerScreenHandler(int syncId, PlayerInventory playerInventory, MP4Player mp4Player) {
//        super(type, syncId);
//        this.mp4Player = mp4Player;
//
//    }
//
//    @Override
//    public ItemStack quickMove(PlayerEntity player, int slot) {
//        return ItemStack.EMPTY;
//    }
//
//    @Override
//    public boolean canUse(PlayerEntity player) {
//        return true;
//    }
//}
