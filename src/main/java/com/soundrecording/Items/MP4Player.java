package com.soundrecording.Items;

import com.soundrecording.Interface.ImplementedInventory;
import com.soundrecording.Payload.ItemStackPayload;
import com.soundrecording.Screens.MP4PlayerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MP4Player extends Item implements ImplementedInventory, ExtendedScreenHandlerFactory<ItemStackPayload>{
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public MP4Player(Settings settings) {
        super(settings);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type){
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            user.openHandledScreen(this);
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public ItemStackPayload getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        ItemStack itemStack = serverPlayerEntity.getStackInHand(Hand.MAIN_HAND);
        return new ItemStackPayload(itemStack);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("MP4Player");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new MP4PlayerScreenHandler(syncId, playerInventory, this);
    }


}
