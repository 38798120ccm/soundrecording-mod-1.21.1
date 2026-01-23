package com.soundrecording.Items.MP4Player;

public enum MP4PlayerStatus {
    Idle(0), SoundPlaying(1), Recording(2);
    private final int value;

    // Enum constructor must be private or package-private
    private MP4PlayerStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MP4PlayerStatus fromOrdinal(int n) {
        // Basic bounds checking
        if (n >= 0 && n < MP4PlayerStatus.values().length) {
            return MP4PlayerStatus.values()[n];
        }
        throw new IllegalArgumentException("Invalid ordinal: " + n);
    }
}

