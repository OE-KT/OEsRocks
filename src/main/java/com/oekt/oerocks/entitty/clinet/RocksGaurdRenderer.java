package com.oekt.oerocks.entitty.clinet;

import com.oekt.oerocks.OErocks;
import com.oekt.oerocks.entitty.RockGard;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class RocksGaurdRenderer extends MobRenderer<RockGard, RockGaurdModel<RockGard>> {
    private static final  ResourceLocation TEXTURE = new ResourceLocation(OErocks.MODID, "textures/entity/placeholder.png");
    public RocksGaurdRenderer(EntityRendererProvider.Context context) {
        super(context, new RockGaurdModel<>(context.bakeLayer(ModModalLayers.ROCK_GAURD_LAYER)), 1.0f);
    }


    @Override
    public ResourceLocation getTextureLocation(RockGard p_114482_) {
        return TEXTURE;
    }
}
