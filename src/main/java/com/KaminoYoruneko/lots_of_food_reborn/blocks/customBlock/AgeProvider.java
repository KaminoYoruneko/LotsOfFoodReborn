package com.KaminoYoruneko.lots_of_food_reborn.blocks.customBlock;

import net.minecraft.world.level.block.state.properties.IntegerProperty;

/**
 * 1.21 的 CropBlock#getAgeProperty 是 protected，跨包访问时用这个接口暴露出来。
 */
public interface AgeProvider {
    IntegerProperty getAgeProperty();
}
