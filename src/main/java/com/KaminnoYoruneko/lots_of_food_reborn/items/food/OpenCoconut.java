package com.KaminnoYoruneko.lots_of_food_reborn.items.food;

import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class OpenCoconut extends Item {
    public OpenCoconut() {
        super(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(2)
                        .saturationMod(0.25f)
                        .alwaysEat()
                        .build()
                )
                .tab(MOD_TAB.TAB_DESSERTS));
    }
}
