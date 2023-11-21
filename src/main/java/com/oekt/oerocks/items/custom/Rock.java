package com.oekt.oerocks.items.custom;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.entitty.ThrowableRock;
import net.minecraft.nbt.CompoundTag;
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
import org.jetbrains.annotations.NotNull;

public class Rock extends Item {
    public ThrowableRock.Rocktype rocktype;
    private int damege = 5;
    private int cooldown = 20;

    boolean Saved = false;
    public Rock(Properties prop, int damege, ThrowableRock.Rocktype rocktype, int cooldown) {
        super(prop);
        this.damege = damege;
        this.rocktype = rocktype;
        this.cooldown = cooldown;



    }

    public int getDamege() {
        return this.damege;
    }
    public ThrowableRock.Rocktype getRockType() {
        return this.rocktype;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {


        ItemStack itemstack = player.getItemInHand(hand);

        level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (player.getRandom().nextFloat() * 0.4F + 0.8F));
        player.getCooldowns().addCooldown(this, cooldown);

        if (!level.isClientSide) {

            ThrowableRock rock = new ThrowableRock(level, player);


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


    /*@Override
    public @NotNull ItemStack getDefaultInstance() {
        //OErocks.LOGGER.info(Saved + " Item Stack: " + this.getDefaultInstance());
        ItemStack stack = super.getDefaultInstance();

        stack.getOrCreateTag().putInt("damege", damege);
        stack.getOrCreateTag().putInt("type", rocktype.ordinal());
        return stack;
    }*/
}
