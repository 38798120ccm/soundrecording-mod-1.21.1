package com.soundrecording.SoundInstance;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.sound.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

public class MP4PlayerSoundInstance extends AbstractSoundInstance {
//    public final static Codec<MP4PlayerSoundInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
//
//    ).apply(instance, MP4PlayerSoundInstance::new));

    public MP4PlayerSoundInstance(Identifier id, float volume, float pitch, Sound sound, boolean repeat, int repeatDelay, SoundInstance.AttenuationType attenuationType, double x, double y, double z, boolean relative) {
        super(id, SoundCategory.MASTER, SoundInstance.createRandom());
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        this.x = x;
        this.y = y;
        this.z = z;
        this.repeat = repeat;
        this.repeatDelay = repeatDelay;
        this.attenuationType = attenuationType;
        this.relative = relative;
    }

    @Override
    public WeightedSoundSet getSoundSet(SoundManager soundManager){
        if (this.id.equals(SoundManager.INTENTIONALLY_EMPTY_ID)) {
            this.sound = SoundManager.INTENTIONALLY_EMPTY_SOUND;
            return SoundManager.INTENTIONALLY_EMPTY_SOUND_SET;
        } else {
            WeightedSoundSet weightedSoundSet = soundManager.get(this.id);
            if (weightedSoundSet == null) {
                this.sound = SoundManager.MISSING_SOUND;
            }
            return weightedSoundSet;
        }
    }
}
