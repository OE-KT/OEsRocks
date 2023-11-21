package com.oekt.oerocks.items.custom;

import com.oekt.oerocks.entitty.ThrowableRock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Rock extends Item {
    public ThrowableRock.Rocktype rocktype = ThrowableRock.Rocktype.NORM;
    public int damege = 5;
    public int cooldown = 20;

    public Rock(Properties prop) {

        super(prop);
    }

    public int getDamege() {
        return damege;
    }
    public ThrowableRock.Rocktype getType() {
        return rocktype;
    }
    public Rock setDamge(int dmg) {
        this.damege = dmg;
        return this;
    }
    public Rock setType(ThrowableRock.Rocktype type) {
        this.rocktype = type;
        return this;
    }
    public Rock setCooldown(int cooldown) {
        this.cooldown = cooldown;
        return this;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (player.getRandom().nextFloat() * 0.4F + 0.8F));
        player.getCooldowns().addCooldown(this, cooldown);

        if (!level.isClientSide) {

            ThrowableRock rock = new ThrowableRock(level, player);
            rock.type = rocktype;
            rock.damege = damege;

            rock.setItem(itemstack);
            rock.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 0.7F, 1.0F);

            level.addFreshEntity(rock);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }



}
