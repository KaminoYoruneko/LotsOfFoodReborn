package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.items.blockitems.*;
import com.KaminnoYoruneko.lots_of_food_reborn.items.food.Caramel;
import com.KaminnoYoruneko.lots_of_food_reborn.items.food.Chocolate;
import com.KaminnoYoruneko.lots_of_food_reborn.items.other.ArmourCheff;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//    public static final RegistryObject<Item> caramelBlockItem =ITEMS.register("caramel_block",CaramelBlockItem::new);
    //帽子
    public static final RegistryObject<Item> cheff = ITEMS.register("cheff_hat",
            ArmourCheff::new
    );

    //食品
    public static final RegistryObject<Item> caramel = ITEMS.register("caramel",
            Caramel::new
    );
    public static final RegistryObject<Item> chocolate = ITEMS.register("chocolate",
            Chocolate::new
    );

    //blockitems
    public static final RegistryObject<Item> caramelBlockItem = ITEMS.register("caramel_block",
            CaramelBlockItem::new
    );
    public static final RegistryObject<Item> chocolateBlockItem = ITEMS.register("chocolate_block",
            ChocolateBlockItem::new
    );
    public static final RegistryObject<Item> sugarBlockItem = ITEMS.register("sugar_bricks",
            SugarBricksItem::new
    );
    public static final RegistryObject<Item> chocolateStairsItem = ITEMS.register("chocolate_stairs",
            ChocolateStairsItem::new
    );
    public static final RegistryObject<Item> caramelStairsItem = ITEMS.register("caramel_stairs",
            CaramelStairsItem::new
    );
    public static final RegistryObject<Item> sugarBricksStairsItem = ITEMS.register("sugar_bricks_stairs",
            SugarBricksStairsItem::new
    );
    public static final RegistryObject<Item> chocolateFenceItem = ITEMS.register("chocolate_fence",
            ChocolateFenceItem::new
    );
    public static final RegistryObject<Item> CaramelFenceItem = ITEMS.register("caramel_fence",
            CaramelFenceItem::new
    );
    public static final RegistryObject<Item> SugarBricksFenceItem = ITEMS.register("sugar_bricks_fence",
            SugarBricksFenceItem::new
    );
}
