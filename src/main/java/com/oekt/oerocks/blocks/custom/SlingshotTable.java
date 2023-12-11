package com.oekt.oerocks.blocks.custom;

import com.oekt.oerocks.blocks.ModBlocks;
import com.oekt.oerocks.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class SlingshotTable extends Block {

    public SlingshotTable(Properties prop) {
        super(prop);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity enitty) {

        if(enitty instanceof Player player) {
            ItemStack itemInHand = player.getItemInHand(InteractionHand.MAIN_HAND);
            if(itemInHand.getItem() == ModItems.SLINGSHOT.get()) {
                CompoundTag nbt =  itemInHand.getOrCreateTag();
                /*

                 */
                //int[] slots = {3, 3, 3};

                NonNullList<ItemStack> pebbles = NonNullList.withSize(3, ItemStack.EMPTY);

                pebbles.set(0 ,new ItemStack(ModItems.PEBBLE.get()));
                pebbles.set(1, new ItemStack(ModItems.PEBBLE.get()));
                pebbles.set(2, new ItemStack(ModItems.PEBBLE_MUTI.get()));
                ContainerHelper.saveAllItems(nbt, pebbles, true);

                level.playSound(player, pos, SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL);
            }

        }




    }
}
