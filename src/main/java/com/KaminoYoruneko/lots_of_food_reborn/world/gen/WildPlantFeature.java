package com.KaminoYoruneko.lots_of_food_reborn.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Supplier;

/**
 * 参考 1.12 WorldGenCoffee / WorldGenCorn / WorldGenStrawberry / WorldGenKelp：
 * 每区块尝试 64 次，在原点附近随机放置，年龄随机；玉米会同时放上下两格。
 */
public class WildPlantFeature extends Feature<NoneFeatureConfiguration> {
    private final Supplier<? extends Block> plant;
    private final IntegerProperty ageProperty;
    private final int maxAge;
    private final int maxPlacements;
    private final boolean inWater;
    private final Supplier<? extends Block> topPlant;
    private final IntegerProperty topAgeProperty;

    public WildPlantFeature(Supplier<? extends Block> plant, IntegerProperty ageProperty,
                            int maxAge, int maxPlacements, boolean inWater,
                            Supplier<? extends Block> topPlant, IntegerProperty topAgeProperty) {
        super(NoneFeatureConfiguration.CODEC);
        this.plant = plant;
        this.ageProperty = ageProperty;
        this.maxAge = maxAge;
        this.maxPlacements = maxPlacements;
        this.inWater = inWater;
        this.topPlant = topPlant;
        this.topAgeProperty = topAgeProperty;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        int placed = 0;

        for (int attempt = 0; attempt < 64 && placed < maxPlacements; attempt++) {
            BlockPos pos = origin.offset(
                    random.nextInt(8) - random.nextInt(8),
                    random.nextInt(4) - random.nextInt(4),
                    random.nextInt(8) - random.nextInt(8));

            if (pos.getY() < level.getMinBuildHeight() || pos.getY() >= level.getMaxBuildHeight()) {
                continue;
            }

            if (inWater) {
                if (!level.getBlockState(pos).is(Blocks.WATER)) {
                    continue;
                }
            } else if (!level.isEmptyBlock(pos)) {
                continue;
            }

            int age = random.nextInt(maxAge + 1);
            BlockState state = plant.get().defaultBlockState().setValue(ageProperty, age);
            if (!state.canSurvive(level, pos)) {
                continue;
            }

            level.setBlock(pos, state, 3);
            placed++;

            if (topPlant != null && age > 0) {
                BlockPos topPos = pos.above();
                BlockState topState = topPlant.get().defaultBlockState()
                        .setValue(topAgeProperty, age - 1);
                if (level.isEmptyBlock(topPos) && topState.canSurvive(level, topPos)) {
                    level.setBlock(topPos, topState, 3);
                }
            }
        }

        return true;
    }
}
