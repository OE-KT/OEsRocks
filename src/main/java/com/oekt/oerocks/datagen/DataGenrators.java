package com.oekt.oerocks.datagen;

import com.oekt.oerocks.OErocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OErocks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenrators {
    @SubscribeEvent
    public static void GatherData(GatherDataEvent dataEvent) {
        DataGenerator gnrators = dataEvent.getGenerator();
        PackOutput packOutput = gnrators.getPackOutput();

        gnrators.addProvider(dataEvent.includeServer(), new ModLootGlobolPrvioders(packOutput));

    }
}
