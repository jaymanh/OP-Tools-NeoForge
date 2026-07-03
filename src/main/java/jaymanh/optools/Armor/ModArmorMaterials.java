package jaymanh.optools.Armor;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;

import static jaymanh.optools.OpTools.MOD_ID;
import static jaymanh.optools.Tools.TagKeys.ItemKeys.DIAMONDILLIUM_REPAIR_ITEMS;
import static jaymanh.optools.Tools.TagKeys.ItemKeys.DIAMONDIUM_REPAIR_ITEMS;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MOD_ID);

    public static final int DIAMONDILLIUM_DURABILITY_MULTIPLIER = 256;
    public static final int DIAMONDIUM_DURABILITY_MULTIPLIER = 512;

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> DIAMONDILLIUM = ARMOR_MATERIALS.register("diamondillium",
            () -> new ArmorMaterial(
                    Map.of(
                            ArmorItem.Type.BOOTS, 5,
                            ArmorItem.Type.LEGGINGS, 9,
                            ArmorItem.Type.CHESTPLATE, 7,
                            ArmorItem.Type.HELMET, 4
                    ),
                    24,
                    SoundEvents.ARMOR_EQUIP_NETHERITE,
                    () -> Ingredient.of(DIAMONDILLIUM_REPAIR_ITEMS),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(MOD_ID, "diamondillium"), "", false)),
                    4.0F,
                    1.0F
            ));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> DIAMONDIUM = ARMOR_MATERIALS.register("diamondium",
            () -> new ArmorMaterial(
                    Map.of(
                            ArmorItem.Type.BOOTS, 7,
                            ArmorItem.Type.LEGGINGS, 15,
                            ArmorItem.Type.CHESTPLATE, 11,
                            ArmorItem.Type.HELMET, 6
                    ),
                    24,
                    SoundEvents.ARMOR_EQUIP_NETHERITE,
                    () -> Ingredient.of(DIAMONDIUM_REPAIR_ITEMS),
                    List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(MOD_ID, "diamondium"), "", false)),
                    6.0F,
                    1.5F
            ));
}