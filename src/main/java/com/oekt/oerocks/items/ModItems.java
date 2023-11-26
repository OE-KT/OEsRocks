package com.oekt.oerocks.items;

import com.oekt.oerocks.entitty.ThrowableRock;
import com.oekt.oerocks.items.custom.Hammer;
import com.oekt.oerocks.items.custom.Rock;
import com.oekt.oerocks.items.custom.Slingshot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.oekt.oerocks.OErocks.MODID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Rocks
    public static final RegistryObject<Item> ROCK = ITEMS.register("rock", () -> new Rock(new Item.Properties().stacksTo(64), 5, ThrowableRock.Rocktype.NORM, 20));

    public static final RegistryObject<Item> DEEPSLATE_ROCK = ITEMS.register("deepslate_rock", () -> new Rock(new Item.Properties().stacksTo(64),
            9, ThrowableRock.Rocktype.NORM, 28));

    public static final RegistryObject<Item> MOLTEN_ROCK = ITEMS.register("molten_rock", () -> new Rock(new Item.Properties().stacksTo(64),
            4, ThrowableRock.Rocktype.FIRE, 10));
    public static final RegistryObject<Item> FREEZE_ROCK = ITEMS.register("freeze_rock", () -> new Rock(new Item.Properties().stacksTo(64),
            4, ThrowableRock.Rocktype.ICE, 8));

    public static final RegistryObject<Item> SLINGSHOT = ITEMS.register("slingshot", () -> new Slingshot(new Item.Properties().stacksTo(1).durability(467)));

    // Hammers
    public static final RegistryObject<Item> STONE_HAMMER = ITEMS.register("stone_hammer", () -> new Hammer(3f, -3.5f, Tiers.STONE, new Item.Properties().stacksTo(1)));

//    public static final RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer", () -> new Hammer(Tiers.IRON, 4, 0.2f, new Item.Properties().stacksTo(1)));
//
//    public static final RegistryObject<Item> DIMOND_HAMMER = ITEMS.register("dimond_hammer", () -> new Hammer(Tiers.DIAMOND, 4, 0.2f, new Item.Properties().stacksTo(1)));
//
//    public static final RegistryObject<Item> NETHERITE_HAMMER = ITEMS.register("netherite_hammer", () -> new Hammer(Tiers.NETHERITE, 4, 0.2f, new Item.Properties().stacksTo(1)));

}