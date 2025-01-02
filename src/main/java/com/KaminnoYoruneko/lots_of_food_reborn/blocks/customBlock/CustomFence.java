package com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock;

import com.KaminnoYoruneko.lots_of_food_reborn.util.FenceIdentifier;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class CustomFence extends FenceBlock {
    public CustomFence(RegistryObject<Block> MaterialBlock) {
        super(BlockBehaviour.Properties.copy(MaterialBlock.get()));
        FenceIdentifier.Fences.add(this);
    }

    @Override
    public boolean connectsTo(BlockState blockState, boolean p_53331_, Direction p_53332_) {
//        System.out.println("Is Same Fence? "+p_53330_.is(BlockTags.FENCES));
//        if (FenceIdentifier.isFenceAsWell(blockState)){
//            System.out.println("Fuck you fences, your daddy is going to connect anyways!");
//            return true;
//        }
        for (Block b:
                FenceIdentifier.Fences) {
            if (blockState.is(b)){
//                System.out.println("Fuck you fences, your daddy is going to connect anyways!");
                return true;
            }
        }
//        System.out.println("Well, not fences");
//        if (blockState.is(this)){
//            System.out.println("牛魔的，爷直接相连");
//            return true;
//        }
        return super.connectsTo(blockState, p_53331_, p_53332_);
    }
}
