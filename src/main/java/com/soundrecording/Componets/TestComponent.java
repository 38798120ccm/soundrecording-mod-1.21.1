package com.soundrecording.Componets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;

import java.util.List;

public record TestComponent(List<Integer> intListTest, int intTest) {
    public static final Codec<TestComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.INT.listOf().fieldOf("intList").forGetter(TestComponent::intListTest),
                    Codec.INT.fieldOf("size").forGetter(TestComponent::intTest)
            ).apply(builder, TestComponent::new)
    );
}
