package com.soundrecording.Items;

import com.soundrecording.Componets.MP4PlayerComponent;
import com.soundrecording.Componets.ModComponets;
import com.soundrecording.Interface.ImplementedInventory;
import com.soundrecording.Payload.ItemStackPayload;
import com.soundrecording.Screens.MP4PlayerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class MP4Player extends Item implements ExtendedScreenHandlerFactory<ItemStackPayload>{

    public MP4Player(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type){
        super.appendTooltip(stack, context, tooltip, type);
        int tick = 1;
        tooltip.add(Text.translatable("soundrecording-mod.tick", tick).formatted(Formatting.GOLD));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            MP4PlayerComponent mp4PlayerComponent = user.getStackInHand(hand).get(ModComponets.MP4PLAYER_COMPONENT);
            user.openHandledScreen(this);
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

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
        return new MP4PlayerScreenHandler(syncId, playerInventory, player.getStackInHand(Hand.MAIN_HAND));
    }


}
