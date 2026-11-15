package com.KaminoYoruneko.lots_of_food_reborn.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

/**
 * 厨师帽的客户端扩展：盔甲层渲染时替换成自定义帽子模型。
 */
public final class ChefHatClientExtension implements IClientItemExtensions {
    @Override
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack,
                                                  EquipmentSlot slot, HumanoidModel<?> original) {
        return new ChefHatArmorModel(
                Minecraft.getInstance().getEntityModels().bakeLayer(ChefHatArmorModel.LAYER));
    }
}
