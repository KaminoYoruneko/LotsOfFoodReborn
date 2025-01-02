package com.KaminnoYoruneko.lots_of_food_reborn.util;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.ChocolateFence;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.Set;

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
