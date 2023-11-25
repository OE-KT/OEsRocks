package com.oekt.oerocks.datagen;

import com.oekt.oerocks.OErocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = OErocks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenrators {
    @SubscribeEvent
    public static void GatherData(GatherDataEvent dataEvent) {
        DataGenerator gnrators = dataEvent.getGenerator();
        PackOutput packOutput = gnrators.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = dataEvent.getLookupProvider();

        gnrators.addProvider(dataEvent.includeServer(), new ModLootGlobolPrvioders(packOutput));
        gnrators.addProvider(dataEvent.includeServer(), new ModWorldGenPevioder(packOutput, lookupProvider));
    }
}
