package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.entities.CoconutEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ITEM_ENTITY= DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
//    public static final DeferredRegister<ItemEntity> ITEM_ENTITY= DeferredRegister.create(ForgeRegistries, MODID);
//    public static final RegistryObject<?> coconutEntity = ITEM_ENTITY.register("coconut",
//            CoconutEntity::new
//    );
}
