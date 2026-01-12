package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public record MP4PlayerComponent(ItemStack itemStack, int tick, int status) {

    public static final Codec<MP4PlayerComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    ItemStack.CODEC.fieldOf("microSD").forGetter(MP4PlayerComponent::itemStack),
                    Codec.INT.fieldOf("tick").forGetter(MP4PlayerComponent::tick),
                    Codec.INT.fieldOf("status").forGetter(MP4PlayerComponent::status)
            ).apply(builder, MP4PlayerComponent::new)
    );

    public ItemStack getItem(){
        return itemStack;
    }

    public ActionResult startRecording(){
        return ActionResult.SUCCESS;
    }

    public ActionResult stopRecording(){
        return ActionResult.SUCCESS;
    }

    public ActionResult openMenu(){
        return ActionResult.SUCCESS;
    }

    public ActionResult loadRecording(){
        return ActionResult.SUCCESS;
    }

    public ActionResult stopPlayingRecording(){
        return ActionResult.SUCCESS;
    }

}
