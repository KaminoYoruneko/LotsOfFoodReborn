package com.KaminoYoruneko.lots_of_food_reborn.register;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;
import static com.KaminoYoruneko.lots_of_food_reborn.custom.Custom.*;

public class DrinkRegister {
    public static final double DefaultDrinkSat=0.6;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> appleJuice=register_glass("apple_juice", 5,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> melonJuice=register_glass("melon_juice", 5,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> grapeJuice=register_glass("grape_juice", 5,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> bananaJuice=register_glass("banana_juice", 5,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> cocoMilk=register_glass("coco_milk", 5,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> cactiJuice=register_glass("cacti_juice", 3,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> tomateJuice=register_glass("tomate_juice", 3,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> carrotJuice=register_glass("carrot_juice", 5,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> cider=register_wine("cider",5,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> wine=register_wine("wine",5,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> rum= register_wine("rum",5,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> vodka= register_wine("vodka",7,DefaultDrinkSat, ITEMS);
    public static final RegistryObject<Item> green_te = register_tea("green_te", ITEMS);
    public static final RegistryObject<Item> black_te = register_tea("black_te", ITEMS);
    public static final RegistryObject<Item> white_te = register_tea("white_te", ITEMS);
}
