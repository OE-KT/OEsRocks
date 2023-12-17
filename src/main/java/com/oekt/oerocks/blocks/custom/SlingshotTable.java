package com.oekt.oerocks.blocks.custom;

import com.oekt.oerocks.blocks.ModBlocks;
import com.oekt.oerocks.blocks.enitty.ModBlockEnittys;
import com.oekt.oerocks.blocks.enitty.SlingShotBlockEnitty;
import com.oekt.oerocks.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SlingshotTable extends BaseEntityBlock {

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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SlingShotBlockEnitty(pos, state);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SlingShotBlockEnitty) {
                ((SlingShotBlockEnitty) blockEntity).drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof SlingShotBlockEnitty) {
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (SlingShotBlockEnitty) entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if(level.isClientSide()){
            return null;
        }
        return createTickerHelper(type, ModBlockEnittys.SLINGSHOT_TABLE_BE.get(),
                (level1, pos, state1, slingShotBlockEnitty) -> slingShotBlockEnitty.tick(level1, pos, state1));
    }
}
