package com.KaminnoYoruneko.lots_of_food_reborn.blocks;

//import com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock.CustomSapling;
import com.KaminnoYoruneko.lots_of_food_reborn.trees.PalmTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class PalmTreeSapling extends SaplingBlock {
    public PalmTreeSapling() {
        super(new PalmTreeGrower(),
                BlockBehaviour.Properties.copy(Blocks.JUNGLE_SAPLING)
        );
    }
    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState blockStateBelow = world.getBlockState(pos.below());

        // 检查下方的方块是否为沙子
        return blockStateBelow.is(Blocks.SAND);
    }
}
