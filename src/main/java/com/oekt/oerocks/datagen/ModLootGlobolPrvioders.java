package com.oekt.oerocks.datagen;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.items.ModItems;
import com.oekt.oerocks.loot.AddItemModifer;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ModLootGlobolPrvioders extends GlobalLootModifierProvider {
    public ModLootGlobolPrvioders(PackOutput output) {
        super(output, OErocks.MODID);
    }

    @Override
    protected void start() {
        add("rocks_from_stone", new AddItemModifer(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.STONE).build()}, ModItems.ROCK.get()));

    }
}
