package jaymanh.optools.Armor;

import jaymanh.optools.OpTools;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;

import static jaymanh.optools.Tools.ModTools.key;

public class ModArmor {
    public static final DeferredItem<Item> DIAMONDIUM_HELMET = OpTools.ITEMS.register("diamondium_helmet",
            () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.DIAMONDIUM, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(ModArmorMaterials.DIAMONDIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.EPIC).setId(key("diamondium_helmet"))));
    public static final DeferredItem<Item> DIAMONDIUM_CHESTPLATE = OpTools.ITEMS.register("diamondium_chestplate",
            () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.DIAMONDIUM, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(ModArmorMaterials.DIAMONDIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.EPIC).setId(key("diamondium_chestplate"))));
    public static final DeferredItem<Item> DIAMONDIUM_LEGGINGS = OpTools.ITEMS.register("diamondium_leggings",
            () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.DIAMONDIUM, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(ModArmorMaterials.DIAMONDIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.EPIC).setId(key("diamondium_leggings"))));
    public static final DeferredItem<Item> DIAMONDIUM_BOOTS = OpTools.ITEMS.register("diamondium_boots",
            () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.DIAMONDIUM, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(ModArmorMaterials.DIAMONDIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.EPIC).setId(key("diamondium_boots"))));

    public static final DeferredItem<Item> DIAMONDILLIUM_HELMET = OpTools.ITEMS.register("diamondillium_helmet",
            () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.DIAMONDILLIUM, ArmorType.HELMET).durability(ArmorType.HELMET.getDurability(ModArmorMaterials.DIAMONDILLIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.RARE).setId(key("diamondillium_helmet"))));
    public static final DeferredItem<Item> DIAMONDILLIUM_CHESTPLATE = OpTools.ITEMS.register("diamondillium_chestplate",
            () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.DIAMONDILLIUM, ArmorType.CHESTPLATE).durability(ArmorType.CHESTPLATE.getDurability(ModArmorMaterials.DIAMONDILLIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.RARE).setId(key("diamondillium_chestplate"))));
    public static final DeferredItem<Item> DIAMONDILLIUM_LEGGINGS = OpTools.ITEMS.register("diamondillium_leggings",
            () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.DIAMONDILLIUM, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(ModArmorMaterials.DIAMONDILLIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.RARE).setId(key("diamondillium_leggings"))));
    public static final DeferredItem<Item> DIAMONDILLIUM_BOOTS = OpTools.ITEMS.register("diamondillium_boots",
            () -> new Item(new Item.Properties().humanoidArmor(ModArmorMaterials.DIAMONDILLIUM, ArmorType.BOOTS).durability(ArmorType.BOOTS.getDurability(ModArmorMaterials.DIAMONDILLIUM_DURABILITY_MULTIPLIER)).fireResistant().rarity(Rarity.RARE).setId(key("diamondillium_boots"))));
}