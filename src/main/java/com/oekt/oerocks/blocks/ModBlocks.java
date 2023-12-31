package com.oekt.oerocks.blocks;

import com.oekt.oerocks.blocks.custom.MoltenSettlement;
import com.oekt.oerocks.blocks.custom.SlingshotTable;
import com.oekt.oerocks.items.ModItems;
import com.oekt.oerocks.sound.ModSounds;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.oekt.oerocks.OErocks.MODID;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final RegistryObject<Block> SETTLEMENT = registerBlock("settlement", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(ModSounds.BLOCK_SETTLEMET_SOUNDS).requiresCorrectToolForDrops().strength(2)), true);

    public static final RegistryObject<Block> AGED_SETTLEMENT = registerBlock("aged_settlement", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.GRAVEL).requiresCorrectToolForDrops().strength(4)), true);

    public static final RegistryObject<Block> FROSTED_SETTLEMENT = registerBlock("frosted_settlement", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.GRAVEL).requiresCorrectToolForDrops().strength(.5f)), true);

    public static final RegistryObject<Block> MOLTEN_SETTLEMENT = registerBlock("molten_settlement", () -> new MoltenSettlement(BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).sound(SoundType.NETHER_WOOD).requiresCorrectToolForDrops().strength(4).lightLevel(emiss -> {
        return 4;
    })), true);
    public static final RegistryObject<Block> SLINGSHOT_TABLE = registerBlock("slingshot_table", () -> new SlingshotTable(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().strength(2)), true);


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
