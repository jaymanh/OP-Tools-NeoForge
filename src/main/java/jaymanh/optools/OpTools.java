package jaymanh.optools;

import com.mojang.logging.LogUtils;
import jaymanh.optools.Armor.ModArmor;
import jaymanh.optools.Blocks.BlockEntitys.ModBlockEntitys;
import jaymanh.optools.Blocks.ModBlocks;
import jaymanh.optools.Enchantments.*;
import jaymanh.optools.Foods.ModFoodItems;
import jaymanh.optools.Fuels.ModFuels;
import jaymanh.optools.GUI.Screen.ModScreenHandlers;
import jaymanh.optools.Items.ModItems;
import jaymanh.optools.Tools.ModTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod(OpTools.MOD_ID)
public class OpTools {
	public static final String MOD_ID = "op_tools";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

	private static final Map<Identifier, CodeRunner> registry = new HashMap<>();

	public static void register(Identifier hook, CodeRunner code) {
		registry.put(hook, code);
	}

	@FunctionalInterface
	public interface CodeRunner {
		void run(ServerLevel world, int level, EnchantedItemInUse context, Entity user, Vec3 pos);
	}

	public static CodeRunner get(Identifier hook) {
		return registry.get(hook);
	}

	public OpTools(IEventBus modEventBus) {
		LOGGER.info("Loading {}", MOD_ID);

		ensureDeferredRegistrations();

		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
		BLOCK_ENTITY_TYPES.register(modEventBus);
		MENU_TYPES.register(modEventBus);
		CREATIVE_TABS.register(modEventBus);

		modEventBus.addListener(this::registerEnchantmentEffects);

		TreeBreakerEnchantment.initialise();
		AutoRepairEnchantment.initialise();
		AutoReplantEnchantment.initialise();
		ElementalDamageEnchantments.initialise();
		EnchantTick.initialise();
		EnchantEventHandler.initialise();

		NeoForge.EVENT_BUS.register(new AutoRepairEnchantment());
		NeoForge.EVENT_BUS.register(new EnchantTick());
		NeoForge.EVENT_BUS.register(new EnchantEventHandler());
		NeoForge.EVENT_BUS.addListener(ModFuels::registerFuelBurnTime);
	}

	private static void ensureDeferredRegistrations() {
		ModTools.DIAMONDIUM_SWORD.getId();
		ModTools.OP_TOOLS_ITEM_GROUP.getId();
		ModBlocks.REFINERY.getId();
		ModItems.DIAMONDIUM_INGOT.getId();
		ModArmor.DIAMONDIUM_HELMET.getId();
		ModFoodItems.SUPER_MEAT.getId();
		ModFuels.SUPER_FUEL.getId();
		ModBlockEntitys.REFINERY_BLOCK_ENTITY.getId();
		ModScreenHandlers.REFINERY_SCREEN_HANDLER.getId();
	}

	private void registerEnchantmentEffects(RegisterEvent event) {
		if (event.getRegistryKey().equals(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE)) {
			event.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "run_code"), () -> ModEnchantmentsRunCode.CODEC);
		}
	}
}