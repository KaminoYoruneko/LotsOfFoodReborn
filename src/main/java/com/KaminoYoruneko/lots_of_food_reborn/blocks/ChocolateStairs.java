package com.KaminoYoruneko.lots_of_food_reborn.blocks;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static com.KaminoYoruneko.lots_of_food_reborn.register.BlockRegister.chocolateBlock;

public class ChocolateStairs extends StairBlock {
    public ChocolateStairs() {
        super(chocolateBlock.get().defaultBlockState(),
                BlockBehaviour.Properties.copy(chocolateBlock.get())
        );
    }
}
