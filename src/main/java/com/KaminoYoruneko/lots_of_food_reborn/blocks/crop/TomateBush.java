package com.KaminoYoruneko.lots_of_food_reborn.blocks.crop;

import com.KaminoYoruneko.lots_of_food_reborn.blocks.customBlock.CropAge5;
import com.KaminoYoruneko.lots_of_food_reborn.register.CropRegister;
import net.minecraft.world.level.ItemLike;

public class TomateBush extends CropAge5 {
    @Override
    protected ItemLike getBaseSeedId() {
        return CropRegister.tomateSeeds.get();
    }
}
