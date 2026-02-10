package com.soundrecording.Items;

import com.soundrecording.Componets.ModComponents;
import com.soundrecording.Componets.TestComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class TestItem extends Item {
    public TestItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        TestComponent testComponent = stack.get(ModComponents.TEST_COMPONENT);
        for(int i=0; i< testComponent.intListTest().size(); i++){
            tooltip.add(Text.translatable(String.valueOf(testComponent.intListTest().get(i))));
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        TestComponent testComponent = user.getStackInHand(hand).get(ModComponents.TEST_COMPONENT);
        user.getStackInHand(hand).set(ModComponents.TEST_COMPONENT, new TestComponent(testComponent.intListTest(), testComponent.intTest()+1));
        TestComponent newtestComponent = user.getStackInHand(hand).get(ModComponents.TEST_COMPONENT);
        newtestComponent.intListTest().add(newtestComponent.intTest());
        return super.use(world, user, hand);
    }
}
