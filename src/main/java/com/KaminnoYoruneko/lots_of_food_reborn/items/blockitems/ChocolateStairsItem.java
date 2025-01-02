package com.KaminnoYoruneko.lots_of_food_reborn.items.blockitems;

import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import static com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister.chocolateStairs;
import static com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister.sugarBricks;

public class ChocolateStairsItem extends BlockItem {
    public ChocolateStairsItem() {
        super(chocolateStairs.get(),
                new Item.Properties()
                        .tab(MOD_TAB.TAB_BLOCK)
        );
    }
}