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
            output.accept(ModItems.SLINGSHOT.get());
            output.accept(ModBlocks.EXAMPLE_BLOCK.get());
            // Hammers
            output.accept(ModItems.STONE_HAMMER.get());
//            output.accept(ModItems.IRON_HAMMER.get());
//            output.accept(ModItems.DIMOND_HAMMER.get());
//            output.accept(ModItems.NETHERITE_HAMMER.get());
            // Rocks
            output.accept(ModItems.ROCK.get());
            output.accept(ModItems.MOLTEN_ROCK.get());
            output.accept(ModItems.FREEZE_ROCK.get());
            output.accept(ModItems.DEEPSLATE_ROCK.get());
            // Add the example item to the tab. For your own tabs, this method is preferred over the event
        }).build());

}
