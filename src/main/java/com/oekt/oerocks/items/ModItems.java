package com.oekt.oerocks.items;

import com.oekt.oerocks.entitty.ThrowableRock;
import com.oekt.oerocks.items.custom.Hammer;
import com.oekt.oerocks.items.custom.Rock;
import com.oekt.oerocks.items.custom.Slingshot;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.oekt.oerocks.OErocks.MODID;

public class ModItems { public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Rocks
    public static final RegistryObject<Item> ROCK = ITEMS.register("rock", () -> new Rock(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MOLTEN_ROCK = ITEMS.register("molten_rock", () -> new Rock(new Item.Properties().stacksTo(64)).setDamge(4).setType(ThrowableRock.Rocktype.FIRE).setCooldown(10));
    public static final RegistryObject<Item> FREEZE_ROCK = ITEMS.register("freeze_rock", () -> new Rock(new Item.Properties().stacksTo(64)).setDamge(4).setCooldown(8).setType(ThrowableRock.Rocktype.ICE));

    public static final RegistryObject<Item> SLINGSHOT = ITEMS.register("slingshot", () -> new Slingshot(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> HAMMER = ITEMS.register("hammer", () -> new Hammer(new Item.Properties().stacksTo(1)));
}
