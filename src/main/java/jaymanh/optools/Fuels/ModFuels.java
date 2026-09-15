package jaymanh.optools.Fuels;

import jaymanh.optools.OpTools;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.neoforged.neoforge.registries.DeferredItem;

import static jaymanh.optools.Tools.ModTools.key;

public class ModFuels {
    public static final DeferredItem<Item> SUPER_FUEL = OpTools.ITEMS.register("super_fuel",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .setId(key("super_fuel"))
                    .component(DataComponents.COOKING_FUEL, new CookingFuel(
                            new ResolvableInt.Constant(20 * 640),
                            ResolvableFloat.fromKey(ContextFloatProviders.COOKING_DEFAULT_SPEED_MULTIPLIER)
                    ))));
}