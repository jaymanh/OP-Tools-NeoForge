package jaymanh.optools.Armor;

import jaymanh.optools.OpTools;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModArmor {
    public static final DeferredItem<Item> DIAMONDIUM_HELMET = OpTools.ITEMS.register("diamondium_helmet",
            () -> new ArmorItem(ModArmorMaterials.DIAMONDIUM, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(ModArmorMaterials.DIAMONDIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> DIAMONDIUM_CHESTPLATE = OpTools.ITEMS.register("diamondium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.DIAMONDIUM, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(ModArmorMaterials.DIAMONDIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> DIAMONDIUM_LEGGINGS = OpTools.ITEMS.register("diamondium_leggings",
            () -> new ArmorItem(ModArmorMaterials.DIAMONDIUM, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(ModArmorMaterials.DIAMONDIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> DIAMONDIUM_BOOTS = OpTools.ITEMS.register("diamondium_boots",
            () -> new ArmorItem(ModArmorMaterials.DIAMONDIUM, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(ModArmorMaterials.DIAMONDIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> DIAMONDILLIUM_HELMET = OpTools.ITEMS.register("diamondillium_helmet",
            () -> new ArmorItem(ModArmorMaterials.DIAMONDILLIUM, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(ModArmorMaterials.DIAMONDILLIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> DIAMONDILLIUM_CHESTPLATE = OpTools.ITEMS.register("diamondillium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.DIAMONDILLIUM, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(ModArmorMaterials.DIAMONDILLIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> DIAMONDILLIUM_LEGGINGS = OpTools.ITEMS.register("diamondillium_leggings",
            () -> new ArmorItem(ModArmorMaterials.DIAMONDILLIUM, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(ModArmorMaterials.DIAMONDILLIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> DIAMONDILLIUM_BOOTS = OpTools.ITEMS.register("diamondillium_boots",
            () -> new ArmorItem(ModArmorMaterials.DIAMONDILLIUM, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(ModArmorMaterials.DIAMONDILLIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.RARE)));
}