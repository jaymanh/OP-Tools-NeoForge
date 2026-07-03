package jaymanh.optools.Armor;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import static jaymanh.optools.OpTools.MOD_ID;

public class CustomArmorModels implements EquipmentAssets {
    public static final ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("equipment_asset"));

    public static final ResourceKey<EquipmentAsset> DIAMONDILLIUM = ResourceKey.create(REGISTRY_KEY,ResourceLocation.fromNamespaceAndPath(MOD_ID, "diamondillium"));
    public static final ResourceKey<EquipmentAsset>  DIAMONDIUM = ResourceKey.create(REGISTRY_KEY,ResourceLocation.fromNamespaceAndPath(MOD_ID, "diamondium"));

}