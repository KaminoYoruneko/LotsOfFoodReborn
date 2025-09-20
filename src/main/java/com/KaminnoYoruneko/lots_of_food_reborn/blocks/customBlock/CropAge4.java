package com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CropAge4 extends CropBlock {
    public static final int maxAge=4;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D)
            , Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D)
            , Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)
            , Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D)
            , Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
    };

    public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_) {
        return SHAPE_BY_AGE[p_52297_.getValue(this.getAgeProperty())];
    }
    public static final IntegerProperty AGE=IntegerProperty.create("age",0,
            maxAge
    );

    public CropAge4() {
        super(BlockBehaviour.Properties.copy(Blocks.WHEAT));
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
