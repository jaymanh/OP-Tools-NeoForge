package jaymanh.optools.GUI.Screen;

import jaymanh.optools.OpTools;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModScreenHandlers {
    public static final DeferredHolder<MenuType<?>, MenuType<RefineryScreenHandler>> REFINERY_SCREEN_HANDLER =
            OpTools.MENU_TYPES.register("refinery", () -> IMenuTypeExtension.create(RefineryScreenHandler::new));
}