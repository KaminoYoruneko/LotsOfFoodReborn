package com.KaminoYoruneko.lots_of_food_reborn.register;

import com.KaminoYoruneko.lots_of_food_reborn.trees.conf.PalmTreeFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class FeatureRegistration {
    /**
     * 1.21 的 ConfiguredFeature / PlacedFeature 是数据驱动注册表；
     * 这里只注册自定义 FoliagePlacerType，树本身放到 worldgen JSON。
     */
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, MODID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<PalmTreeFoliagePlacer>> PALM_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("palm",
                    () -> new FoliagePlacerType<>(PalmTreeFoliagePlacer.CODEC));
}
