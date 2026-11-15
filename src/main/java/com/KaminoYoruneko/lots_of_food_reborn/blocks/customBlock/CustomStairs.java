package com.KaminoYoruneko.lots_of_food_reborn.blocks.customBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.*;

public class CustomStairs extends StairBlock {
    //传入注册类中的注册方块类型
    public CustomStairs(DeferredBlock<Block> MaterialBlock) {
        super(MaterialBlock.get().defaultBlockState(),
                BlockBehaviour.Properties.ofFullCopy(MaterialBlock.get())
        );
    }
}
