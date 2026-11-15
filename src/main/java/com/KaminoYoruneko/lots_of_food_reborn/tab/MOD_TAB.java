package com.KaminoYoruneko.lots_of_food_reborn.tab;

import com.KaminoYoruneko.lots_of_food_reborn.register.BlockRegister;
import com.KaminoYoruneko.lots_of_food_reborn.register.DrinkRegister;
import com.KaminoYoruneko.lots_of_food_reborn.register.ItemRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

public final class MOD_TAB {
    private MOD_TAB() {
    }

    public enum Tab {
        BLOCK, NOT_FOOD, MEAT, FRUITS, DESSERTS, DRINKS, MEALS, DEBUG
    }

    // 兼容旧代码里的 TAB_XXX 写法。
    public static final Tab TAB_BLOCK = Tab.BLOCK;
    public static final Tab TAB_NOT_FOOD = Tab.NOT_FOOD;
    public static final Tab TAB_MEAT = Tab.MEAT;
    public static final Tab TAB_FRUITS = Tab.FRUITS;
    public static final Tab TAB_DESSERTS = Tab.DESSERTS;
    public static final Tab TAB_DRINKS = Tab.DRINKS;
    public static final Tab TAB_MEALS = Tab.MEALS;
    public static final Tab TAB_DEBUG = Tab.DEBUG;

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    private static final Map<Tab, List<Supplier<ItemStack>>> CONTENTS = new EnumMap<>(Tab.class);

    static {
        for (Tab tab : Tab.values()) {
            CONTENTS.put(tab, new ArrayList<>());
        }
    }

    public static void add(Tab tab, Supplier<? extends Item> item) {
        CONTENTS.get(tab).add(() -> new ItemStack(item.get()));
    }

    public static void add(Tab tab, String itemName) {
        var id = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MODID, itemName);
        CONTENTS.get(tab).add(() -> new ItemStack(net.minecraft.core.registries.BuiltInRegistries.ITEM.get(id)));
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCK_TAB =
            register("tab_block", "TAB_BLOCK", Tab.BLOCK,
                    () -> new ItemStack(BlockRegister.caramelBlock.get()));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NOT_FOOD_TAB =
            register("tab_not_food", "TAB_NOT_FOOD", Tab.NOT_FOOD,
                    () -> new ItemStack(ItemRegister.coconut.get()));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MEAT_TAB =
            register("tab_meat", "TAB_MEAT", Tab.MEAT,
                    () -> new ItemStack(ItemRegister.rawHorse.get()));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FRUITS_TAB =
            register("tab_fruits", "TAB_FRUITS", Tab.FRUITS,
                    () -> new ItemStack(ItemRegister.caramel.get()));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DESSERTS_TAB =
            register("tab_desserts", "TAB_DESSERTS", Tab.DESSERTS,
                    () -> new ItemStack(ItemRegister.chocolateDonut.get()));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DRINKS_TAB =
            register("tab_drinks", "TAB_DRINKS", Tab.DRINKS,
                    () -> new ItemStack(DrinkRegister.cactiJuice.get()));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MEALS_TAB =
            register("tab_meals", "TAB_MEALS", Tab.MEALS,
                    () -> new ItemStack(ItemRegister.pasta.get()));
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEBUG_TAB =
            register("tab_debug", "TAB_DEBUG", Tab.DEBUG,
                    () -> new ItemStack(ItemRegister.cheff.get()));

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(
            String id, String translationKey, Tab tab, Supplier<ItemStack> icon) {
        return TABS.register(id, () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + translationKey))
                .icon(icon)
                .displayItems((params, output) -> CONTENTS.get(tab).forEach(supplier -> {
                    ItemStack stack = supplier.get();
                    if (!stack.isEmpty()) {
                        output.accept(stack);
                    }
                }))
                .build());
    }
}
