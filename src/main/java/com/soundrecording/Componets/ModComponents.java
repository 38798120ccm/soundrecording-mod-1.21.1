package com.soundrecording.Componets;

import com.soundrecording.SoundRecordingMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static void initialize(){
        SoundRecordingMod.LOGGER.info("Registering {} components", SoundRecordingMod.MOD_ID);
    }

    public static final ComponentType<RecordingComponent> RECORDING_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(SoundRecordingMod.MOD_ID, "recording_component"),
            ComponentType.<RecordingComponent>builder().codec(RecordingComponent.CODEC).build()
    );

    public static final ComponentType<ItemStackComponent> ITEMSTACK_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(SoundRecordingMod.MOD_ID, "itemstack_component"),
            ComponentType.<ItemStackComponent>builder().codec(ItemStackComponent.CODEC).build()
    );

    public static final ComponentType<TickComponent> TICK_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(SoundRecordingMod.MOD_ID, "tick_component"),
            ComponentType.<TickComponent>builder().codec(TickComponent.CODEC).build()
    );
    public static final ComponentType<StatusComponent> STATUS_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(SoundRecordingMod.MOD_ID, "status_component"),
            ComponentType.<StatusComponent>builder().codec(StatusComponent.CODEC).build()
    );

//    public static final ComponentType<MP4PlayerComponent> MP4PLAYER_COMPONENT = Registry.register(
//            Registries.DATA_COMPONENT_TYPE,
//            Identifier.of(SoundRecordingMod.MOD_ID, "mp4player_component"),
//            ComponentType.<MP4PlayerComponent>builder().codec(MP4PlayerComponent.CODEC).build()
//    );

    public static final ComponentType<TestComponent> TEST_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(SoundRecordingMod.MOD_ID, "test_component"),
            ComponentType.<TestComponent>builder().codec(TestComponent.CODEC).build()
    );
}
