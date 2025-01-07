package com.KaminnoYoruneko.lots_of_food_reborn.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class CoconutBlock extends LeavesBlock {
    public CoconutBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOD)
                .strength(1.2f)
                .explosionResistance(2.0f)
                .sound(SoundType.WOOD)
                .noOcclusion()
        );
    }

    @Override
    public boolean hidesNeighborFace(BlockGetter level, BlockPos pos, BlockState state, BlockState neighborState, Direction dir) {

        return false;//super.hidesNeighborFace(level, pos, state, neighborState, dir);
    }
}
