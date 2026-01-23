package com.soundrecording.Items.MP4Player;

import com.soundrecording.Items.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class MP4PlayerSlot extends Slot { ;

    public MP4PlayerSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    // This method determines if an item can be put into the slot
    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isOf(ModItems.MICROSD); // Only allows the specified item type
    }
}
