package jaymanh.optools.Items;

import jaymanh.optools.OpTools;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItems {
    public static final DeferredItem<Item> DIAMONDIUM_INGOT = OpTools.ITEMS.register("diamondium_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    public static final DeferredItem<Item> DIAMONDILLIUM_INGOT = OpTools.ITEMS.register("diamondillium_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
    public static final DeferredItem<Item> RAW_DARKMATTER = OpTools.ITEMS.register("raw_darkmatter",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> REFINED_DARKMATTER = OpTools.ITEMS.register("refined_darkmatter",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
}