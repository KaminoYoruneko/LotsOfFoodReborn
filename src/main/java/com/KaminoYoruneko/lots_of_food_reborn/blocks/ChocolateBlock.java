package com.KaminoYoruneko.lots_of_food_reborn.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ChocolateBlock extends Block /*implements registerable*/ {
//    @Override
//    public DeferredBlock<Block> BlockRegister(DeferredRegister.Blocks reg, String name) {
//        return reg.register(name, ChocolateBlock::new);
//    }
    public ChocolateBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(1.0f)
                .explosionResistance(0.7f)
                .sound(SoundType.STONE)
        );
    }
}
