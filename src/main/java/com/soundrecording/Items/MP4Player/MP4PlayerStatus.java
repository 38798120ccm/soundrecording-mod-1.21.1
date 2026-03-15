package com.soundrecording.Items.MP4Player;

import net.minecraft.util.math.MathHelper;

public enum MP4PlayerStatus {
    Idle, Loop, PlayMode, Recording;

    public static MP4PlayerStatus fromInt(int index) {
        int safeIndex = MathHelper.clamp(index, 0, values().length - 1);
        return values()[safeIndex];
    }
}

