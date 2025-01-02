package com.KaminnoYoruneko.lots_of_food_reborn.blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;

import java.util.function.Supplier;

import static com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister.chocolateBlock;

public class ChocolateStairs extends StairBlock {
    public ChocolateStairs() {
        super(chocolateBlock.get().defaultBlockState(),
                BlockBehaviour.Properties.copy(chocolateBlock.get())
        );
    }
}
