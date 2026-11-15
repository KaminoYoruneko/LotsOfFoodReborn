package com.KaminoYoruneko.lots_of_food_reborn.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.*;
import net.neoforged.neoforge.registries.*;
//废弃
public interface registerable {
    default DeferredBlock<Block> BlockRegister(DeferredRegister.Blocks reg, String name){
        return null;
    };
    default DeferredItem<Item> ItemRegister(DeferredRegister.Items reg, String name){
        return null;
    };
}
