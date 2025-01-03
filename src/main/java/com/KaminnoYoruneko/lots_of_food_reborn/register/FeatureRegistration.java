package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.trees.CustomPalmTreeFeature;
import com.KaminnoYoruneko.lots_of_food_reborn.trees.PalmTreeGrower;
import com.KaminnoYoruneko.lots_of_food_reborn.trees.conf.PalmTreeFoliagePlacer;
import com.KaminnoYoruneko.lots_of_food_reborn.world.gen.WorldGenPalm;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class FeatureRegistration {
    public static final DeferredRegister<ConfiguredFeature<?,?>> FEATURES= DeferredRegister.create(
            Registry.CONFIGURED_FEATURE_REGISTRY, MODID
    );
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES= DeferredRegister.create(
            Registry.PLACED_FEATURE_REGISTRY, MODID
    );
    public static final int[] TREE_LEAVES_CONF={4,1,2};/*半径，偏移量，高度*/
//    public static final PalmTreeFoliagePlacer ptfp=
//            new PalmTreeFoliagePlacer(ConstantInt.of(3),ConstantInt.of(3),2);
//    public static final TreeConfiguration PALM_TREE_CONFIGURATION=
//        new TreeConfiguration.TreeConfigurationBuilder(
//            BlockStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState())/*树干*/,
//            new StraightTrunkPlacer(2,5,2)/*树干高度，最小最大基础*/,
//                            BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState())/*树叶*/,
//                new PalmTreeFoliagePlacer(ConstantInt.of(3),ConstantInt.of(3),2),
////                            new BlobFoliagePlacer(ConstantInt.of(4),ConstantInt.of(0),2),
//            new TwoLayersFeatureSize(1,0,2)/*检测生成空间是否足够*/
//        ).build();

    public static final RegistryObject<ConfiguredFeature<?,?>> palmTreeConfig=FEATURES.register(
            "palm_tree",
            ()->new ConfiguredFeature<>(
                    Feature.TREE,
//                    new CustomPalmTreeFeature(),
                    new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(Blocks.JUNGLE_LOG.defaultBlockState())/*树干*/,
                            new StraightTrunkPlacer(2,5,2)/*树干高度，最小最大基础*/,
                            BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState())/*树叶*/,
                            new PalmTreeFoliagePlacer(ConstantInt.of(3),ConstantInt.of(3),0),
//NEVER MOVE THIS 0
//                            new BlobFoliagePlacer(ConstantInt.of(4),ConstantInt.of(0),2),
                            new TwoLayersFeatureSize(1,4,10)/*检测生成空间是否足够*/
                    ).build()
            )
    );//此处树的傅里叶placer参数都不重要了，全部硬编码了，之后再改qwq

    //检测树是否可以生成
    public static final RegistryObject<PlacedFeature> palmTreeChecked=PLACED_FEATURES.register(
            "palm_tree_checked",
            ()->new PlacedFeature(
//                    palmTreeConfig.getHolder().get(),
                    PalmTreeGrower.getConf(),
                    List.of(PlacementUtils.filteredByBlockSurvival(BlockRegister.palmTreeSapling.get()))
            )
    );
    //树的生成方式（随机）
    public static final RegistryObject<ConfiguredFeature<?,?>> palmTreeSpawn=FEATURES.register(
            "palm_tree_spawn",
            ()->new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(
                            List.of(
                                    new WeightedPlacedFeature(palmTreeChecked.getHolder().get(),0.5F)
                            ),
                            palmTreeChecked.getHolder().get()
                    )
            )
    );
    //检测最多一次性生成多少树
    public static final RegistryObject<PlacedFeature> palmTreePlaced=PLACED_FEATURES.register(
            "palm_tree_placed",
            ()->new PlacedFeature(
                    palmTreeSpawn.getHolder().get(),
                    VegetationPlacements.treePlacement(PlacementUtils.countExtra(1,0.02f,0))
                    //放置数，生成概率，基础数目
            )
    );
//    //采用邪道手法
//    public static final RegistryObject<ConfiguredFeature<?,?>> palmSaplingConfig=FEATURES.register(
//            "palm_sapling",
//            ()->new ConfiguredFeature<>(
//                    Feature.TREE,
////                    new CustomPalmTreeFeature(),
//                    new TreeConfiguration.TreeConfigurationBuilder(
//                            BlockStateProvider.simple(BlockRegister.palmTreeSapling.get())/*树干*/,
//                            new StraightTrunkPlacer(1,1,1)/*树干高度，最小最大基础*/,
//                            BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState())/*树叶*/,
//                            new BlobFoliagePlacer(ConstantInt.of(0),ConstantInt.of(0),0),
//                            new TwoLayersFeatureSize(1,0,2)/*检测生成空间是否足够*/
//                    ).build()
//            )
//    );
//    public static final RegistryObject<PlacedFeature> palmSaplingChecked=PLACED_FEATURES.register(
//            "palm_sapling_checked",
//            ()->new PlacedFeature(
//                    palmSaplingConfig.getHolder().get(),
//                    List.of(PlacementUtils.filteredByBlockSurvival(BlockRegister.palmTreeSapling.get()))
//            )
//    );
//    public static final RegistryObject<ConfiguredFeature<?,?>> palmSaplingSpawn=FEATURES.register(
//            "palm_sapling_spawn",
//            ()->new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
//                    new RandomFeatureConfiguration(
//                            List.of(
//                                    new WeightedPlacedFeature(palmSaplingChecked.getHolder().get(),0.5F)
//                            ),
//                            palmSaplingChecked.getHolder().get()
//                    )
//            )
//    );
//    //检测最多一次性生成多少树
//    public static final RegistryObject<PlacedFeature> palmSaplingPlaced=PLACED_FEATURES.register(
//            "palm_sapling_placed",
//            ()->new PlacedFeature(
//                    palmSaplingSpawn.getHolder().get(),
//                    VegetationPlacements.treePlacement(PlacementUtils.countExtra(1,0.02f,0))
//                    //放置数，生成概率，基础数目
//            )
//    );
}
