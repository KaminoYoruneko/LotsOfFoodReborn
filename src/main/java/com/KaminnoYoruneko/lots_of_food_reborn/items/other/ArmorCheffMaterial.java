package com.KaminnoYoruneko.lots_of_food_reborn.items.other;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorCheffMaterial implements ArmorMaterial {

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return 2;  // 盔甲值
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot p_40410_) {
        return 100;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;  // 附魔值
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;  // 装备时的声音
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Items.WHITE_WOOL);  // 修复物品
    }

    @Override
    public String getName() {
        return "lots_of_food_reborn:cheff_hat";  // 材质名称
    }

    @Override
    public float getToughness() {
        return 0.0F;  // 防护力
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;  // 击退抗性
    }
}

