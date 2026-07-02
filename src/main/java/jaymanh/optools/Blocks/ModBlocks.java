package jaymanh.optools.Blocks;

import jaymanh.optools.Blocks.CustomBlockTypes.RefineryBlock;
import jaymanh.optools.OpTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import static jaymanh.optools.OpTools.MOD_ID;

public class ModBlocks {
    public static ResourceKey<Block> key(String id) {
        Identifier blockId = Identifier.fromNamespaceAndPath(MOD_ID, id);
        return ResourceKey.create(Registries.BLOCK, blockId);
    }

    public static final DeferredBlock<Block> DIAMONDIUM_BLOCK = OpTools.BLOCKS.register("diamondium_block",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).destroyTime(4f).requiresCorrectToolForDrops().setId(key("diamondium_block"))));
    public static final DeferredItem<BlockItem> DIAMONDIUM_BLOCK_ITEM = OpTools.ITEMS.registerSimpleBlockItem("diamondium_block", DIAMONDIUM_BLOCK);

    public static final DeferredBlock<Block> DIAMONDILLIUM_BLOCK = OpTools.BLOCKS.register("diamondillium_block",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).destroyTime(5f).requiresCorrectToolForDrops().setId(key("diamondillium_block"))));
    public static final DeferredItem<BlockItem> DIAMONDILLIUM_BLOCK_ITEM = OpTools.ITEMS.registerSimpleBlockItem("diamondillium_block", DIAMONDILLIUM_BLOCK);

    public static final DeferredBlock<Block> STONE_DARKMATTER_ORE = OpTools.BLOCKS.register("stone_darkmatter_ore",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).destroyTime(1f).requiresCorrectToolForDrops().setId(key("stone_darkmatter_ore"))));
    public static final DeferredItem<BlockItem> STONE_DARKMATTER_ORE_ITEM = OpTools.ITEMS.registerSimpleBlockItem("stone_darkmatter_ore", STONE_DARKMATTER_ORE);

    public static final DeferredBlock<Block> DEEPSLATE_DARKMATTER_ORE = OpTools.BLOCKS.register("deepslate_darkmatter_ore",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.DEEPSLATE).destroyTime(1.5f).requiresCorrectToolForDrops().setId(key("deepslate_darkmatter_ore"))));
    public static final DeferredItem<BlockItem> DEEPSLATE_DARKMATTER_ORE_ITEM = OpTools.ITEMS.registerSimpleBlockItem("deepslate_darkmatter_ore", DEEPSLATE_DARKMATTER_ORE);

    public static final DeferredBlock<RefineryBlock> REFINERY = OpTools.BLOCKS.register("refinery",
            () -> new RefineryBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).destroyTime(0.5f).setId(key("refinery"))));
    public static final DeferredItem<BlockItem> REFINERY_ITEM = OpTools.ITEMS.registerSimpleBlockItem("refinery", REFINERY);
}