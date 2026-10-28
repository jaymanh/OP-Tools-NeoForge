package jaymanh.optools.Enchantments;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TreeBreakerEnchantment {
    private static final int MAX_BLOCKS = 64 * 9;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onBlockBreak(BreakBlockEvent event) {
        if (event.isCanceled() || !(event.getLevel() instanceof ServerLevel world)) {
            return;
        }
        if (!(event.getPlayer() instanceof ServerPlayer player)) {
            return;
        }

        BlockState originState = event.getState();
        if (!originState.is(BlockTags.LOGS)) {
            return;
        }

        ItemStack tool = player.getMainHandItem();
        Optional<Holder<Enchantment>> treeBreaker = enchantment(world);
        if (treeBreaker.isEmpty() || EnchantmentHelper.getItemEnchantmentLevel(treeBreaker.get(), tool) <= 0) {
            return;
        }

        breakConnectedLogs(world, player, event.getPos(), originState.getBlock(), tool);
    }

    private static Optional<Holder<Enchantment>> enchantment(ServerLevel world) {
        Registry<Enchantment> registry = world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Enchantment value = registry.getValue(ModEnchantments.TREE_BREAKER);
        if (value == null) {
            return Optional.empty();
        }
        return Optional.of(registry.wrapAsHolder(value));
    }

    private static void breakConnectedLogs(ServerLevel world, ServerPlayer player, BlockPos origin, Block log, ItemStack tool) {
        GameType gameType = gameType(player);
        if (gameType == null) {
            return;
        }

        Set<BlockPos> seen = new HashSet<>();
        ArrayDeque<BlockPos> queue = new ArrayDeque<>();
        seen.add(origin);
        enqueueLogs(world, origin, log, seen, queue);

        int broken = 1;
        while (!queue.isEmpty() && broken < MAX_BLOCKS) {
            BlockPos pos = queue.removeFirst();
            if (player.blockActionRestricted(world, pos, gameType)) {
                continue;
            }
            if (world.getBlockState(pos).getBlock() != log) {
                continue;
            }

            world.destroyBlock(pos, true, player);
            broken++;
            tool.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            if (tool.isEmpty() || broken >= MAX_BLOCKS) {
                return;
            }
            enqueueLogs(world, pos, log, seen, queue);
        }
    }

    private static GameType gameType(ServerPlayer player) {
        if (player.gameMode == null) {
            return null;
        }
        return player.gameMode.getGameModeForPlayer();
    }

    private static void enqueueLogs(ServerLevel world, BlockPos current, Block log, Set<BlockPos> seen, ArrayDeque<BlockPos> queue) {
        for (Direction direction : Direction.values()) {
            offer(world, current.relative(direction), log, seen, queue);
        }

        for (Direction direction1 : Direction.values()) {
            for (Direction direction2 : Direction.values()) {
                if (direction1 != direction2 && direction1.getAxis() != direction2.getAxis()) {
                    offer(world, current.relative(direction1).relative(direction2), log, seen, queue);
                }
            }
        }
    }

    private static void offer(ServerLevel world, BlockPos pos, Block log, Set<BlockPos> seen, ArrayDeque<BlockPos> queue) {
        if (world.getBlockState(pos).getBlock() == log && seen.add(pos)) {
            queue.add(pos);
        }
    }
}
