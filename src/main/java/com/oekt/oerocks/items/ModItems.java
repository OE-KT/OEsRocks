package com.oekt.oerocks.items;

import com.oekt.oerocks.items.custom.Rock;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.oekt.oerocks.OErocks.MODID;

public class ModItems { public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<Item> ROCK = ITEMS.register("rock", () -> new Rock(new Item.Properties().stacksTo(64)));
}
