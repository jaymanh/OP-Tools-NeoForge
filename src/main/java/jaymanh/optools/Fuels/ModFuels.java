package jaymanh.optools.Fuels;

import jaymanh.optools.OpTools;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModFuels {
    public static final DeferredItem<Item> SUPER_FUEL = OpTools.ITEMS.register("super_fuel",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

    public static void registerFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().is(SUPER_FUEL.get())) {
            event.setBurnTime(20 * 640);
        }
    }
}