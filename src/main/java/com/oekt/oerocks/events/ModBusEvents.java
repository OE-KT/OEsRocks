package com.oekt.oerocks.events;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.entitty.ModEnittys;
import com.oekt.oerocks.entitty.RockGard;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OErocks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
    @SubscribeEvent
    public static void registerAttrabuits(EntityAttributeCreationEvent event) {
        event.put(ModEnittys.ROCK_GARD_ENITTY.get(), RockGard.createAttrabuits().build());
    }
}
