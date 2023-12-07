package com.oekt.oerocks.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;

import java.util.RandomAccess;
import java.util.function.ToIntFunction;

public class MoltenSettlement extends MagmaBlock {
    public MoltenSettlement(Properties p_54800_) {
        super(p_54800_);
    }



    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity entity, ItemStack stack) {
        if(RandomSource.create().nextInt(0, 100) < 10) {
            level.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
        }

        super.playerDestroy(level, player, pos, state, entity, stack);
    }
}
