package com.soundrecording.Items;
import com.soundrecording.Componets.MP4PlayerComponent;
import com.soundrecording.Componets.ModComponets;
import com.soundrecording.Componets.TestComponent;
import com.soundrecording.Items.MP4Player.MP4Player;
import com.soundrecording.SoundRecordingMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;


public class ModItems {
    public static final Item TESTITEM = register("test_item", new TestItem(new Item.Settings().component(
            ModComponets.TEST_COMPONENT, new TestComponent(new ArrayList<>(), 0)
    )));
    public static final Item MICROPHONE = register("microphone", new Microphone(new Microphone.Settings().maxCount(1)));
    public static final Item MP4PLAYER = register("mp4player", new MP4Player(new MP4Player.Settings().maxCount(1).component(
            ModComponets.MP4PLAYER_COMPONENT, new MP4PlayerComponent(ItemStack.EMPTY, 0, 0)
    )));
    public static final Item MICROSD = register("microsd", new MicroSD(new MicroSD.Settings()));

    public static Item register(String id, Item item){
        // Create the identifier for the item.
        Identifier itemID = Identifier.of(SoundRecordingMod.MOD_ID, id);

        // Create the item instance.
        return Registry.register(Registries.ITEM, itemID, item);
    }

    public static final RegistryKey<ItemGroup> SOUND_RECORDING_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(SoundRecordingMod.MOD_ID, "item_group"));
    public static final ItemGroup SOUND_RECORDING_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.MICROPHONE))
            .displayName(Text.translatable("itemGroup.soundrecording-mod"))
            .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP,SOUND_RECORDING_ITEM_GROUP_KEY, SOUND_RECORDING_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(SOUND_RECORDING_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.TESTITEM);
            itemGroup.add(ModItems.MICROPHONE);
            itemGroup.add(ModItems.MP4PLAYER);
            itemGroup.add(ModItems.MICROSD);
        });
    }
}
