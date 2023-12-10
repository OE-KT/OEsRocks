package com.oekt.oerocks.blocks.custom;

import com.oekt.oerocks.blocks.ModBlocks;
import com.oekt.oerocks.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

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
                int[] slots = {0, 0, 0};
                nbt.putIntArray("slots", slots);

                level.playSound(player, pos, SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL);
            }

        }




    }
}
