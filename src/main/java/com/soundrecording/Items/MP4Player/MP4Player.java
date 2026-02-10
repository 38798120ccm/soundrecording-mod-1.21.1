package com.soundrecording.Items.MP4Player;

import com.soundrecording.Componets.MP4PlayerComponent;
import com.soundrecording.Componets.ModComponents;
import com.soundrecording.Componets.StatusComponent;
import com.soundrecording.Componets.TickComponent;
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
        if(stack.contains(ModComponents.STATUS_COMPONENT)){
            StatusComponent statusComponent = stack.get(ModComponents.STATUS_COMPONENT);
            String key = "";
            if(MP4PlayerStatus.Idle.ordinal() == statusComponent.status()){key = "Mode: Idle";}
            if(MP4PlayerStatus.SoundPlaying.ordinal() == statusComponent.status()){key = "Mode: SoundPlaying";}
            if(MP4PlayerStatus.Recording.ordinal() == statusComponent.status()){key = "Mode: Recording";}
            tooltip.add(Text.translatable(key).formatted(Formatting.GOLD));
        }

        if(stack.contains(ModComponents.TICK_COMPONENT)) {
            TickComponent tickComponent = stack.get(ModComponents.TICK_COMPONENT);
            int tick = tickComponent.tick()/20;
            tooltip.add(Text.translatable("soundrecording-mod.tick", tick).formatted(Formatting.GOLD));
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(Hand.MAIN_HAND);

        if (!world.isClient()) {
            if(user.isSneaking()) {
                    user.openHandledScreen(this);
            }
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

    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}
