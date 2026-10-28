package jaymanh.optools.Enchantments;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class AutoRepairEnchantment {
    private static final Random random = new Random();

    @SubscribeEvent
    public void onEndLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel world)) {
            return;
        }
        Optional<Holder<Enchantment>> autoRepair = enchantment(world);
        if (autoRepair.isEmpty()) {
            return;
        }
        for (ServerPlayer player : world.players()) {
            repairPlayer(player, world, autoRepair.get());
        }
    }

    private static Optional<Holder<Enchantment>> enchantment(ServerLevel world) {
        Registry<Enchantment> registry = world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        return registry.get(ModEnchantments.AUTO_REPAIR).map(holder -> holder);
    }

    private static void repairPlayer(ServerPlayer player, ServerLevel world, Holder<Enchantment> autoRepair) {
        Set<ItemStack> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            tryRepair(player.getInventory().getItem(i), seen, autoRepair, world, player);
        }
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.isArmor() || slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND) {
                tryRepair(player.getItemBySlot(slot), seen, autoRepair, world, player);
            }
        }
    }

    private static void tryRepair(ItemStack itemStack, Set<ItemStack> seen, Holder<Enchantment> autoRepair, ServerLevel world, ServerPlayer player) {
        if (itemStack.isEmpty() || !seen.add(itemStack)) {
            return;
        }
        int level = EnchantmentHelper.getItemEnchantmentLevel(autoRepair, itemStack);
        if (level <= 0) {
            return;
        }
        int chance = Math.max(level / 2, 1);
        chance = Math.min(chance, 100);
        if (itemStack.isDamaged() && random.nextInt(100 / chance) == 0) {
            itemStack.hurtAndBreak(-1 * level, world, player, stack -> {
            });
        }
    }
}
