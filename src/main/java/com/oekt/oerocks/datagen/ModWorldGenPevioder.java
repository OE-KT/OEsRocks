package com.oekt.oerocks.datagen;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.worldgen.ModBiomModifres;
import com.oekt.oerocks.worldgen.ModConfigeredFetures;
import com.oekt.oerocks.worldgen.ModPlacedFetures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenPevioder extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfigeredFetures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFetures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomModifres::bootstrap);
    public ModWorldGenPevioder(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(OErocks.MODID));
    }
}
