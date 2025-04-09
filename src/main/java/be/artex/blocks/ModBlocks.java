package be.artex.blocks;

import be.artex.Structalis;
import be.artex.blocks.advanced.CactusBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Structalis.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Structalis.MODID);

    private static <T extends Block> DeferredBlock<T> registerAdvancedBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static DeferredBlock<Block> registerBlock(String name, BlockBehaviour.Properties properties) {
        return BLOCKS.registerSimpleBlock(name, properties);
    }

    private static DeferredBlock<Block> registerBlockFromCopy(String name, Block block) {
        return BLOCKS.registerSimpleBlock(name, BlockBehaviour.Properties.ofFullCopy(block));
    }

    private static DeferredItem<BlockItem> registerBlockItem(String name, DeferredBlock<Block> block) {
        return ITEMS.registerSimpleBlockItem(name, block);
    }

    public static final DeferredBlock<Block> DARK_PRISMARINE_BRICKS = registerBlockFromCopy("dark_prismarine_bricks", Blocks.DARK_PRISMARINE);
    public static final DeferredItem<BlockItem> DARK_PRISMARINE_BRICKS_ITEM = registerBlockItem("dark_prismarine_bricks", DARK_PRISMARINE_BRICKS);

    public static final DeferredBlock<Block> CACTUS_BLOCK = registerAdvancedBlock("cactus_block",
            () -> new CactusBlock(BlockBehaviour.Properties.of()));
    public static final DeferredItem<BlockItem> CACTUS_BLOCK_ITEM = registerBlockItem("cactus_block", CACTUS_BLOCK);

    public static final DeferredBlock<Block> STRIPPED_CACTUS_BLOCK = registerBlockFromCopy("stripped_cactus_block", Blocks.OAK_PLANKS);
    public static final DeferredItem<BlockItem> STRIPPED_CACTUS_BLOCK_ITEM = registerBlockItem("stripped_cactus_block", STRIPPED_CACTUS_BLOCK);


}
