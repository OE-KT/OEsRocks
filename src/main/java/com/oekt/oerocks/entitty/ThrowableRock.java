package com.oekt.oerocks.entitty;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.items.ModItems;
import com.oekt.oerocks.items.custom.Rock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
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
    int fire = 0;
   int ice = 0;

   int perjectiols = 0;
   int baseDamege = 0;

   double speed = 1f;
   int bouceniss = 0;

   int timesBonged = 0;
   int maxBonged = 5;
    boolean boucesed = false;

   boolean dupacict = false;

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
        double Damage = baseDamege*(this.getItem().getOrCreateTag().getDouble("power")+1*this.speed);
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
        if(dupacict) {super.onAddedToWorld();}
        ItemStack itemStack = this.getItem();
        CompoundTag nbt = itemStack.getOrCreateTag();
        baseDamege = nbt.getInt("base");
        fire = nbt.getInt("fire");
        ice = nbt.getInt("ice");
        bouceniss = nbt.getInt("bounce");
        //perjectiols = nbt.getInt("rockP");

        /*if(perjectiols > 1) {
            nbt.getUUID("player-uuid");
            Player player = level().getPlayerByUUID(nbt.getUUID("playeruuid"));
            for (int i = 0; i < perjectiols; i++) {
                ThrowableRock rock = new ThrowableRock(this.level(), player);
                rock.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, 1.2f, 1.0F);
                level().addFreshEntity(rock);

            }
        }*/


        super.onAddedToWorld();
    }

    public void typeHit(HitResult result, Rock rockCc) {
            if(bouceniss > 1 && timesBonged < maxBonged) {
                Vec3 vec = this.getForward().subtract(this.getUpVector(1)).normalize().multiply(bouceniss, bouceniss, bouceniss);
                Vec3 newVec = vec.multiply(this.getDeltaMovement().reverse());
               this.setDeltaMovement(newVec.multiply(0.3, 0.3, 0.3));

                timesBonged++;
            } else {
                this.discard();
            }

//        if(rockCc.getRockType()!=null) {
//            switch(rockCc.getRockType()) {
//                case FIRE:
//                    this.discard();
//                    break;
//                case ICE:
//                    //this.setDeltaMovement(this.getDeltaMovement().multiply(-0.7, -0.7, -0.7));
//
//                    this.discard();
//                    break;
//                default:
//                    this.discard();
//                    break;
//            }
//        }

    }

    public void EntittytypeHit(EntityHitResult result, Rock rockCc) {
        Entity entity = result.getEntity();
        entity.setSecondsOnFire(fire);
        entity.setTicksFrozen(ice*100);
//        for (int i = 0; i < perjectiols; i++) {
//            ThrowableRock rock = new ThrowableRock(this.level());
//            rock.perjectiols = perjectiols;
//            rock.baseDamege = baseDamege;
//            rock.dupacict = true;
//            rock.speed = speed+0.5f;
//
//            Vec3 derection = entity.position().vectorTo(rock.position());
//            rock.setPos(entity.position().offsetRandom(RandomSource.create(), 5));
//
//
//            rock.shoot((float) derection.x, (float) derection.y, (float) derection.z, 0f, 2F+speed);
//            level().addFreshEntity(rock);
//        }





//        if(rockCc.getRockType()!=null) {
//            switch(rockCc.getRockType()) {
//                case FIRE:
//                    entity.setSecondsOnFire(10);
//                    break;
//                case ICE:
//                    entity.setTicksFrozen(300);
//
//
//                    /*if(entity instanceof Skeleton skeleton) {
//                        ItemStack stack = ((Skeleton) entity).getItemInHand(InteractionHand.MAIN_HAND);
//                        if(stack.getItem() == Items.BOW) {
//                            //OErocks.LOGGER.info("That Skelton has a bow!");
//                            skeleton.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
//                        }
//                    }*/
//
//                    break;
//                default:
//                    break;
//            }
//        }

    }

    @Override
    public void tick() {
        speed = this.getDeltaMovement().length();

        super.tick();
    }
}
