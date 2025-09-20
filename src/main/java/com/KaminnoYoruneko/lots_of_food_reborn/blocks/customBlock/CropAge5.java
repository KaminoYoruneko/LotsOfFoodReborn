package com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CropAge5 extends CropBlock {
    public static final int maxAge=5;
    public static final IntegerProperty AGE=IntegerProperty.create("age",0,
            maxAge
    );

    public CropAge5() {
        super(Properties.copy(Blocks.POTATOES));
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_52302_, BlockGetter p_52303_, BlockPos p_52304_) {
//                        return super.mayPlaceOn(p_52302_, p_52303_, p_52304_);
        return p_52302_.is(Blocks.GRASS_BLOCK)||p_52302_.is(Blocks.FARMLAND);
    }

//    @Override
//    protected ItemLike getBaseSeedId() {
////        return seed.get();
////                        return chiliSeeds.get();
//    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
