//package com.KaminnoYoruneko.lots_of_food_reborn.trees.conf;
//
//import com.mojang.serialization.Codec;
//import net.minecraft.core.BlockPos;
//import net.minecraft.util.RandomSource;
//import net.minecraft.util.valueproviders.ConstantInt;
//import net.minecraft.world.level.LevelSimulatedReader;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
//import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
//import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
//import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
//import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
//import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
//import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
//import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
//import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
//import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
//import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
//import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.BiConsumer;
//
//public class PalmTreeConfiguration extends TreeConfiguration{
//    protected PalmTreeConfiguration(
//            BlockStateProvider provider, TrunkPlacer trunkPlacer,
//            BlockStateProvider blockStateProvider, FoliagePlacer foliagePlacer,
//            Optional<RootPlacer> optional, BlockStateProvider blockStateProvider2,
//            FeatureSize featureSize, List<TreeDecorator> decList, boolean bool1, boolean bool2) {
//        super(provider, trunkPlacer, blockStateProvider, foliagePlacer, optional, blockStateProvider2, featureSize, decList, bool1, bool2);
//        BlockStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState())/*树干*/,
//                new StraightTrunkPlacer(2,5,2)/*树干高度，最小最大基础*/,
//                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState())/*树叶*/,
//                new PalmTreeFoliagePlacer(ConstantInt.of(4),ConstantInt.of(0),2),
////                            new BlobFoliagePlacer(ConstantInt.of(4),ConstantInt.of(0),2),
//                new TwoLayersFeatureSize(1,0,2)/*检测生成空间是否足够*/
//        }
//
//
//}