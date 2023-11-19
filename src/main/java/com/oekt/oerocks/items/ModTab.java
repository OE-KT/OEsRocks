package com.oekt.oerocks.items;

import com.oekt.oerocks.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.oekt.oerocks.OErocks.MODID;

public class ModTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> OEROCKS_TAB = CREATIVE_MODE_TABS.register("oerocks_tab", () -> CreativeModeTab.builder()
        .withTabsBefore(CreativeModeTabs.COMBAT)
        .icon(() -> ModItems.ROCK.get().getDefaultInstance())
        .displayItems((parameters, output) -> {
            output.accept(ModItems.ROCK.get());
            output.accept(ModBlocks.EXAMPLE_BLOCK.get());// Add the example item to the tab. For your own tabs, this method is preferred over the event
        }).build());

}
