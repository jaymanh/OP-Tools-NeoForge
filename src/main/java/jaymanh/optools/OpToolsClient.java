package jaymanh.optools;

import jaymanh.optools.GUI.Screen.ModScreenHandlers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@Mod(value = OpTools.MOD_ID, dist = Dist.CLIENT)
public class OpToolsClient {
    public OpToolsClient(IEventBus modEventBus) {
        modEventBus.addListener(this::registerScreens);
    }

    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModScreenHandlers.REFINERY_SCREEN_HANDLER.get(), RefineryScreen::new);
    }
}