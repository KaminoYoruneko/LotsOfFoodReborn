package com.KaminoYoruneko.lots_of_food_reborn.blockbase;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockBase extends Block {
    public BlockBase(String name, SoundType sound) {
        super(BlockBehaviour.Properties.of()
                .sound(sound));
    }
}
