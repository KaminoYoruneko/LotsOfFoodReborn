package com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CustomStairs extends StairBlock {
    //传入注册类中的注册方块类型
    public CustomStairs(RegistryObject<Block> MaterialBlock) {
        super(MaterialBlock.get().defaultBlockState(),
                BlockBehaviour.Properties.copy(MaterialBlock.get())
        );
    }
}
