package jaymanh.optools.Tools;

import static jaymanh.optools.Tools.TagKeys.ItemKeys.DIAMONDILLIUM_REPAIR_ITEMS;
import static jaymanh.optools.Tools.TagKeys.ItemKeys.DIAMONDIUM_REPAIR_ITEMS;
import static net.minecraft.tags.BlockTags.INCORRECT_FOR_DIAMOND_TOOL;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public enum ToolMaterials implements Tier {
    DIAMONDILLIUM(4096, 20f, 8f, 30, DIAMONDILLIUM_REPAIR_ITEMS),
    DIAMONDIUM(8192, 30f, 10f, 30, DIAMONDIUM_REPAIR_ITEMS);

    private final int uses;
    private final float speed;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final TagKey<Item> repairTag;

    ToolMaterials(int uses, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairTag) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantmentValue = enchantmentValue;
        this.repairTag = repairTag;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamageBonus;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return INCORRECT_FOR_DIAMOND_TOOL;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(this.repairTag);
    }
}