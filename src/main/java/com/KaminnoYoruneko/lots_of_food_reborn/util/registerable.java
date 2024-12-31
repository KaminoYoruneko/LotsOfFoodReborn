package com.KaminnoYoruneko.lots_of_food_reborn.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
//废弃
public interface registerable {
    default RegistryObject<Block> BlockRegister(DeferredRegister<Block> reg, String name){
        return null;
    };
    default RegistryObject<Item> ItemRegister(DeferredRegister<Item> reg, String name){
        return null;
    };
}
