package com.oekt.oerocks.datagen;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.items.ModItems;
import com.oekt.oerocks.loot.AddItemModifer;
import com.oekt.oerocks.util.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

import java.util.List;
import java.util.function.Predicate;

public class ModLootGlobolPrvioders extends GlobalLootModifierProvider {
    public ModLootGlobolPrvioders(PackOutput output) {
        super(output, OErocks.MODID);
    }

    @Override
    protected void start() {



            add("rocks_from_cobbelestone", new AddItemModifer(new LootItemCondition[] {

                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.COBBLESTONE).build(),
                    MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.HAMMER)).build()}, ModItems.ROCK.get()));

        add("rocks_from_deepslate", new AddItemModifer(new LootItemCondition[] {

                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.COBBLED_DEEPSLATE).build(),
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModTags.Items.HAMMER)).build()}, ModItems.DEEPSLATE_ROCK.get()));



    }
}
