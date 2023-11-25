package com.oekt.oerocks.items.custom;

import com.oekt.oerocks.items.ModItems;
import com.oekt.oerocks.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

public class Hammer extends DiggerItem {


    public Hammer(float p_204108_, float p_204109_, Tier p_204110_, Properties p_204112_) {
        super(p_204108_, p_204109_, p_204110_, ModTags.Blocks.HAMMER_MINEABLE, p_204112_);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {

        return ModToolActions.DEFAULT_HAMMER_ACTIONS.contains(toolAction);
    }
    // Terrible Code its 9:10PM and I have just finshed tweeking the slingshot
    //  EHHEHEHHEHEHHEH
//    @Override
//    public InteractionResult useOn(UseOnContext context) {
//        BlockPos pos = context.getClickedPos();
//        Player player = context.getPlayer();
//        Level level = context.getLevel();
//        boolean IsStone = context.getLevel().getBlockState(pos).is(BlockTags.BASE_STONE_OVERWORLD);
//        if(!level.isClientSide() && IsStone && !player.getCooldowns().isOnCooldown(this)) {
//            level.destroyBlock(pos, false);
//            player.getInventory().add(new ItemStack(ModItems.ROCK.get(), level.getRandom().nextInt(1, maxRocks)));
//           player.getCooldowns().addCooldown(this, cooldown);
//            return InteractionResult.PASS;
//        }
//        //context.getPlayer().awardStat(Stats.ITEM_USED.get(this));
//        return super.useOn(context);
//    }
}
