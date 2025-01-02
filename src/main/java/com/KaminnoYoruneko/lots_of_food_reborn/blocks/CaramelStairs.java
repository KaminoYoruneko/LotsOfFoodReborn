package com.KaminnoYoruneko.lots_of_food_reborn.blocks;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock.CustomStairs;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister.caramelBlock;

public class CaramelStairs extends CustomStairs {

    public CaramelStairs() {
        super(caramelBlock);
    }
}
