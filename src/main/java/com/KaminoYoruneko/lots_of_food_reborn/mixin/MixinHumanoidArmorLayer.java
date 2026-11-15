package com.KaminoYoruneko.lots_of_food_reborn.mixin;

import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 旧版这里是空实现，1.21 先保留一个空的客户端 Mixin。
 * 厨师帽目前是可装备的普通物品，头部渲染后续再补。
 */
@Mixin(HumanoidArmorLayer.class)
public class MixinHumanoidArmorLayer {
}
