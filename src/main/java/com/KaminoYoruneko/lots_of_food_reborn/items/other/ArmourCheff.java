package com.KaminoYoruneko.lots_of_food_reborn.items.other;

import com.KaminoYoruneko.lots_of_food_reborn.register.FeatureRegistration;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ArmourCheff extends ArmorItem {
    public ArmourCheff() {
        super(FeatureRegistration.CHEFF_HAT_MATERIAL, ArmorItem.Type.HELMET,
                new Item.Properties()
                        .stacksTo(1)
                        .durability(100));
    }
}
