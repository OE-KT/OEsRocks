package com.oekt.oerocks.util;

import com.oekt.oerocks.OErocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        private static TagKey <Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(OErocks.MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> ROCKS = tag("rocks");
        private static TagKey <Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(OErocks.MODID, name));
        }
    }
}
