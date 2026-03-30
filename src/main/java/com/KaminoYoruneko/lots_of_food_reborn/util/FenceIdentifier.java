package com.KaminoYoruneko.lots_of_food_reborn.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;

public class FenceIdentifier {
    public static HashSet<Block> Fences=new HashSet<>();

//    static {
//        Fences.add(new ChocolateFence());
//    }

    public static boolean isFenceAsWell(BlockState bs){
        for (Block b:
             Fences) {
            if (bs.is(b)){
                return true;
            }
        }
        return false;
    }
}
