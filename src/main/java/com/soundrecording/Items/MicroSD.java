package com.soundrecording.Items;

import com.soundrecording.Componets.ModComponents;
import com.soundrecording.Componets.RecordingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class MicroSD extends Item {
    public MicroSD(Settings settings){
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type){
        super.appendTooltip(stack, context, tooltip, type);
        if (stack.contains(ModComponents.RECORDING_COMPONENT)){
            RecordingComponent recordingComponent = stack.get(ModComponents.RECORDING_COMPONENT);
            int size = recordingComponent.size();
            tooltip.add(Text.translatable(String.valueOf(size)));
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
