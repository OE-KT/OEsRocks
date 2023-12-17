package com.oekt.oerocks.screen;

import com.oekt.oerocks.OErocks;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES,OErocks.MODID);

    public static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registeMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static final RegistryObject<MenuType<SlingshotTableMenu>> SLINGSHOT_TABLE_MENU = registeMenuType("slingshot_table_menu", SlingshotTableMenu::new);
}
