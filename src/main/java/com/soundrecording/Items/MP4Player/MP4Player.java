package com.soundrecording.Items.MP4Player;

import com.soundrecording.Componets.MP4PlayerComponent;
import com.soundrecording.Componets.ModComponets;
import com.soundrecording.Payload.ItemStackPayload;
import com.soundrecording.Screens.MP4PlayerScreenHandler;
import com.soundrecording.SoundRecordingMod;
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
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.List;

//0414125
public class MP4Player extends Item implements ExtendedScreenHandlerFactory<ItemStackPayload>{

    public MP4Player(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type){
        super.appendTooltip(stack, context, tooltip, type);
        if(stack.contains(ModComponets.MP4PLAYER_COMPONENT)) {
            MP4PlayerComponent mp4PlayerComponent = stack.get(ModComponets.MP4PLAYER_COMPONENT);
            int tick = mp4PlayerComponent.tick()/20;
            String key = "";
            if(MP4PlayerStatus.Idle.getValue() == mp4PlayerComponent.status()){key = "Mode: Idle";}
            if(MP4PlayerStatus.SoundPlaying.getValue() == mp4PlayerComponent.status()){key = "Mode: SoundPlaying";}
            if(MP4PlayerStatus.Recording.getValue() == mp4PlayerComponent.status()){key = "Mode: Recording";}
            tooltip.add(Text.translatable(key).formatted(Formatting.GOLD));
            tooltip.add(Text.translatable("soundrecording-mod.tick", tick).formatted(Formatting.GOLD));
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(Hand.MAIN_HAND);
        MP4PlayerComponent mp4PlayerComponent = itemStack.get(ModComponets.MP4PLAYER_COMPONENT);

        if (!world.isClient()) {
            if(mp4PlayerComponent.status() == MP4PlayerStatus.Recording.getValue()){
                itemStack.set(ModComponets.MP4PLAYER_COMPONENT, new MP4PlayerComponent(mp4PlayerComponent.itemStack(), 0, MP4PlayerStatus.Idle.getValue()));
            }
            else if(user.isSneaking()){
                user.openHandledScreen(this);
            }
            else if(mp4PlayerComponent.itemStack() != ItemStack.EMPTY){
                if(mp4PlayerComponent.status() != MP4PlayerStatus.SoundPlaying.getValue()) {
                    itemStack.set(ModComponets.MP4PLAYER_COMPONENT, new MP4PlayerComponent(mp4PlayerComponent.itemStack(), 0, MP4PlayerStatus.SoundPlaying.getValue()));
                }
                else {
                    itemStack.set(ModComponets.MP4PLAYER_COMPONENT, new MP4PlayerComponent(mp4PlayerComponent.itemStack(), 0, MP4PlayerStatus.Idle.getValue()));
                }
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        MP4PlayerComponent mp4PlayerComponent = stack.get(ModComponets.MP4PLAYER_COMPONENT);
        if(mp4PlayerComponent.status() == MP4PlayerStatus.Recording.getValue()){
            stack.set(ModComponets.MP4PLAYER_COMPONENT, new MP4PlayerComponent(mp4PlayerComponent.itemStack(), mp4PlayerComponent.tick()+1, MP4PlayerStatus.Recording.getValue()));
        }
        else if(mp4PlayerComponent.status() == MP4PlayerStatus.SoundPlaying.getValue()){
            stack.set(ModComponets.MP4PLAYER_COMPONENT, new MP4PlayerComponent(mp4PlayerComponent.itemStack(), mp4PlayerComponent.tick()+1, MP4PlayerStatus.SoundPlaying.getValue()));
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
