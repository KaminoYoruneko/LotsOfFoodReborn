package com.KaminnoYoruneko.lots_of_food_reborn.items.food;

import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import com.KaminnoYoruneko.lots_of_food_reborn.util.registerable;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

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
