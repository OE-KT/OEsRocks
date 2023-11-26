package com.oekt.oerocks.worldgen;

import com.oekt.oerocks.OErocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFetures {

    public static final ResourceKey<PlacedFeature> SETTLEMENT_PLACED_KEY = registerKey("settlement_placed");

    public static final ResourceKey<PlacedFeature> AGED_SETTLEMENT_PLACED_KEY = registerKey("aged_settlement_placed");


    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?,?>> configeredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SETTLEMENT_PLACED_KEY, configeredFeatures.getOrThrow(ModConfigeredFetures.OVERWOLRD_SETTLEMENT_KEY),
                ModOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(60))));

        register(context, AGED_SETTLEMENT_PLACED_KEY, configeredFeatures.getOrThrow(ModConfigeredFetures.OVERWORLD_AGED_SETTLEMENT_KEY),
                ModOrePlacement.commonOrePlacement(5,
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0))));
    }
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(OErocks.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
