package com.KaminnoYoruneko.lots_of_food_reborn.blocks;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock.CustomFence;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister.caramelBlock;

public class CaramelFence extends CustomFence {
    public CaramelFence() {
        super(caramelBlock);
    }
}
