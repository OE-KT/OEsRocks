package com.oekt.oerocks.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CopperHammer extends Hammer{
    public CopperHammer(float p_204108_, float p_204109_, Tier p_204110_, Properties p_204112_) {
        super(p_204108_, p_204109_, p_204110_, p_204112_);
    }
    float timer = 200;


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.success(itemInHand);
    }
    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int p_41415_) {
        Strike(level, entity);
        super.releaseUsing(itemStack, level, entity, p_41415_);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.SPEAR;
    }


//    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
//        //RandomSource rand = RandomSource.create();
//        if(level.isRainingAt(player.blockPosition())) {
//            timer -= 1;
//            if(timer < 0) {
//                Strike(level, player);
//            }
//        }
//        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
//    }
    public void Strike(Level level, LivingEntity entity) {
        LightningBolt lighting = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lighting.setDamage(99);
        lighting.setPos(entity.position());
        level.addFreshEntity(lighting);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> componentList, TooltipFlag tooltipFlag) {

        componentList.add(Component.translatable("Try holding it in the air for a supprise! \n hold right click to raise").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, componentList, tooltipFlag);
    }
}
