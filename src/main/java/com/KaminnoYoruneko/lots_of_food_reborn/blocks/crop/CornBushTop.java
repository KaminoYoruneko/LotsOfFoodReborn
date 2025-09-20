package com.KaminnoYoruneko.lots_of_food_reborn.blocks.crop;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock.CropAge3;
import com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister;
import com.KaminnoYoruneko.lots_of_food_reborn.register.CropRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CornBushTop extends CropAge3 {
    @Override
    protected ItemLike getBaseSeedId() {
        return CropRegister.corn.get();
    }

    @Override
    protected boolean mayPlaceOn(BlockState bs, BlockGetter getter, BlockPos bp) {
        return bs.is(CropRegister.cornBush.get());
    }
}
