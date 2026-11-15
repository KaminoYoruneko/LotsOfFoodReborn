package com.KaminoYoruneko.lots_of_food_reborn.trees;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

/**
 * 1.21 的 TreeGrower 是 final 的，并且通过 ResourceKey 查找 ConfiguredFeature。
 * 这里保留原来的类名，只作为静态工厂使用。
 */
public final class PalmTreeGrower {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE_KEY =
            ResourceKey.create(Registries.CONFIGURED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(MODID, "palm_tree"));

    public static final TreeGrower GROWER = new TreeGrower(
            "lots_of_food_reborn_palm",
            Optional.empty(),
            Optional.of(PALM_TREE_KEY),
            Optional.empty());

    private PalmTreeGrower() {
    }
}
