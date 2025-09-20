package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.*;
import com.KaminnoYoruneko.lots_of_food_reborn.custom.Custom;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
    public static final RegistryObject<Block> coconutBlock = BLOCKS.register("coconut_block",
            CoconutBlock::new
    );
    public static final RegistryObject<Block> palmTreeSapling = BLOCKS.register("palm_tree_sapling",
            PalmTreeSapling::new
    );

    //走向规范
    public static final RegistryObject<Block> chocolateCakeBlock = Custom.register_cake_block("chocolate_cake",BLOCKS);
    public static final RegistryObject<Block> appleCakeBlock = Custom.register_cake_block("apple_cake",BLOCKS);
    public static final RegistryObject<Block> cheeseCakeBlock = Custom.register_cake_block("cheesecake",BLOCKS);
    public static final RegistryObject<Block> carrotCakeBlock = Custom.register_cake_block("carrot_cake",BLOCKS);
    public static final RegistryObject<Block> strawberryCakeBlock = Custom.register_cake_block("strawberry_cake",BLOCKS);
    public static final RegistryObject<Block> blackforestCakeBlock = Custom.register_cake_block("blackforest_cake",BLOCKS);

/////////////////////////////////////////////////////废弃
//    public static final RegistryObject<Block> caramelBlock = new CaramelBlock().BlockRegister(BLOCKS,"caramel_block");
//    public static final RegistryObject<Block> chocolateBlock = new ChocolateBlock().BlockRegister(BLOCKS,"chocolate_block");
//    public static final RegistryObject<Block> sugarBricks = new SugarBricks().BlockRegister(BLOCKS,"sugar_bricks");




    //作物类
}
