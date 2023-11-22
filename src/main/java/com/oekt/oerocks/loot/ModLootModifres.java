package com.oekt.oerocks.loot;

import com.mojang.serialization.Codec;
import com.oekt.oerocks.OErocks;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifres {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFRERS_SEARLIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, OErocks.MODID);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFRERS_SEARLIZERS.register("add_item", AddItemModifer.CODEC);
}
