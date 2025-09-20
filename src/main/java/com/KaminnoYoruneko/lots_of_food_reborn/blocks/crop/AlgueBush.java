package com.KaminnoYoruneko.lots_of_food_reborn.blocks.crop;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock.CropAge3InWater;
import com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock.CropAge4;
import com.KaminnoYoruneko.lots_of_food_reborn.register.CropRegister;
import net.minecraft.world.level.ItemLike;

public class AlgueBush extends CropAge3InWater {
    @Override
    protected ItemLike getBaseSeedId() {
        return CropRegister.algue.get();
    }
}
