package com.KaminoYoruneko.lots_of_food_reborn.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SugarBricks extends Block /*implements registerable */{
//    @Override
//    public DeferredBlock<Block> BlockRegister(DeferredRegister.Blocks reg, String name) {
//        return reg.register(name, SugarBricks::new);
//    }
    public SugarBricks() {
        super(BlockBehaviour.Properties.of()
                .strength(0.2f)
                .explosionResistance(0.2f)
                .sound(SoundType.WOOL)
        );
    }
}
