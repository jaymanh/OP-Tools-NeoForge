package jaymanh.optools.Foods;

import jaymanh.optools.OpTools;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModFoodItems {
    public static final DeferredItem<Item> SUPER_MEAT = OpTools.ITEMS.register("super_meat",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).food(new FoodProperties.Builder()
                    .nutrition(20)
                    .saturationModifier(20)
                    .build())));
}