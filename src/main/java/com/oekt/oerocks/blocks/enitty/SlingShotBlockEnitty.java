package com.oekt.oerocks.blocks.enitty;

import com.oekt.oerocks.screen.SlingshotTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlingShotBlockEnitty extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(1);

    private static final int WEPOEN_SLOT = 0;

    protected final ContainerData data;
    private int PLACEHOLDER = 0;

    private LazyOptional<IItemHandler> lazyHandler = LazyOptional.empty();
    public SlingShotBlockEnitty(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEnittys.SLINGSHOT_TABLE_BE.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            @Override
            public int get(int indexP) {
                return switch (indexP) {
                    case 0 -> SlingShotBlockEnitty.this.PLACEHOLDER;
                    default -> 0;
                };

            }

            @Override
            public void set(int indexP, int pValue) {
                switch (indexP) {
                    case 0 -> SlingShotBlockEnitty.this.PLACEHOLDER = pValue;
                }
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }
    public void drops() {
        SimpleContainer invtory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            invtory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, invtory);
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        lazyHandler = LazyOptional.of(() -> itemHandler);
        super.onLoad();
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Slingshot Forger");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerID, Inventory inventory, Player player) {
        return new SlingshotTableMenu(pContainerID, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("slingshot_table.PLACEHOLDER", PLACEHOLDER);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        PLACEHOLDER = pTag.getInt("slingshot_table.PLACEHOLDER");
        super.load(pTag);
    }

    public void tick(Level level1, BlockPos pos, BlockState state1) {

    }
}
