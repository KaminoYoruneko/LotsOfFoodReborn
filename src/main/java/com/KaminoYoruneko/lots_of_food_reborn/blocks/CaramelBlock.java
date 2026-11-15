package com.KaminoYoruneko.lots_of_food_reborn.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CaramelBlock extends Block /*implements registerable*/{
//    @Override
//    public DeferredBlock<Block> BlockRegister(DeferredRegister.Blocks reg, String name) {
//        return reg.register(name, CaramelBlock::new);
//    }
    public CaramelBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(1.0f)
                .explosionResistance(1.0f)
                .sound(SoundType.STONE)
        );
    }


}
