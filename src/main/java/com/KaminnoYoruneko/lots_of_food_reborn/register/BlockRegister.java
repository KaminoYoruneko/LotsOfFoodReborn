package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> caramelBlock = BLOCKS.register("caramel_block",
            CaramelBlock::new
    );
    public static final RegistryObject<Block> chocolateBlock = BLOCKS.register("chocolate_block",
            ChocolateBlock::new
    );
    public static final RegistryObject<Block> sugarBricks = BLOCKS.register("sugar_bricks",
            SugarBricks::new
    );
    public static final RegistryObject<Block> chocolateStairs = BLOCKS.register("chocolate_stairs",
            ChocolateStairs::new
    );
    public static final RegistryObject<Block> caramelStairs = BLOCKS.register("caramel_stairs",
            CaramelStairs::new
    );
    public static final RegistryObject<Block> sugarBricksStairs = BLOCKS.register("sugar_bricks_stairs",
            SugarBricksStairs::new
    );
    public static final RegistryObject<Block> chocolateFence = BLOCKS.register("chocolate_fence",
            ChocolateFence::new
    );
    public static final RegistryObject<Block> caramelFence = BLOCKS.register("caramel_fence",
            CaramelFence::new
    );
    public static final RegistryObject<Block> sugarBricksFence = BLOCKS.register("sugar_bricks_fence",
            SugarBricksFence::new
    );
/////////////////////////////////////////////////////废弃
//    public static final RegistryObject<Block> caramelBlock = new CaramelBlock().BlockRegister(BLOCKS,"caramel_block");
//    public static final RegistryObject<Block> chocolateBlock = new ChocolateBlock().BlockRegister(BLOCKS,"chocolate_block");
//    public static final RegistryObject<Block> sugarBricks = new SugarBricks().BlockRegister(BLOCKS,"sugar_bricks");
}
