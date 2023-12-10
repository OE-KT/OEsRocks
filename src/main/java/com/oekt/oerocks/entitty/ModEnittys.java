package com.oekt.oerocks.entitty;

import com.oekt.oerocks.OErocks;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.text.html.parser.Entity;

public class ModEnittys {
    public static final DeferredRegister<EntityType<?>> ENTITYS_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OErocks.MODID);

    public static final RegistryObject<EntityType<ThrowableRock>> ROCK_ENITTY =
            ENTITYS_TYPES.register("rock", () -> EntityType.Builder.<ThrowableRock>of(ThrowableRock::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("rock"));
    public static final RegistryObject<EntityType<RockGard>> ROCK_GARD_ENITTY =
            ENTITYS_TYPES.register("rock_gard", () -> EntityType.Builder.<RockGard>of(RockGard::new, MobCategory.MISC)
                    .sized(1, 1).build("rock_gard"));
}
