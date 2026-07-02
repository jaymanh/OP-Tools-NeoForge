package jaymanh.optools.Enchantments;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static jaymanh.optools.OpTools.LOGGER;

public class EnchantTick {
    public static void initialise() {
    }

    @SubscribeEvent
    public void onEndLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel world)) {
            return;
        }
        List<? extends LivingEntity> entitiesList = world.getEntities(EntityTypeTest.forClass(LivingEntity.class), EntitySelector.ENTITY_STILL_ALIVE);
        for (Entity entity : entitiesList) {
            Set<String> tags = new HashSet<>(entity.entityTags());
            for (String cTag : tags) {
                if (Objects.equals(cTag, "Gravity")) {
                    for (String cTags : tags) {
                        if (cTags.startsWith("Num")) {
                            String numPart = cTags.substring(3);
                            int currentNum;
                            try {
                                currentNum = Integer.parseInt(numPart);
                            } catch (NumberFormatException e) {
                                LOGGER.error("Invalid number format for tag: {}", cTags);
                                continue;
                            }

                            if (currentNum == 100) {
                                entity.setNoGravity(false);
                                entity.removeTag("Num" + numPart);
                                entity.removeTag("Gravity");
                            } else {
                                entity.removeTag("Num" + numPart);
                                int nextNum = currentNum + 1;
                                entity.addTag("Num" + nextNum);
                            }
                        }
                    }
                }
            }
        }
    }
}