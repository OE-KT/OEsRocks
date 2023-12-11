package com.oekt.oerocks.items.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.oekt.oerocks.blocks.ModBlocks;
import com.oekt.oerocks.entitty.ThrowableRock;
import com.oekt.oerocks.items.ModItems;
import com.oekt.oerocks.util.ModTags;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Slingshot extends ProjectileWeaponItem {
    private int LoadedDamge = 0;
    private int LoadedType = 0;
    public static final Predicate<ItemStack> ROCKS_ONLY = (p_43017_) -> {
        return p_43017_.is(ModTags.Items.ROCKS);
    };

    public Slingshot(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                int i = arm == HumanoidArm.RIGHT ? 1 : -1;
                poseStack.translate(i * 0.56F, -0.52F, -0.72F);
                float useTime = itemInHand.getUseDuration();

                if (player.getUseItem() == itemInHand && player.isUsingItem()) {
                    poseStack.translate(-0.5, 0, 0.0);
                    poseStack.mulPose(Axis.YP.rotationDegrees((float)i * 90.0F));
                    float interpetTimeUse = (float)itemInHand.getUseDuration() - ((float)player.getUseItemRemainingTicks() - partialTick + 1.0F);
                    float inbetwwenTimeRotioe = Mth.lerp(interpetTimeUse, 0, 1);
                    System.out.println(inbetwwenTimeRotioe);
                    //poseStack.mulPose(Axis.XP.rotationDegrees(10.0F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees((float)i * Mth.clamp(inbetwwenTimeRotioe, 0, 30)));
                }
                return true;
            }
            private static final HumanoidModel.ArmPose SLINGSHOT_POSE = HumanoidModel.ArmPose.create("SLINGSHOT", true, (model, entity, arm) -> {
                if (arm == HumanoidArm.RIGHT) {
                    //model.rightArm.yRot = (float) (Math.random() * Math.PI * 2);
                    model.rightArm.yRot = -0.1F + model.head.yRot - 0.4F;
                    model.leftArm.yRot = 0.1F + model.head.yRot;
                    model.rightArm.xRot = (-(float)Math.PI / 2F) + model.head.xRot;
                    model.leftArm.xRot = (-(float)Math.PI / 2F) + model.head.xRot;


                } else {
                    model.rightArm.yRot = -0.1F + model.head.yRot - 0.4F;
                    model.leftArm.yRot = 0.1F + model.head.yRot;
                    model.rightArm.xRot = (-(float)Math.PI / 2F) + model.head.xRot;
                    model.leftArm.xRot = (-(float)Math.PI / 2F) + model.head.xRot;
                }
            });

            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if (!itemStack.isEmpty()) {
                    if (entityLiving.getUsedItemHand() == hand && entityLiving.getUseItemRemainingTicks() > 0) {
                        return SLINGSHOT_POSE;
                    }
                }
                return HumanoidModel.ArmPose.EMPTY;
            }
        });

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
        return UseAnim.CUSTOM;
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

                    ItemStack ammo = itemstack.split(1);
                    if (player.isCreative()) {
                        itemstack.grow(1);
                    }
                    // Read nbt and apply it accordnly
                    CompoundTag nbt = ammo.getOrCreateTag();
                    CompoundTag itemNbt = stack.getOrCreateTag();
                    NonNullList<ItemStack> pebbles = NonNullList.withSize(3, ItemStack.EMPTY);
                    int baseDamege = 1;
                    int firePower = 0;
                    int iceDamge = 0;

                    ContainerHelper.loadAllItems(itemNbt, pebbles);

                    for (ItemStack pebbleC : pebbles) {
                        if(pebbleC.getItem().asItem() instanceof Pebble pebbleCC) {
                            switch (pebbleCC.getType()) {
                                case BOOSTER -> {
                                    baseDamege += pebbleCC.getPower();
                                    break;
                                }
                                case MUTIPLER -> {
                                    baseDamege *= pebbleCC.getPower();
                                    break;
                                }
                                case FIRE -> {
                                    firePower += pebbleCC.getPower();
                                    break;
                                }
                                case ICE -> {
                                    iceDamge += pebbleCC.getPower();
                                    break;
                                }
                            }

                        }

                    }

                    ammo.getOrCreateTag().putDouble("power", power);
                    nbt.putInt("base", baseDamege);
                    nbt.putInt("fire", firePower);
                    nbt.putInt("ice", iceDamge);

                    rock.setItem(ammo);





                    //rock.damege = rock.damege*(power+1);

                    rock.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, 1.2f*power, 1.0F);
                    level.addFreshEntity(rock);
                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.DEEPSLATE_HIT, SoundSource.PLAYERS, 1.0F, 1.3f*power);
                }

                player.awardStat(Stats.ITEM_USED.get(this));
                stack.hurtAndBreak(1, player, (eplayer) -> {
                    eplayer.broadcastBreakEvent(player.getUsedItemHand());
                });

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
