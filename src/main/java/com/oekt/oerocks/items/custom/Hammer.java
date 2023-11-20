package com.oekt.oerocks.items.custom;

import com.oekt.oerocks.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class Hammer extends Item {
    public int cooldown = 10;

    public int maxRocks = 3;

    public Hammer(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        boolean IsStone = context.getLevel().getBlockState(pos).is(BlockTags.BASE_STONE_OVERWORLD);
        if(IsStone && !context.getPlayer().getCooldowns().isOnCooldown(this)) {
            context.getLevel().destroyBlock(pos, false);
            context.getPlayer().getInventory().add(new ItemStack(ModItems.ROCK.get(), context.getLevel().getRandom().nextInt(1, maxRocks)));
            context.getPlayer().getCooldowns().addCooldown(this, cooldown);
        }
        context.getPlayer().awardStat(Stats.ITEM_USED.get(this));
        return super.useOn(context);
    }
}
