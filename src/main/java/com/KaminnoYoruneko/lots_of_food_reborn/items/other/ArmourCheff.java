package com.KaminnoYoruneko.lots_of_food_reborn.items.other;

import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmourCheff extends ArmorItem {
//    ARMOUR_CHEFF = EnumHelper.addArmorMaterial("armour_cheff", "lotsoffoodrev:cheff", -1, new int[]{0, 0, 0, 0}
//        , 0, SoundEvents.field_187728_s, 0.0F);
    public ArmourCheff() {
        super(ArmorMaterials.LEATHER, EquipmentSlot.HEAD,new Properties().tab(MOD_TAB.TAB_DEBUG));
    }
}
