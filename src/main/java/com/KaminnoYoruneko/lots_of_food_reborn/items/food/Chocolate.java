package com.KaminnoYoruneko.lots_of_food_reborn.items.food;

import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class Chocolate extends Item {
    public Chocolate() {
        super(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(6)
                        .saturationMod(5)
                        .alwaysEat()
                        .build()
                )
                .tab(MOD_TAB.TAB_FOOD));
    }
}
