package com.KaminoYoruneko.lots_of_food_reborn.items.other;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;

public class ArmourCheff extends Item implements Equipable {
    public ArmourCheff() {
        super(new Item.Properties()
                .stacksTo(1)
                .durability(100));
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
