package com.KaminoYoruneko.lots_of_food_reborn.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

public final class ModDamageTypes {
    public static final ResourceKey<DamageType> HIT_BY_COCONUT =
            ResourceKey.create(Registries.DAMAGE_TYPE,
                    ResourceLocation.fromNamespaceAndPath(MODID, "hit_by_coconut"));

    private ModDamageTypes() {
    }
}
