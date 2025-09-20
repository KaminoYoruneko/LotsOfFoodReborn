package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.crop.*;
import com.KaminnoYoruneko.lots_of_food_reborn.custom.Custom;
import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class CropRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static RegistryObject<Block> chiliBush;
    public static RegistryObject<Block> algueBush;
    public static RegistryObject<Block> tomateBush;
    public static RegistryObject<Block> coffeeBush;
    public static RegistryObject<Block> vanillaBush;
    public static RegistryObject<Block> strawberryBush;
    public static RegistryObject<Block> grapeBush;
    public static RegistryObject<Block> cornBush;
    public static RegistryObject<Block> cornBushTop;
    public static RegistryObject<Item> chiliSeeds;
    public static RegistryObject<Item> tomateSeeds;
    public static RegistryObject<Item> coffeeSeeds;
    public static RegistryObject<Item> vanilla;
    public static RegistryObject<Item> algue;
    public static RegistryObject<Item> strawberry;
    public static RegistryObject<Item> grapes;
    public static RegistryObject<Item> corn;

    public static RegistryObject<Item> chili;


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
