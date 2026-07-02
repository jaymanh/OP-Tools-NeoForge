package jaymanh.optools.Tools;

import jaymanh.optools.Armor.ModArmor;
import jaymanh.optools.Blocks.ModBlocks;
import jaymanh.optools.Foods.ModFoodItems;
import jaymanh.optools.Fuels.ModFuels;
import jaymanh.optools.Items.ModItems;
import jaymanh.optools.OpTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import static jaymanh.optools.OpTools.MOD_ID;

public class ModTools {
    public static ResourceKey<Item> key(String id) {
        Identifier itemId = Identifier.fromNamespaceAndPath(MOD_ID, id);
        return ResourceKey.create(Registries.ITEM, itemId);
    }

    public static final DeferredItem<Item> DIAMONDIUM_SWORD = OpTools.ITEMS.register("diamondium_sword",
            () -> new Item(new Item.Properties().sword(ToolMaterials.DIAMONDIUM.getMaterial(), 12, 0.6f).rarity(Rarity.EPIC).fireResistant().setId(key("diamondium_sword"))));
    public static final DeferredItem<Item> DIAMONDIUM_PICKAXE = OpTools.ITEMS.register("diamondium_pickaxe",
            () -> new Item(new Item.Properties().pickaxe(ToolMaterials.DIAMONDIUM.getMaterial(), 5, 0.2f).rarity(Rarity.EPIC).fireResistant().setId(key("diamondium_pickaxe"))));
    public static final DeferredItem<Item> DIAMONDIUM_AXE = OpTools.ITEMS.register("diamondium_axe",
            () -> new Item(new Item.Properties().axe(ToolMaterials.DIAMONDIUM.getMaterial(), 15, 0.4f).rarity(Rarity.EPIC).fireResistant().setId(key("diamondium_axe"))));
    public static final DeferredItem<Item> DIAMONDIUM_SHOVEL = OpTools.ITEMS.register("diamondium_shovel",
            () -> new Item(new Item.Properties().shovel(ToolMaterials.DIAMONDIUM.getMaterial(), 8, 0.5f).rarity(Rarity.EPIC).fireResistant().setId(key("diamondium_shovel"))));
    public static final DeferredItem<Item> DIAMONDIUM_HOE = OpTools.ITEMS.register("diamondium_hoe",
            () -> new HoeItem(ToolMaterials.DIAMONDIUM.getMaterial(), 7, 1f, new Item.Properties().rarity(Rarity.EPIC).fireResistant().setId(key("diamondium_hoe"))));

    public static final DeferredItem<Item> DIAMONDILLIUM_SWORD = OpTools.ITEMS.register("diamondillium_sword",
            () -> new Item(new Item.Properties().sword(ToolMaterials.DIAMONDILLIUM.getMaterial(), 10, 0.5f).rarity(Rarity.RARE).fireResistant().setId(key("diamondillium_sword"))));
    public static final DeferredItem<Item> DIAMONDILLIUM_PICKAXE = OpTools.ITEMS.register("diamondillium_pickaxe",
            () -> new Item(new Item.Properties().pickaxe(ToolMaterials.DIAMONDILLIUM.getMaterial(), 5, 0.2f).rarity(Rarity.RARE).fireResistant().setId(key("diamondillium_pickaxe"))));
    public static final DeferredItem<Item> DIAMONDILLIUM_AXE = OpTools.ITEMS.register("diamondillium_axe",
            () -> new Item(new Item.Properties().axe(ToolMaterials.DIAMONDILLIUM.getMaterial(), 15, 0.4f).rarity(Rarity.RARE).fireResistant().setId(key("diamondillium_axe"))));
    public static final DeferredItem<Item> DIAMONDILLIUM_SHOVEL = OpTools.ITEMS.register("diamondillium_shovel",
            () -> new Item(new Item.Properties().shovel(ToolMaterials.DIAMONDILLIUM.getMaterial(), 8, 0.5f).rarity(Rarity.RARE).fireResistant().setId(key("diamondillium_shovel"))));
    public static final DeferredItem<Item> DIAMONDILLIUM_HOE = OpTools.ITEMS.register("diamondillium_hoe",
            () -> new HoeItem(ToolMaterials.DIAMONDILLIUM.getMaterial(), 6, 1f, new Item.Properties().rarity(Rarity.RARE).fireResistant().setId(key("diamondillium_hoe"))));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> OP_TOOLS_ITEM_GROUP = OpTools.CREATIVE_TABS.register("op_tools", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.op_tools"))
            .icon(() -> new ItemStack(DIAMONDIUM_SWORD.get()))
            .displayItems((parameters, output) -> {
                output.accept(DIAMONDILLIUM_SWORD.get());
                output.accept(DIAMONDILLIUM_PICKAXE.get());
                output.accept(DIAMONDILLIUM_AXE.get());
                output.accept(DIAMONDILLIUM_SHOVEL.get());
                output.accept(DIAMONDILLIUM_HOE.get());
                output.accept(DIAMONDIUM_SWORD.get());
                output.accept(DIAMONDIUM_PICKAXE.get());
                output.accept(DIAMONDIUM_AXE.get());
                output.accept(DIAMONDIUM_SHOVEL.get());
                output.accept(DIAMONDIUM_HOE.get());

                output.accept(ModArmor.DIAMONDIUM_HELMET.get());
                output.accept(ModArmor.DIAMONDIUM_CHESTPLATE.get());
                output.accept(ModArmor.DIAMONDIUM_LEGGINGS.get());
                output.accept(ModArmor.DIAMONDIUM_BOOTS.get());
                output.accept(ModArmor.DIAMONDILLIUM_HELMET.get());
                output.accept(ModArmor.DIAMONDILLIUM_CHESTPLATE.get());
                output.accept(ModArmor.DIAMONDILLIUM_LEGGINGS.get());
                output.accept(ModArmor.DIAMONDILLIUM_BOOTS.get());

                output.accept(ModItems.DIAMONDIUM_INGOT.get());
                output.accept(ModItems.DIAMONDILLIUM_INGOT.get());
                output.accept(ModItems.RAW_DARKMATTER.get());
                output.accept(ModItems.REFINED_DARKMATTER.get());

                output.accept(ModBlocks.DIAMONDIUM_BLOCK.get().asItem());
                output.accept(ModBlocks.DIAMONDILLIUM_BLOCK.get().asItem());
                output.accept(ModBlocks.STONE_DARKMATTER_ORE.get().asItem());
                output.accept(ModBlocks.DEEPSLATE_DARKMATTER_ORE.get().asItem());
                output.accept(ModBlocks.REFINERY.get().asItem());

                output.accept(ModFoodItems.SUPER_MEAT.get());
                output.accept(ModFuels.SUPER_FUEL.get());
            })
            .build());
}