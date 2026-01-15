package com.soundrecording.Interface;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;

public class MP4PlayerInventory extends SimpleInventory {
    public MP4PlayerInventory(ItemStack... items){
        super(items);
    }

    @Override
    public void markDirty() {
        super.markDirty();

    }
}
