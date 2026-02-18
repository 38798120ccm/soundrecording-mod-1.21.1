package com.soundrecording.SoundInstance;

import com.soundrecording.Componets.DirectionComponent;
import com.soundrecording.Componets.PositionComponent;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public class PlayerFollowingSoundInstance extends MovingSoundInstance {

    private final LivingEntity entity;
    private final PositionComponent pos;
    private final DirectionComponent dir;
    private final boolean Isdirectional;
    public PlayerFollowingSoundInstance(LivingEntity entity, SoundEvent soundEvent, SoundCategory soundCategory, PositionComponent pos,
                                        DirectionComponent dir, float volume, float pitch, boolean Isdirectional) {
        super(soundEvent, soundCategory, SoundInstance.createRandom());
        this.entity = entity;
        this.volume = volume;
        this.pitch = pitch;
        this.repeat = false;
        this.pos = pos;
        this.dir = dir;
        this.Isdirectional = Isdirectional;
        this.setPositionToEntity();
    }

    @Override
    public void tick() {
        if (this.entity == null || this.entity.isRemoved() || this.entity.isDead()) {
            this.setDone();
            return;
        }
        this.setPositionToEntity();
    }

    @Override
    public boolean shouldAlwaysPlay() {
        return true;
    }

    private void setPositionToEntity() {
        double diff = Math.toRadians(entity.getYaw() - dir.yaw());
        this.x = this.entity.getX() + (Isdirectional? pos.x() * Math.cos(diff) - pos.z() * Math.sin(diff):pos.x());
        this.y = this.entity.getY() + pos.y();
        this.z = this.entity.getZ() + (Isdirectional? pos.x() * Math.sin(diff) + pos.z() * Math.cos(diff):pos.z());
    }
}
