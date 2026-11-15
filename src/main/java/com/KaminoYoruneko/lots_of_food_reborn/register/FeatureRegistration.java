package com.KaminoYoruneko.lots_of_food_reborn.register;

import com.KaminoYoruneko.lots_of_food_reborn.trees.conf.PalmTreeFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;

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

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, MODID);

    /**
     * 厨师帽的“盔甲材质”只用于提供 64x32 的帽子贴图和自定义头部模型。
     */
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> CHEFF_HAT_MATERIAL =
            ARMOR_MATERIALS.register("cheff_hat", () -> new ArmorMaterial(
                    Map.of(ArmorItem.Type.HELMET, 0),
                    0,
                    SoundEvents.ARMOR_EQUIP_LEATHER,
                    () -> Ingredient.EMPTY,
                    List.of(new ArmorMaterial.Layer(
                            ResourceLocation.fromNamespaceAndPath(MODID, "cheff_hat"))),
                    0.0F,
                    0.0F));
}
