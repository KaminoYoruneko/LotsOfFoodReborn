package com.KaminnoYoruneko.lots_of_food_reborn.trees;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

public class CustomPalmTreeFeature extends Feature<TreeConfiguration> {

    public CustomPalmTreeFeature() {
        super(TreeConfiguration.CODEC);
    }

//    @Override
//    public boolean place(LevelAccessor world, ChunkGenerator chunkGenerator, RandomSource random, BlockPos position, TreeConfiguration config) {
//        TrunkPlacer trunkPlacer = config.trunkPlacer();
//        FoliagePlacer foliagePlacer = config.foliagePlacer();
//
//        // 生成树干
//        boolean trunkGenerated = trunkPlacer.place(world, random, position, config);
//
//        if (trunkGenerated) {
//            // 手动调用 createFoliage 来生成树叶
//            foliagePlacer.createFoliage(world, (pos, state) -> {
//                if (world.isStateAtPosition(pos, BlockState::isAir)) {
//                    world.setBlock(pos, state, 3);
//                }
//            }, random, config, position.getY(), FoliagePlacer.FoliageAttachment.top(position), config.foliageHeight(random, position.getY(), config), 4, 0);
//        }
//
//        return trunkGenerated;
//    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> p_159749_) {
        WorldGenLevel level = p_159749_.level();
        RandomSource random = p_159749_.random();
        BlockPos position = p_159749_.origin();
        TreeConfiguration config = p_159749_.config();

        // 计算树干的高度、叶子的高度和半径
        int treeHeight = config.trunkPlacer.getTreeHeight(random);
        int foliageHeight = config.foliagePlacer.foliageHeight(random, treeHeight, config);
        int trunkHeight = treeHeight - foliageHeight;
        int foliageRadius = config.foliagePlacer.foliageRadius(random, trunkHeight);

        // 创建根位置（即树木的底部位置）
        BlockPos rootPosition = config.rootPlacer
                .map(placer -> placer.getTrunkOrigin(position, random))
                .orElse(position);

        // 强行调用 createFoliage
        config.foliagePlacer.createFoliage(level, (pos, state) -> {
            level.setBlock(pos, state, 19);
        }, random, config, trunkHeight, new FoliagePlacer.FoliageAttachment(rootPosition, 0, false), foliageHeight, foliageRadius);

        return true; // 返回 true 表示生成成功
    }

}
