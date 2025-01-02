package com.KaminnoYoruneko.lots_of_food_reborn.world.gen;

import com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource   ;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraft.world.level.levelgen.feature.WorldGenFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
//import net.minecraft.world.level.levelgen.feature.TreeFeatureConfig;

import java.util.List;
import java.util.Random;

public class WorldGenPalm extends Feature<TreeConfiguration> {
    public WorldGenPalm(Codec codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext context) {
        // 获取当前世界对象
        Level world = context.level().getLevel();
        // 获取生成位置
        BlockPos pos = context.origin();
        RandomSource rand = context.random();
        // 获取配置的树配置（可以设置树的基本配置，例如木材、叶子、空隙等）
        FeatureConfiguration config = context.config();//???????????

        // 判断沙子上是否可以种植
        if (/*!this.isSoilAt(world, pos)||*/!world.getBlockEntity(pos).equals(Blocks.SAND)) {
            return false;
        }

        // 生成树的高度和方向
        int palmHeight = 7 + rand.nextInt(5); // 高度为 7 到 12
        BlockPos currentPos = pos.above();

        // 生成树干
        for (int i = 0; i < palmHeight; i++) {
            world.setBlock(currentPos, Blocks.OAK_LOG.defaultBlockState(), 3);
            currentPos = currentPos.above();
        }

        // 生成树叶（在顶端四个方向扩展）
        BlockPos topPos = currentPos.above();  // 顶端位置
        this.generateLeaves(world, topPos, rand);

        // 生成椰子方块（随机选择 2 至 4 个位置生成椰子）
        this.generateCoconuts(world, topPos, rand);

        return true;
    }

    private boolean isSoilAt(Level world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.below());
        return blockState.is(Blocks.SAND);
    }

    private void generateLeaves(Level world, BlockPos topPos, RandomSource rand) {
        // 在顶部扩展树叶
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (Math.abs(dx) + Math.abs(dz) <= 2) {  // 确保在 4 方向生成叶子
                    BlockPos leafPos = topPos.offset(dx, 0, dz);
                    world.setBlock(leafPos, Blocks.OAK_LEAVES.defaultBlockState(), 3);
                }
            }
        }
    }

    private void generateCoconuts(Level world, BlockPos topPos, RandomSource rand) {
        // 随机生成椰子（2 到 4 个）
        int coconutsToGenerate = rand.nextInt(3) + 2;
        for (int i = 0; i < coconutsToGenerate; i++) {
            int offsetX = rand.nextInt(3) - 1; // 随机偏移 -1 到 1
            int offsetZ = rand.nextInt(3) - 1; // 随机偏移 -1 到 1
            BlockPos coconutPos = topPos.offset(offsetX, -1, offsetZ);
            world.setBlock(coconutPos, BlockRegister.coconutBlock.get().defaultBlockState(), 3);
        }
    }
}
