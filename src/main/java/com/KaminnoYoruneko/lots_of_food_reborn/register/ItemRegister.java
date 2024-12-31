package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.items.blockitems.CaramelBlockItem;
import com.KaminnoYoruneko.lots_of_food_reborn.items.blockitems.ChocolateBlockItem;
import com.KaminnoYoruneko.lots_of_food_reborn.items.blockitems.SugarBricksItem;
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
    public static final RegistryObject<Item> chocolate = ITEMS.register("chocolate",
            Chocolate::new
    );
}
