package com.soundrecording.Items.MP4Player;

import com.soundrecording.Componets.*;
import com.soundrecording.Items.ModItems;
import com.soundrecording.Payload.ItemStackPayload;
import com.soundrecording.Screens.MP4PlayerScreenHandler;
import com.soundrecording.SoundRecordingMod;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.client.sound.Sound;
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
            TickComponent tickComponent = itemStack.get(ModComponents.TICK_COMPONENT);
            ItemStackComponent itemStackComponent = itemStack.get(ModComponents.ITEMSTACK_COMPONENT);
            StatusComponent statusComponent = itemStack.get(ModComponents.STATUS_COMPONENT);
            if(user.isSneaking()) {
                    user.openHandledScreen(this);
            }
            else if(itemStackComponent.itemStack().contains(ModComponents.TICK_COMPONENT)){
                if(statusComponent.status() == MP4PlayerStatus.Recording.ordinal()){
                    itemStack.set(ModComponents.STATUS_COMPONENT, new StatusComponent(MP4PlayerStatus.Idle.ordinal()));
                    itemStack.set(ModComponents.TICK_COMPONENT, new TickComponent(0));
                    itemStackComponent.itemStack().set(ModComponents.TICK_COMPONENT, new TickComponent(tickComponent.tick()));
                }
                else if(statusComponent.status() == MP4PlayerStatus.Idle.ordinal()){
                    itemStack.set(ModComponents.STATUS_COMPONENT, new StatusComponent(MP4PlayerStatus.SoundPlaying.ordinal()));
                    itemStack.set(ModComponents.TICK_COMPONENT, new TickComponent(0));
                }
                else if(statusComponent.status() == MP4PlayerStatus.SoundPlaying.ordinal()){
                    itemStack.set(ModComponents.STATUS_COMPONENT, new StatusComponent(MP4PlayerStatus.Idle.ordinal()));
                    itemStack.set(ModComponents.TICK_COMPONENT, new TickComponent(0));
                }
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(stack.contains(ModComponents.ITEMSTACK_COMPONENT)){
            StatusComponent statusComponent = stack.get(ModComponents.STATUS_COMPONENT);
            TickComponent tickComponent = stack.get(ModComponents.TICK_COMPONENT);
            int nexttick = tickComponent.tick() + 1;
            if(statusComponent.status() == MP4PlayerStatus.Recording.ordinal()){
                stack.set(ModComponents.TICK_COMPONENT, new TickComponent(nexttick));
                //SoundRecordingMod.LOGGER.info("inventoryTick-recording");
            }
            else if(statusComponent.status() == MP4PlayerStatus.SoundPlaying.ordinal()){
                if(tickComponent.tick() >= stack.get(ModComponents.ITEMSTACK_COMPONENT).itemStack().get(ModComponents.TICK_COMPONENT).tick()){
                    stack.set(ModComponents.TICK_COMPONENT, new TickComponent(0));
                }
                else stack.set(ModComponents.TICK_COMPONENT, new TickComponent(nexttick));
                //SoundRecordingMod.LOGGER.info("inventoryTick-soundplaying");
            }
        }
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
