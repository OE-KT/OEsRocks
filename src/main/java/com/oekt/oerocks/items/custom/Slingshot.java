package com.oekt.oerocks.items.custom;

import com.oekt.oerocks.entitty.ThrowableRock;
import com.oekt.oerocks.items.ModItems;
import com.oekt.oerocks.util.ModTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Predicate;

public class Slingshot extends ProjectileWeaponItem {
    public static final Predicate<ItemStack> ROCKS_ONLY = (p_43017_) -> {
        return p_43017_.is(ModTags.Items.ROCKS);
    };

    public Slingshot(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ROCKS_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }


    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int p_40670_) {
        if (entity instanceof Player player) {
            ItemStack itemstack = player.getProjectile(stack);
            int time = this.getUseDuration(stack) - p_40670_;

            if(time < 0) { return; }

            if (!itemstack.isEmpty()) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(ModItems.ROCK.get());
                }

            }
            float power=getPowerForTime(time);
            if (!((double)power < 0.1D)) {
                if(!level.isClientSide()) {
                    ThrowableRock rock = new ThrowableRock(level, player);
                    rock.setItem(itemstack);
                    rock.damege = rock.damege*(power+1);
                    rock.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, 1.2f*power, 1.0F);
                    level.addFreshEntity(rock);
                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.DEEPSLATE_HIT, SoundSource.PLAYERS, 1.0F, 1.3f*power);
                }

                player.awardStat(Stats.ITEM_USED.get(this));
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
            }


        }
        super.releaseUsing(stack, level, entity, p_40670_);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 72000;
    }
    public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        ItemStack itemstack = p_40673_.getItemInHand(p_40674_);
        boolean flag = !p_40673_.getProjectile(itemstack).isEmpty();

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, p_40672_, p_40673_, p_40674_, flag);
        if (ret != null) return ret;

        if (!p_40673_.getAbilities().instabuild && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            p_40673_.startUsingItem(p_40674_);
            return InteractionResultHolder.consume(itemstack);
        }
    }
    // From BowItem Class
    public static float getPowerForTime(int p_40662_) {
        float f = (float)p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

}
