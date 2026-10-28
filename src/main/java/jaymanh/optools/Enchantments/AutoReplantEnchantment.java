package jaymanh.optools.Enchantments;

import jaymanh.optools.OpTools;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jaymanh.optools.Enchantments.InventoryHelper.findItemSlot;
import static jaymanh.optools.Enchantments.InventoryHelper.hasItemInInventory;
import static jaymanh.optools.Enchantments.InventoryHelper.removeItemInInventory;

public class AutoReplantEnchantment {

    private static final Map<Block, Item> blockToItemMap = new HashMap<>();

    private static final List<ReplantTask> replantTasks = new ArrayList<>();

    static record ReplantTask(ServerLevel world, BlockPos pos, BlockState state) {}

    static {
        blockToItemMap.put(Blocks.WHEAT, Items.WHEAT_SEEDS);
        blockToItemMap.put(Blocks.CARROTS, Items.CARROT);
        blockToItemMap.put(Blocks.POTATOES, Items.POTATO);
        blockToItemMap.put(Blocks.BEETROOTS, Items.BEETROOT_SEEDS);
        blockToItemMap.put(Blocks.MELON_STEM, Items.MELON_SEEDS);
        blockToItemMap.put(Blocks.PUMPKIN_STEM, Items.PUMPKIN_SEEDS);
        blockToItemMap.put(Blocks.NETHER_WART, Items.NETHER_WART);
        blockToItemMap.put(Blocks.COCOA, Items.COCOA_BEANS);
    }

    private static BlockState replantState(BlockState broken) {
        BlockState state = broken.getBlock().defaultBlockState();
        if (broken.hasProperty(BlockStateProperties.HORIZONTAL_FACING) && state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            state = state.setValue(BlockStateProperties.HORIZONTAL_FACING, broken.getValue(BlockStateProperties.HORIZONTAL_FACING));
        }
        for (Property<?> property : state.getProperties()) {
            if (property instanceof IntegerProperty age && "age".equals(age.getName())) {
                state = setAge(state, age);
            }
        }
        return state;
    }

    private static BlockState setAge(BlockState state, IntegerProperty age) {
        return state.setValue(age, 0);
    }

    private static void scheduleReplant(BlockPos blockPos, BlockState planted, ServerLevel world, Player player) {
        Item item = blockToItemMap.get(planted.getBlock());
        if (item == null) {
            return;
        }
        ItemStack itemStack = new ItemStack(item);
        if (!hasItemInInventory(player, itemStack)) {
            return;
        }
        int itemSlot = findItemSlot(player, itemStack);
        removeItemInInventory(player, itemSlot);
        replantTasks.add(new ReplantTask(world, blockPos, planted));
    }

    public static void initialise() {
        OpTools.register(OpTools.hook("crop_break"), (world, level, context, user, pos) -> {
            if (!(user instanceof Player player)) {
                return;
            }
            BlockPos blockPos = BlockPos.containing(pos);
            BlockState broken = world.getBlockState(blockPos);
            if (!blockToItemMap.containsKey(broken.getBlock())) {
                return;
            }
            BlockState planted = replantState(broken);
            world.getServer().execute(() -> scheduleReplant(blockPos, planted, world, player));
        });
    }

    public static List<ReplantTask> getReplantTasks() {
        return replantTasks;
    }
}
