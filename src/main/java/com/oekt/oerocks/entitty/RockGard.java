package com.oekt.oerocks.entitty;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;

import java.util.jar.Attributes;

public class RockGard extends Monster {
    public RockGard(EntityType<? extends Monster> EntityType, Level Level) {
        super(EntityType, Level);
    }


    public RockGard(Level level, double x, double y, double z) {
        this(ModEnittys.ROCK_GARD_ENITTY.get(),level);
        setPos(x, y, z);

    }
    public RockGard(Level level, BlockPos blockPos) {
        this(level, blockPos.getX(), blockPos.getY(), blockPos.getZ());


    }




    public RockGard(Level level, LivingEntity entity) {
        super(ModEnittys.ROCK_GARD_ENITTY.get(), level);

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttrabuits() {
        return Skeleton.createAttributes();
    }
}
