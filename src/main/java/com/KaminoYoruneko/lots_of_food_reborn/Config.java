package com.KaminoYoruneko.lots_of_food_reborn;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@EventBusSubscriber(modid = LOFR.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    // a list of strings that are treated as resource locations for items
    private static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty(Collections.singletonList("items"), () -> List.of("minecraft:iron_ingot"), Config::validateItemName);

    // --- 右键收割配置 ---
    private static final ModConfigSpec.BooleanValue ALLOW_RIGHT_CLICK_HARVEST = BUILDER
            .comment("是否开启右键收割功能。")
            .define("allowRightClickHarvest", true);

    private static final ModConfigSpec.BooleanValue HARVEST_DROPS_TO_INVENTORY = BUILDER
            .comment("收割产物是否直接进入玩家背包（若背包满则掉落在地上）。")
            .define("harvestDropsToInventory", true);

    private static final ModConfigSpec.EnumValue<HarvestScope> HARVEST_SCOPE = BUILDER
            .comment("右键收割的范围: MOD_ONLY (仅限本模组), VANILLA_AND_MOD (原版+本模组), ALL_CROPS (所有作物)。")
            .defineEnum("harvestScope", HarvestScope.MOD_ONLY);

    static final ModConfigSpec SPEC = BUILDER.build();

    public enum HarvestScope {
        MOD_ONLY, VANILLA_AND_MOD, ALL_CROPS
    }

    public static boolean logDirtBlock;
    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;
    public static boolean allowRightClickHarvest;
    public static boolean harvestDropsToInventory;
    public static HarvestScope harvestScope;

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();
        allowRightClickHarvest = ALLOW_RIGHT_CLICK_HARVEST.get();
        harvestDropsToInventory = HARVEST_DROPS_TO_INVENTORY.get();
        harvestScope = HARVEST_SCOPE.get();

        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemName)))
                .collect(Collectors.toSet());
    }
}
