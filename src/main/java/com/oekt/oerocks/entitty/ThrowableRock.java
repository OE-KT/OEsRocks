package com.oekt.oerocks.entitty;

import com.oekt.oerocks.items.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrowableRock extends ThrowableItemProjectile {
   public float damege = 5.0f;

    public ThrowableRock(EntityType<? extends ThrowableItemProjectile> EntityType, Level Level) {
        super(EntityType, Level);
    }


    public ThrowableRock(Level level) {
        super(ModEnittys.ROCK_ENITTY.get() ,level);

    }

    public ThrowableRock(Level level, LivingEntity entity) {
        super(ModEnittys.ROCK_ENITTY.get(), entity, level);

    }



    @Override
    protected Item getDefaultItem() {
        return ModItems.ROCK.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();

        entity.hurt(this.damageSources().thrown(this, this.getOwner()), damege);
        super.onHitEntity(result);
    }


    @Override
    protected void onHit(HitResult result) {

        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }
}
