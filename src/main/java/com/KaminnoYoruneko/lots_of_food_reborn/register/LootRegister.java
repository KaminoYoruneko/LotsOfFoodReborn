package com.KaminnoYoruneko.lots_of_food_reborn.register;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class LootRegister {
    public static final DeferredRegister<LootTable> TABLES = DeferredRegister.create((ResourceLocation) ForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MODID);

    public static DeferredRegister<LootTable> getTables(String[] args) {
//        TABLES.register("")
        return TABLES;
    }
}
