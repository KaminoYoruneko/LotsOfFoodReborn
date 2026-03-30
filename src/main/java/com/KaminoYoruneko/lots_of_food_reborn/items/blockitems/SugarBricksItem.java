package com.KaminoYoruneko.lots_of_food_reborn.items.blockitems;

import com.KaminoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import static com.KaminoYoruneko.lots_of_food_reborn.register.BlockRegister.sugarBricks;

public class SugarBricksItem extends BlockItem {
    public SugarBricksItem() {
        super(sugarBricks.get(),
                new Item.Properties()
                        .tab(MOD_TAB.TAB_BLOCK)
        );
    }
}
