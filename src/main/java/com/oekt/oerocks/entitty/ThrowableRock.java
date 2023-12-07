package com.oekt.oerocks.entitty;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.items.ModItems;
import com.oekt.oerocks.items.custom.Rock;
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
import net.minecraftforge.common.Tags;

import java.util.HashMap;
import java.util.Map;

public class ThrowableRock extends ThrowableItemProjectile {

   public enum Rocktype {
        NORM(0),
        FIRE(1),
        ICE(2);
       public final int typeV;

       private Rocktype(int value)
       {
           typeV = value;
       }

       private static final Map<Integer, Rocktype> _map = new HashMap<Integer, Rocktype>();
       static
       {
           for (Rocktype type : Rocktype.values())
               _map.put(type.typeV, type);
       }


       public static Rocktype from(int value)
       {
           return _map.get(value);
       }
    }


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
        if(!(this.getItem().getItem() instanceof Rock rockCc)) {
            return;
        }

        Entity entity = result.getEntity();
        OErocks.LOGGER.info(rockCc.getRockType().toString());
        double Damage = rockCc.getDamege()*(this.getItem().getOrCreateTag().getDouble("power")+1);
        entity.hurt(this.damageSources().thrown(this, this.getOwner()),(float) Damage); // Detirms behavior onHit
        EntittytypeHit(result, rockCc);
//        if(rockCc.getRockType()!=null) {
//            switch(rockCc.getRockType()) {
//                case FIRE:
//                    entity.setSecondsOnFire(10);
//                    break;
//                case ICE:
//                    entity.setTicksFrozen(300);
//                    break;
//                default:
//                    break;
//            }
//        }


        super.onHitEntity(result);
    }


    @Override
    protected void onHit(HitResult result) {
        if(!(this.getItem().getItem() instanceof Rock rockCc)) {
            return;
        }
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            typeHit(result, rockCc);


        }


    }

    @Override
    public void onAddedToWorld() {

        super.onAddedToWorld();
    }

    public void typeHit(HitResult result, Rock rockCc) {
        if(rockCc.getRockType()!=null) {
            switch(rockCc.getRockType()) {
                case FIRE:
                    this.discard();
                    break;
                case ICE:
                    //this.setDeltaMovement(this.getDeltaMovement().multiply(-0.7, -0.7, -0.7));
                    this.discard();
                    break;
                default:
                    this.discard();
                    break;
            }
        }

    }
    public void EntittytypeHit(EntityHitResult result, Rock rockCc) {

        Entity entity = result.getEntity();
        if(rockCc.getRockType()!=null) {
            switch(rockCc.getRockType()) {
                case FIRE:
                    entity.setSecondsOnFire(10);
                    break;
                case ICE:
                    entity.setTicksFrozen(300);
                    break;
                default:
                    break;
            }
        }

    }



}
