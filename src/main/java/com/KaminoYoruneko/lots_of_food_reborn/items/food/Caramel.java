package com.KaminoYoruneko.lots_of_food_reborn.items.food;

import com.KaminoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class Caramel extends Item /*implements registerable*/ {

    public Caramel() {
        super(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(1)
                        .alwaysEat()
                        .fast()
                        .build()
                )
                .tab(MOD_TAB.TAB_DESSERTS));
    }

//    @Override
//    public RegistryObject<Item> ItemRegister(DeferredRegister<Item> reg, String name) {
//        return reg.register(name,Caramel::new);
//    }
}
