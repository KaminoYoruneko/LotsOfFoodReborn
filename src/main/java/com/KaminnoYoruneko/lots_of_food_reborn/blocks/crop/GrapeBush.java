package com.KaminnoYoruneko.lots_of_food_reborn.blocks.crop;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock.CropAge4;
import com.KaminnoYoruneko.lots_of_food_reborn.register.CropRegister;
import net.minecraft.world.level.ItemLike;

public class GrapeBush extends CropAge4 {
    @Override
    protected ItemLike getBaseSeedId() {
        return CropRegister.grapes.get();
    }
}
