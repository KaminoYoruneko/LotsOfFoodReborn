package com.KaminnoYoruneko.lots_of_food_reborn.items.drink;

import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class Glass extends Item {
    public Glass() {
        super(new Item.Properties()
                .tab(MOD_TAB.TAB_DRINKS));
    }
}