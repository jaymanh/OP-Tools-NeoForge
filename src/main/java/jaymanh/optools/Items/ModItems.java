package jaymanh.optools.Items;

import jaymanh.optools.OpTools;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;

import static jaymanh.optools.Tools.ModTools.key;

public class ModItems {
    public static final DeferredItem<Item> DIAMONDIUM_INGOT = OpTools.ITEMS.register("diamondium_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).fireResistant().setId(key("diamondium_ingot"))));
    public static final DeferredItem<Item> DIAMONDILLIUM_INGOT = OpTools.ITEMS.register("diamondillium_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant().setId(key("diamondillium_ingot"))));
    public static final DeferredItem<Item> RAW_DARKMATTER = OpTools.ITEMS.register("raw_darkmatter",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).setId(key("raw_darkmatter"))));
    public static final DeferredItem<Item> REFINED_DARKMATTER = OpTools.ITEMS.register("refined_darkmatter",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).setId(key("refined_darkmatter"))));
}