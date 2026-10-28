package jaymanh.optools.Enchantments;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

public class EnchantTick {
    static final String GRAVITY_TICKS = "op_tools:gravity_ticks";
    private static final int GRAVITY_DURATION = 100;
    private static final double SCAN_RANGE = 64.0;

    public static void suspendGravity(Entity entity) {
        entity.setNoGravity(true);
        entity.getPersistentData().putInt(GRAVITY_TICKS, GRAVITY_DURATION);
        stripLegacyTags(entity);
    }

    @SubscribeEvent
    public void onEndLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel world)) {
            return;
        }

        Set<Entity> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        for (ServerPlayer player : world.players()) {
            AABB box = player.getBoundingBox().inflate(SCAN_RANGE);
            for (LivingEntity entity : world.getEntitiesOfClass(LivingEntity.class, box, EnchantTick::hasGravityTimer)) {
                if (seen.add(entity)) {
                    tickGravity(entity);
                }
            }
        }
    }

    private static boolean hasGravityTimer(LivingEntity entity) {
        return entity.getPersistentData().contains(GRAVITY_TICKS) || entityTags(entity).contains("Gravity");
    }

    private static void tickGravity(Entity entity) {
        CompoundTag data = entity.getPersistentData();
        int remaining = data.contains(GRAVITY_TICKS) ? data.getInt(GRAVITY_TICKS) : legacyRemaining(entity);
        stripLegacyTags(entity);
        if (remaining <= 1) {
            entity.setNoGravity(false);
            data.remove(GRAVITY_TICKS);
            return;
        }
        data.putInt(GRAVITY_TICKS, remaining - 1);
    }

    private static int legacyRemaining(Entity entity) {
        for (String tag : entityTags(entity)) {
            if (!tag.startsWith("Num")) {
                continue;
            }
            try {
                return Math.max(1, GRAVITY_DURATION - Integer.parseInt(tag.substring(3)));
            } catch (NumberFormatException ignored) {
                return GRAVITY_DURATION;
            }
        }
        return GRAVITY_DURATION;
    }

    private static void stripLegacyTags(Entity entity) {
        List<String> legacy = new ArrayList<>();
        for (String tag : entityTags(entity)) {
            if ("Gravity".equals(tag) || tag.startsWith("Num")) {
                legacy.add(tag);
            }
        }
        for (String tag : legacy) {
            entity.removeTag(tag);
        }
    }

    private static Set<String> entityTags(Entity entity) {
        return entity.entityTags();
    }
}
