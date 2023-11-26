package com.oekt.oerocks.worldgen;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfigeredFetures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWOLRD_SETTLEMENT_KEY =
registerKey("settlement");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_AGED_SETTLEMENT_KEY =
            registerKey("aged_settlement");
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplace = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplace = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> overworldSettlement = List.of(OreConfiguration.target(stoneReplace,
                ModBlocks.SETTLEMENT.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> overworldAgedSettlement = List.of(OreConfiguration.target(deepslateReplace,
                ModBlocks.AGED_SETTLEMENT.get().defaultBlockState()));

        register(context, OVERWOLRD_SETTLEMENT_KEY, Feature.ORE, new OreConfiguration(overworldSettlement, 20));
        register(context, OVERWORLD_AGED_SETTLEMENT_KEY, Feature.ORE, new OreConfiguration(overworldAgedSettlement, 20));
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(OErocks.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
