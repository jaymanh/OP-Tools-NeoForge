package jaymanh.optools.Enchantments;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.ArrayList;
import java.util.List;

public class EnchantEventHandler {

    public static void initialise() {
    }

    @SubscribeEvent
    public void onEndLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel serverWorld)) {
            return;
        }
        List<AutoReplantEnchantment.ReplantTask> tasksToProcess = new ArrayList<>();
        for (AutoReplantEnchantment.ReplantTask task : AutoReplantEnchantment.getReplantTasks()) {
            if (task.world() == serverWorld) {
                tasksToProcess.add(task);
            }
        }
        for (AutoReplantEnchantment.ReplantTask task : tasksToProcess) {
            serverWorld.setBlockAndUpdate(task.pos(), task.block().defaultBlockState());
            AutoReplantEnchantment.getReplantTasks().remove(task);
        }
    }
}