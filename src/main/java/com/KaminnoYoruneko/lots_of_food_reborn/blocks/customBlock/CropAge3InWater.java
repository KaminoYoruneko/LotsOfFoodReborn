package com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CropAge3InWater extends CropBlock implements LiquidBlockContainer {
    public static final int maxAge=3;
    public static final IntegerProperty PICKLES = BlockStateProperties.PICKLES;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty AGE=IntegerProperty.create("age",0,
            maxAge
    );

    public CropAge3InWater() {
        super(BlockBehaviour.Properties.of(
                Material.WATER_PLANT
            ).noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.WET_GRASS)
        );
    }
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D)
            , Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D)
            , Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D)
            , Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
    };

    public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_) {
        return SHAPE_BY_AGE[p_52297_.getValue(this.getAgeProperty())];
    }
    @Override
    protected boolean mayPlaceOn(BlockState bs, BlockGetter getter, BlockPos bp) {
//                        return super.mayPlaceOn(p_52302_, p_52303_, p_52304_);
        BlockState upper=getter.getBlockState(bp.above());

        if (!upper.is(Blocks.WATER)){
            return false;
        }

        return bs.is(Blocks.SAND)
                ||bs.is(Blocks.GRAVEL)
                ||bs.is(Blocks.DIRT)
                ||bs.is(Blocks.FARMLAND);
    }

//    @Override
//    protected ItemLike getBaseSeedId() {
//        return seed.get();
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
        builder.add(PICKLES,WATERLOGGED);
        builder.add(AGE);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter p_54766_, BlockPos p_54767_, BlockState p_54768_, Fluid p_54769_) {
        return true;
    }

    @Override
    public boolean placeLiquid(LevelAccessor p_54770_, BlockPos p_54771_, BlockState p_54772_, FluidState p_54773_) {
        return true;
    }

    public FluidState getFluidState(BlockState p_56131_) {
        return p_56131_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_56131_);
    }
    public BlockState updateShape(BlockState p_56113_, Direction p_56114_, BlockState p_56115_, LevelAccessor p_56116_, BlockPos p_56117_, BlockPos p_56118_) {
        if (!p_56113_.canSurvive(p_56116_, p_56117_)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (p_56113_.getValue(WATERLOGGED)) {
                p_56116_.scheduleTick(p_56117_, Fluids.WATER, Fluids.WATER.getTickDelay(p_56116_));
            }

            return super.updateShape(p_56113_, p_56114_, p_56115_, p_56116_, p_56117_, p_56118_);
        }
    }

//    public boolean canBeReplaced(BlockState p_56101_, BlockPlaceContext p_56102_) {
//        return !p_56102_.isSecondaryUseActive() && p_56102_.getItemInHand().is(this.asItem()) && p_56101_.getValue(PICKLES) < 4 ? true : super.canBeReplaced(p_56101_, p_56102_);
//    }

//    @Override
//    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
//        // 持续验证水源存在
//        BlockPos waterPos = pos;
//        return world.getBlockState(waterPos).getBlock() == Blocks.WATER &&
//                super.canSurvive(state, world, pos);
//    }

}
