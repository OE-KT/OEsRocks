package com.oekt.oerocks.blocks;

import com.oekt.oerocks.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.oekt.oerocks.OErocks.MODID;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final RegistryObject<Block> EXAMPLE_BLOCK = registerBlock("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)), true);
   // public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ModItems.ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, boolean createblockitem) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        if (createblockitem) {
            registerBlockItem(name, (RegistryObject<Block>) toReturn);
        }

        return toReturn;


    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<Block> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
