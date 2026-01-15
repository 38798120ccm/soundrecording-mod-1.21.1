package com.soundrecording.Screens;

import com.soundrecording.Items.MP4Player;
import com.soundrecording.Payload.ItemStackPayload;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class MP4PlayerScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    // Client Constructor
    public MP4PlayerScreenHandler(int synvId, PlayerInventory playerInventory, ItemStackPayload payload){
        this(synvId, playerInventory, (MP4Player) payload.itemStack().getItem());
    }

    // Server Constructor
    public MP4PlayerScreenHandler(int syncId, PlayerInventory playerInventory, MP4Player mp4Player) {
        super(ModScreenHandler.MP4PLAYER_SCREEN_HANDLER, syncId);
        this.inventory = (Inventory) mp4Player;

        this.addSlot(new Slot(inventory, 0, 80, 35));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if(slot != null && slot.hasStack()){
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if(invSlot < this.inventory.size()){
                if(!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)){
                    return ItemStack.EMPTY;
                }
            } else if(!this.insertItem(originalStack,0, this.inventory.size(), false)){
                return ItemStack.EMPTY;
            }

            if(originalStack.isEmpty()){
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory){
        for(int i=0; i<3; ++i){
            for(int l=0; l<9; ++l){
                this.addSlot(new Slot(playerInventory, l+i*9+9, 8+l*18, 84+i*18));
            }
        }
    }
    private void addPlayerHotbar(PlayerInventory playerInventory){
        for(int i=0; i<9; ++i){
            this.addSlot(new Slot(playerInventory, i, 8+i*18, 142));
        }
    }
}
