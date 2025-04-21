package be.artex.blocks.advanced;

import be.artex.Structalis;
import be.artex.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.slf4j.Logger;

public class LogBlock extends Block {
    private static final Logger LOGGER = Structalis.LOGGER;
    private DeferredBlock<Block> strippedBlock;

    public LogBlock(Properties properties, DeferredBlock<Block> strippedBlock) {
        super(properties);

        this.strippedBlock = strippedBlock;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(stack.getItem() instanceof AxeItem))
            return ItemInteractionResult.FAIL;

        level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);

        level.setBlock(pos, this.strippedBlock.get().defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE);

        if (!level.isClientSide) {
            if (player.isCreative())
                return ItemInteractionResult.SUCCESS;

            stack.setDamageValue(stack.getDamageValue() + 1);

            if (stack.getDamageValue() == stack.getMaxDamage()) {
                player.setItemInHand(hand, ItemStack.EMPTY);
                level.playSound(player, pos, SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }

        return ItemInteractionResult.SUCCESS;
    }


}
