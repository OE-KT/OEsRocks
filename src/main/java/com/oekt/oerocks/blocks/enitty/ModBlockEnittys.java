package com.oekt.oerocks.blocks.enitty;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEnittys {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENITTYS = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OErocks.MODID);

    public static final RegistryObject<BlockEntityType<SlingShotBlockEnitty>> SLINGSHOT_TABLE_BE = BLOCK_ENITTYS.register("slingshot_table_be",
            () -> BlockEntityType.Builder.of(SlingShotBlockEnitty::new,
                    ModBlocks.SLINGSHOT_TABLE.get()).build(null));
}
