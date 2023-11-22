package com.oekt.oerocks.items.custom;

import com.oekt.oerocks.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Hammer extends PickaxeItem {
    public int cooldown = 10;

    public int maxRocks = 3;

    public Hammer(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42961_, p_42962_, p_42963_, p_42964_);
    }


    // Terrible Code its 9:10PM and I have just finshed tweeking the slingshot
    //  EHHEHEHHEHEHHEH
    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        Level level = context.getLevel();
        boolean IsStone = context.getLevel().getBlockState(pos).is(BlockTags.BASE_STONE_OVERWORLD);
        if(!level.isClientSide() && IsStone && !player.getCooldowns().isOnCooldown(this)) {
            level.destroyBlock(pos, false);
            player.getInventory().add(new ItemStack(ModItems.ROCK.get(), level.getRandom().nextInt(1, maxRocks)));
           player.getCooldowns().addCooldown(this, cooldown);
            return InteractionResult.PASS;
        }
        //context.getPlayer().awardStat(Stats.ITEM_USED.get(this));
        return super.useOn(context);
    }
}
