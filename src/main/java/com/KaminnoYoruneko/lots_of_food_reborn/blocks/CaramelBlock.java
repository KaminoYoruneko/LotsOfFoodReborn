package com.KaminnoYoruneko.lots_of_food_reborn.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class CaramelBlock extends Block /*implements registerable*/{
//    @Override
//    public RegistryObject<Block> BlockRegister(DeferredRegister<Block> reg, String name) {
//        return reg.register(name, CaramelBlock::new);
//    }
    public CaramelBlock() {
        super(BlockBehaviour.Properties.of(Material.WOOL)
                .strength(1.0f)
                .explosionResistance(1.0f)
                .sound(SoundType.STONE)
        );
    }


}
