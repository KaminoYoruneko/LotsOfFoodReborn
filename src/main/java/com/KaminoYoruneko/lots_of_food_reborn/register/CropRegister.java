package com.KaminoYoruneko.lots_of_food_reborn.register;

import com.KaminoYoruneko.lots_of_food_reborn.blocks.crop.*;
import com.KaminoYoruneko.lots_of_food_reborn.custom.Custom;
import com.KaminoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.*;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class CropRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static DeferredBlock<Block> chiliBush;
    public static DeferredBlock<Block> algueBush;
    public static DeferredBlock<Block> tomateBush;
    public static DeferredBlock<Block> coffeeBush;
    public static DeferredBlock<Block> vanillaBush;
    public static DeferredBlock<Block> strawberryBush;
    public static DeferredBlock<Block> grapeBush;
    public static DeferredBlock<Block> cornBush;
    public static DeferredBlock<Block> cornBushTop;
    public static DeferredItem<Item> chiliSeeds;
    public static DeferredItem<Item> tomateSeeds;
    public static DeferredItem<Item> coffeeSeeds;
    public static DeferredItem<Item> vanilla;
    public static DeferredItem<Item> algue;
    public static DeferredItem<Item> strawberry;
    public static DeferredItem<Item> grapes;
    public static DeferredItem<Item> corn;

    public static DeferredItem<Item> chili;


    static {
//        chiliSeeds = Custom.register_seeds("chili_seeds",chiliBush,ITEMS);
        chiliBush = BLOCKS.register("chili_bush", ChiliBush::new);
        //Custom.register_crop_block_age4("chili_bush",chiliSeeds, Blocks.GRASS_BLOCK, BLOCKS);
        chiliSeeds = Custom.register_seeds("chili_seeds",chiliBush,ITEMS);
        chili = Custom.register_chili("chili",4,0.3f,MOD_TAB.TAB_FRUITS,ITEMS);

        tomateBush = BLOCKS.register("tomate_bush", TomateBush::new);
        //Custom.register_crop_block_age5("tomate_bush",tomateSeeds, Blocks.GRASS_BLOCK, BLOCKS);
        tomateSeeds = Custom.register_seeds("tomate_seeds",tomateBush,ITEMS);
        //tomatoe已经有了

        coffeeBush = BLOCKS.register("coffee_bush", CoffeeBush::new);
        //Custom.register_crop_block_age4("coffee_bush",coffeeSeeds,Blocks.GRASS_BLOCK,BLOCKS);
        coffeeSeeds = Custom.register_seeds("coffee_seeds",coffeeBush,ITEMS);

        grapeBush = BLOCKS.register("grape_bush", GrapeBush::new);
        //Custom.register_crop_block_age4("grape_bush",grapes,Blocks.GRASS_BLOCK,BLOCKS);
        grapes = Custom.register_seeds_food("grapes",4,0.3f,grapeBush,ITEMS);

        strawberryBush = BLOCKS.register("strawberry_bush", StrawberryBush::new);
        //Custom.register_crop_block_age4("strawberry_bush",strawberry,Blocks.GRASS_BLOCK,BLOCKS);
        strawberry = Custom.register_seeds_food("strawberry",3,0.3f,strawberryBush,ITEMS);

        vanillaBush = BLOCKS.register("vanilla_bush", VanillaBush::new);
        // Custom.register_crop_block_age4("vanilla_bush",vanilla,Blocks.GRASS_BLOCK,BLOCKS);
        vanilla = Custom.register_seeds("vanilla",vanillaBush,ITEMS);

        algueBush = BLOCKS.register("algue_bush", AlgueBush::new);
        //Custom.register_algue("algue_bush",algue,BLOCKS);
        algue = Custom.register_seeds("algue",algueBush,ITEMS);

        cornBush = BLOCKS.register("corn_bush", CornBush::new);
        cornBushTop = BLOCKS.register("corn_bush_top", CornBushTop::new);
        corn = Custom.register_seeds_food("corn",3,0.4f,cornBush,ITEMS);
    }

}
