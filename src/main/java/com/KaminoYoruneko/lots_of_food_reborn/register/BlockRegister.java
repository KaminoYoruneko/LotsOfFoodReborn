package com.KaminoYoruneko.lots_of_food_reborn.register;

import com.KaminoYoruneko.lots_of_food_reborn.blocks.*;
import com.KaminoYoruneko.lots_of_food_reborn.custom.Custom;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.*;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class BlockRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);


    public static final DeferredBlock<Block> caramelBlock = BLOCKS.register("caramel_block",
            CaramelBlock::new
    );
    public static final DeferredBlock<Block> chocolateBlock = BLOCKS.register("chocolate_block",
            ChocolateBlock::new
    );
    public static final DeferredBlock<Block> sugarBricks = BLOCKS.register("sugar_bricks",
            SugarBricks::new
    );
    public static final DeferredBlock<Block> chocolateStairs = BLOCKS.register("chocolate_stairs",
            ChocolateStairs::new
    );
    public static final DeferredBlock<Block> caramelStairs = BLOCKS.register("caramel_stairs",
            CaramelStairs::new
    );
    public static final DeferredBlock<Block> sugarBricksStairs = BLOCKS.register("sugar_bricks_stairs",
            SugarBricksStairs::new
    );
    public static final DeferredBlock<Block> chocolateFence = BLOCKS.register("chocolate_fence",
            ChocolateFence::new
    );
    public static final DeferredBlock<Block> caramelFence = BLOCKS.register("caramel_fence",
            CaramelFence::new
    );
    public static final DeferredBlock<Block> sugarBricksFence = BLOCKS.register("sugar_bricks_fence",
            SugarBricksFence::new
    );
    public static final DeferredBlock<Block> coconutBlock = BLOCKS.register("coconut_block",
            CoconutBlock::new
    );
    public static final DeferredBlock<Block> palmTreeSapling = BLOCKS.register("palm_tree_sapling",
            PalmTreeSapling::new
    );

    public static final DeferredBlock<Block> dish = BLOCKS.register("dish",
            () -> new DishBlock()
    );

    //走向规范
    public static final DeferredBlock<Block> chocolateCakeBlock = Custom.register_cake_block("chocolate_cake",BLOCKS);
    public static final DeferredBlock<Block> appleCakeBlock = Custom.register_cake_block("apple_cake",BLOCKS);
    public static final DeferredBlock<Block> cheeseCakeBlock = Custom.register_cake_block("cheesecake",BLOCKS);
    public static final DeferredBlock<Block> carrotCakeBlock = Custom.register_cake_block("carrot_cake",BLOCKS);
    public static final DeferredBlock<Block> strawberryCakeBlock = Custom.register_cake_block("strawberry_cake",BLOCKS);
    public static final DeferredBlock<Block> blackforestCakeBlock = Custom.register_cake_block("blackforest_cake",BLOCKS);

/////////////////////////////////////////////////////废弃
//    public static final DeferredBlock<Block> caramelBlock = new CaramelBlock().BlockRegister(BLOCKS,"caramel_block");
//    public static final DeferredBlock<Block> chocolateBlock = new ChocolateBlock().BlockRegister(BLOCKS,"chocolate_block");
//    public static final DeferredBlock<Block> sugarBricks = new SugarBricks().BlockRegister(BLOCKS,"sugar_bricks");




    //作物类
}
