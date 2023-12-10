package com.oekt.oerocks.events;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.entitty.clinet.ModModalLayers;
import com.oekt.oerocks.entitty.clinet.RockGaurdModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OErocks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
    // Why is there an error??
    public static void registerLayer(EntityRenderersEvent.RegisterRenderers events) {
        events.registerEntityRenderer(ModModalLayers.ROCK_GAURD_LAYER, RockGaurdModel::createBodyLayer);
    }
}
