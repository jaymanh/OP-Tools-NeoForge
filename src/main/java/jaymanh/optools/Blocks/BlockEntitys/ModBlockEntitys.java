package jaymanh.optools.Blocks.BlockEntitys;

import jaymanh.optools.Blocks.ModBlocks;
import jaymanh.optools.OpTools;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModBlockEntitys {
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RefineryBlockEntity>> REFINERY_BLOCK_ENTITY =
            OpTools.BLOCK_ENTITY_TYPES.register("refinery",
                    () -> BlockEntityType.Builder.of(RefineryBlockEntity::new, ModBlocks.REFINERY.get()).build(null));
}