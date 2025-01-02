package com.KaminnoYoruneko.lots_of_food_reborn.trees;

import com.KaminnoYoruneko.lots_of_food_reborn.register.FeatureRegistration;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class PalmTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
        return FeatureRegistration.palmTreeConfig.getHolder().get();
    }
}
