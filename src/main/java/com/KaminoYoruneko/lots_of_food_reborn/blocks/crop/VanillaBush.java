package com.KaminoYoruneko.lots_of_food_reborn.blocks.crop;

import com.KaminoYoruneko.lots_of_food_reborn.blocks.customBlock.CropAge4;
import com.KaminoYoruneko.lots_of_food_reborn.register.CropRegister;
import net.minecraft.world.level.ItemLike;

public class VanillaBush extends CropAge4 {
    @Override
    protected ItemLike getBaseSeedId() {
        return CropRegister.vanilla.get();
    }
}
