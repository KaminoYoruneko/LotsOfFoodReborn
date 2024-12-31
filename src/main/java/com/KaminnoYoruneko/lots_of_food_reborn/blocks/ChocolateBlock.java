package com.KaminnoYoruneko.lots_of_food_reborn.blocks;

import com.KaminnoYoruneko.lots_of_food_reborn.util.registerable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ChocolateBlock extends Block /*implements registerable*/ {
//    @Override
//    public RegistryObject<Block> BlockRegister(DeferredRegister<Block> reg, String name) {
//        return reg.register(name, ChocolateBlock::new);
//    }
    public ChocolateBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOL)
                .strength(1.0f)
                .explosionResistance(0.7f)
                .sound(SoundType.STONE)
        );
    }
}
