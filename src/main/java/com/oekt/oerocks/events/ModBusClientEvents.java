package com.oekt.oerocks.events;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.entitty.clinet.ModModalLayers;
import com.oekt.oerocks.entitty.clinet.RockGaurdModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OErocks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions events) {
        events.registerLayerDefinition(ModModalLayers.ROCK_GAURD_LAYER, RockGaurdModel::createBodyLayer);
    }
}
