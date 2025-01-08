package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.items.blockitems.*;
import com.KaminnoYoruneko.lots_of_food_reborn.items.drink.Glass;
import com.KaminnoYoruneko.lots_of_food_reborn.items.drink.Mug;
import com.KaminnoYoruneko.lots_of_food_reborn.items.food.Caramel;
import com.KaminnoYoruneko.lots_of_food_reborn.items.food.Chocolate;
import com.KaminnoYoruneko.lots_of_food_reborn.items.food.OpenCoconut;
import com.KaminnoYoruneko.lots_of_food_reborn.items.normal.Coconut;
import com.KaminnoYoruneko.lots_of_food_reborn.items.other.ArmourCheff;
import net.minecraft.tags.TagManager;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;
import static com.KaminnoYoruneko.lots_of_food_reborn.custom.Custom.*;
import static com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB.*;

public class ItemRegister {
    public static final double DefaultFoodSat=0.6;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//    public static final RegistryObject<Item> caramelBlockItem =ITEMS.register("caramel_block",CaramelBlockItem::new);
    //帽子
    public static final RegistryObject<Item> cheff = ITEMS.register("cheff_hat",
            ArmourCheff::new
    );

    //普通物品
    public static final RegistryObject<Item> coconut = ITEMS.register("coconut",
            Coconut::new
    );

    //食品
    public static final RegistryObject<Item> caramel = ITEMS.register("caramel",
            Caramel::new
    );
    public static final RegistryObject<Item> chocolate = ITEMS.register("chocolate",
            Chocolate::new
    );
    public static final RegistryObject<Item> openCoconut = ITEMS.register("open_coconut",
            OpenCoconut::new
    );

    //饮料
    public static final RegistryObject<Item> glass = ITEMS.register("glass",
            Glass::new
    );
    public static final RegistryObject<Item> mug = ITEMS.register("mug",
            Mug::new
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
    public static final RegistryObject<Item> coconutBlockItem = ITEMS.register("coconut_block",
            CoconutBlockItem::new
    );
    public static final RegistryObject<Item> palmTreeSaplingItem = ITEMS.register("palm_tree_sapling",
            PalmTreeSaplingItem::new
    );

    //采用更便捷的方式，以下由AI处理
    ////193~208
    public static final RegistryObject<Item> rawHorse = register_food("raw_horse", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedHorse = register_food("cooked_horse", 8, 0.8F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> rawSquid = register_food("raw_squid", 2, 0.2F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedSquid = register_food("cooked_squid", 4, 0.4F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> bacon = register_food("bacon", 1, 0.1F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedBacon = register_food("cooked_bacon", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> bass = register_food("bass", 4, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedBass = register_food("cooked_bass", 8, 0.6F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> hake = register_food("hake", 4, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedHake = register_food("cooked_hake", 8, 0.6F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> sole = register_food("sole", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedSole = register_food("cooked_sole", 7, 0.6F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> trout = register_food("trout", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedTrout = register_food("cooked_trout", 7, 0.6F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> goldenFish = register_food("golden_fish", 20, 2.0F, TAB_MEAT, ITEMS);
//    public static final RegistryObject<Item> cookedGoldenFish = register_special_fish("cooked_golden_fish", 20, TAB_MEAT, ITEMS);

    //209~225
    public static final RegistryObject<Item> caramelPork = register_food("caramel_pork", 12, 1.0F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> tartarSteak = register_food("tartar_steak", 6, DefaultFoodSat, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> chorizo = register_food("chorizo", 6, DefaultFoodSat, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> meatSkewer = register_food("meat_skewer", 5, 0.5F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> purifiedFlesh = register_food("purified_flesh", 4, 0.2F, TAB_MEAT, ITEMS);
//    public static final RegistryObject<Item> strawberry = register_seeds_food("strawberry", 3, 0.3F, Blocks.field_150349_c, TAB_FRUITS, ITEMS);
    public static final RegistryObject<Item> cherry = register_food("cherry", 2, 0.3F, TAB_FRUITS, ITEMS);
//    public static final RegistryObject<Item> grapes = register_seeds_food("grapes", 4, 0.3F, Blocks.field_150458_ak, TAB_FRUITS, ITEMS);
    public static final RegistryObject<Item> banana = register_food("banana", 4, 0.4F, TAB_FRUITS, ITEMS);
//    public static final RegistryObject<Item> openCoconut = register_food("open_coconut", 2, 0.2F, TAB_FRUITS, ITEMS);
    public static final RegistryObject<Item> tomatoe = register_food("tomatoe", 2, 0.2F, TAB_FRUITS, ITEMS);
//    public static final RegistryObject<Item> chili = register_chili_food("chili", 4, 0.3F, TAB_FRUITS, ITEMS);
//    public static final RegistryObject<Item> corn = register_seeds_food("corn", 3, 0.4F, Blocks.field_150349_c, TAB_FRUITS, ITEMS);
//    public static final RegistryObject<Item> chocolate = register_food("chocolate", 6, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> whiteChocolate = register_food("white_chocolate", 6, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> spicyChocolate = register_food("spicy_chocolate", 10, 0.6F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> bunWithChocolate = register_food("bun_with_chocolate", 4, 0.4F, TAB_DESSERTS, ITEMS);

    //226~245
    public static final RegistryObject<Item> whiteCookie = register_food("white_cookie", 1, 0.1F, TAB_DESSERTS, ITEMS);
//    public static final RegistryObject<Item> caramel = register_food("caramel", 1, 0.1F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> caramelApple = register_stick_food("caramel_apple", 8, 0.6F, ITEMS);
    //堆叠设置32
    public static final RegistryObject<Item> chocolateIceCream = register_glass_food("chocolate_ice_cream", 8, 0.3F, ITEMS);
    public static final RegistryObject<Item> appleIceCream = register_glass_food("apple_ice_cream", 6, 0.3F, ITEMS);
    public static final RegistryObject<Item> melonIceCream = register_glass_food("melon_ice_cream", 8, 0.3F, ITEMS);
    public static final RegistryObject<Item> caramelIceCream = register_glass_food("caramel_ice_cream", 10, 0.3F, ITEMS);
    public static final RegistryObject<Item> cocoIceCream = register_glass_food("coco_ice_cream", 6, 0.3F, ITEMS);
    public static final RegistryObject<Item> grapeIceCream = register_glass_food("grape_ice_cream", 6, 0.3F, ITEMS);
    public static final RegistryObject<Item> bananaIceCream = register_glass_food("banana_ice_cream", 6, 0.3F, ITEMS);
    public static final RegistryObject<Item> strawberryIceCream = register_glass_food("strawberry_ice_cream", 5, 0.3F, ITEMS);
    public static final RegistryObject<Item> vanillaIceCream = register_glass_food("vanilla_ice_cream", 5, 0.3F, ITEMS);
    public static final RegistryObject<Item> cherryIceCream = register_glass_food("cherry_ice_cream", 6, 0.3F, ITEMS);
    public static final RegistryObject<Item> whiteChocolateIceCream = register_glass_food("white_chocolate_ice_cream", 8, 0.3F, ITEMS);
    public static final RegistryObject<Item> coffeeIceCream = register_glass_food("coffee_ice_cream", 6, 0.3F, ITEMS);
    public static final RegistryObject<Item> bananaSplit = register_food("banana_split", 18, 2.0F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> crepe = register_food("crepe", 2, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> caramelCrepe = register_food("caramel_crepe", 4, 0.5F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> chocolateCrepe = register_food("chocolate_crepe", 10, 0.8F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> cheeseCrepe = register_food("cheese_crepe", 8, DefaultFoodSat, TAB_DESSERTS, ITEMS);

    //246~266
    public static final RegistryObject<Item> sugarCrepe = register_food("sugar_crepe", 3, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> chocolateDonut = register_food("chocolate_donut", 3, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> glazedDonut = register_food("glazed_donut", 3, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> strawberryDonut = register_food("strawberry_donut", 3, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> grapeDonut = register_food("grape_donut", 3, 0.4F, TAB_DESSERTS, ITEMS);
//    public static final RegistryObject<Item> candy = register_candy("candy", TAB_DESSERTS, ITEMS);
//    public static final RegistryObject<Item> fortuneCookie = register_fortune_cookie("fortune_cookie", 2, 0.2F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> marshmallow = register_food("marshmallow", 2, 0.1F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> marshmallowStick = register_stick_food("marshmallow_stick", 2, 0.1F, ITEMS);
    //堆叠应该为1
    public static final RegistryObject<Item> cookedMarshmallowStick = register_stick_food("cooked_marshmallow_stick", 4, 0.2F, ITEMS);
    //堆叠应该为1
    public static final RegistryObject<Item> cupcake = register_food("cupcake", 2, 0.1F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> chocolateCupcake = register_food("chocolate_cupcake", 3, 0.1F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> strawberryCupcake = register_food("strawberry_cupcake", 4, 0.2F, TAB_DESSERTS, ITEMS);

// 以下物品已注册，不需要重复代码
// public static final RegistryObject<Item> appleJuice = register_glass_drink("apple_juice", 5, TAB_DRINKS, ITEMS);
// public static final RegistryObject<Item> melonJuice = register_glass_drink("melon_juice", 5, TAB_DRINKS, ITEMS);
// public static final RegistryObject<Item> grapeJuice = register_glass_drink("grape_juice", 5, TAB_DRINKS, ITEMS);
// public static final RegistryObject<Item> bananaJuice = register_glass_drink("banana_juice", 5, TAB_DRINKS, ITEMS);
// public static final RegistryObject<Item> cocoMilk = register_glass_drink("coco_milk", 5, TAB_DRINKS, ITEMS);
// public static final RegistryObject<Item> cactiJuice = register_glass_drink("cacti_juice", 3, TAB_DRINKS, ITEMS);
// public static final RegistryObject<Item> tomateJuice = register_glass_drink("tomate_juice", 3, TAB_DRINKS, ITEMS);
// public static final RegistryObject<Item> carrotJuice = register_glass_drink("carrot_juice", 5, TAB_DRINKS, ITEMS);

    //267-287
    public static final RegistryObject<Item> chocolateMilkshake = register_glass_food("chocolate_milkshake", 8, DefaultFoodSat, ITEMS);
    public static final RegistryObject<Item> strawberryMilkshake = register_glass_food("strawberry_milkshake", 8, DefaultFoodSat, ITEMS);
    public static final RegistryObject<Item> caramelMilkshake = register_glass_food("caramel_milkshake", 8, DefaultFoodSat, ITEMS);
    public static final RegistryObject<Item> vanillaMilkshake = register_glass_food("vanilla_milkshake", 8, DefaultFoodSat, ITEMS);
    public static final RegistryObject<Item> whiteChocolateMilkshake = register_glass_food("white_chocolate_milkshake", 8, DefaultFoodSat, ITEMS);

    public static final RegistryObject<Item> hotChocolate = register_mug("hot_chocolate", 6, 0.2F, ITEMS);
    // 给予玩家效果：恢复饥饿值6点，持续800秒提供抗火效果（效果强度0）
    public static final RegistryObject<Item> coffee = register_mug("coffee", 4, 0.3F, ITEMS);
    // 给予玩家效果：恢复饥饿值4点，持续800秒提供速度效果（效果强度0）

    //下列已经注册过
//    public static final RegistryObject<Item> greenTe = register_tea("green_te", TAB_DRINKS, ITEMS);
//    public static final RegistryObject<Item> blackTe = register_tea("black_te", TAB_DRINKS, ITEMS);
//    public static final RegistryObject<Item> whiteTe = register_tea("white_te", TAB_DRINKS, ITEMS);
//    public static final RegistryObject<Item> cider = register_alcohol("cider", 5, TAB_DRINKS, ITEMS);
//    public static final RegistryObject<Item> wine = register_alcohol("wine", 5, TAB_DRINKS, ITEMS);
//    public static final RegistryObject<Item> rum = register_alcohol("rum", 5, TAB_DRINKS, ITEMS);
//    public static final RegistryObject<Item> vodka = register_alcohol("vodka", 7, TAB_DRINKS, ITEMS);

    public static final RegistryObject<Item> cheese = register_food("cheese", 4, 0.4F, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> pizza = register_food("pizza", 3, 0.4F, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> popcorn = register_food("popcorn", 5, 0.2F, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> cookedEgg = register_food("cooked_egg", 2, 0.2F, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> sushi = register_food("sushi", 4, 0.3F, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> fishAndChips = register_food("fish_and_chips", 8, 0.7F, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> steakAndFries = register_food("steak_and_fries", 12, 1.0F, TAB_MEALS, ITEMS);

    //288-308
    public static final RegistryObject<Item> takoyaki = register_food("takoyaki", 7, 0.4F, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> boxBread = register_food("box_bread", 2, 0.3F, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> cheeseSandwich = register_food("cheese_sandwich", 10, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> hamSandwich = register_food("ham_sandwich", 10, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> fishSandwich = register_food("fish_sandwich", 12, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> chickenSandwich = register_food("chicken_sandwich", 12, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> baconSandwich = register_food("bacon_sandwich", 12, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> steakSandwich = register_food("steak_sandwich", 14, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> fishSoup = register_soup_food("fish_soup", 6, DefaultFoodSat, ITEMS);
    public static final RegistryObject<Item> chickenSoup = register_soup_food("chicken_soup", 6, 0.5F, ITEMS);
    public static final RegistryObject<Item> pumpkinSoup = register_soup_food("pumpkin_soup", 6, DefaultFoodSat, ITEMS);
    public static final RegistryObject<Item> cactiSoup = register_soup_food("cacti_soup", 2, 0.2F, ITEMS);
    public static final RegistryObject<Item> vegeSoup = register_soup_food("vege_soup", 7, 0.5F, ITEMS);
    public static final RegistryObject<Item> algueSoup = register_soup_food("algue_soup", 3, 0.2F, ITEMS);
    public static final RegistryObject<Item> phoSoup = register_soup_food("pho_soup", 11, 1.0F, ITEMS);
    public static final RegistryObject<Item> puree = register_soup_food("puree", 13, 0.3F, ITEMS);
    public static final RegistryObject<Item> pasta = register_soup_food("pasta", 8, 0.8F, ITEMS);
    public static final RegistryObject<Item> chickenPasta = register_soup_food("chicken_pasta", 14, 1.0F, ITEMS);
    public static final RegistryObject<Item> spicyPasta = register_soup_food("spicy_pasta", 12, 1.0F, ITEMS);
    public static final RegistryObject<Item> tomatePasta = register_soup_food("tomate_pasta", 12, 1.0F, ITEMS);
    public static final RegistryObject<Item> mushroomPasta = register_soup_food("mushroom_pasta", 12, 1.0F, ITEMS);

    //309-312 DONE
    public static final RegistryObject<Item> carbonara = register_soup_food("carbonara", 18, 1.0F, ITEMS);
    public static final RegistryObject<Item> chiliPasta = register_soup_food("chili_pasta", 12, 1.0F, ITEMS);
    public static final RegistryObject<Item> fruitSalad = register_soup_food("fruit_salad", 8, DefaultFoodSat, ITEMS);
    public static final RegistryObject<Item> hungerCake = register_hunger_food("hunger_cake", -12, 0.0F, TAB_MEALS, ITEMS); // 该物品恢复负饥饿，极端特殊食物

}

/*
public static final RegistryObject<Item> baconSandwich = register_food("bacon_sandwich", 12, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final RegistryObject<Item> rawHorse = register_food("raw_horse", 3, 0.3F,  TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedHorse = register_food("cooked_horse", 8, 0.8F,  TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> rawSquid = register_food("raw_squid", 2, 0.2F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedSquid = register_food("cooked_squid", 4, 0.4F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> bacon = register_food("bacon", 1, 0.1F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedBacon = register_food("cooked_bacon", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> bass = register_food("bass", 4, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedBass = register_food("cooked_bass", 8, 0.6F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> hake = register_food("hake", 4, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedHake = register_food("cooked_hake", 8, 0.6F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> sole = register_food("sole", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedSole = register_food("cooked_sole", 7, 0.6F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> trout = register_food("trout", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedTrout = register_food("cooked_trout", 7, 0.6F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> goldenFish = register_food("golden_fish", 20, 2.0F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> cookedGoldenFish = register_food("cooked_golden_fish", 20,DefaultFoodSat, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> caramelPork = register_food("caramel_pork", 12, 1.0F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> tartarSteak = register_food("tartar_steak", 6, DefaultFoodSat, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> chorizo = register_food("chorizo", 6,DefaultFoodSat, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> meatSkewer = register_food("meat_skewer", 5, 0.5F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> purifiedFlesh = register_food("purified_flesh", 4, 0.2F, TAB_MEAT, ITEMS);
    public static final RegistryObject<Item> strawberry = register_food("strawberry", 3, 0.3F, TAB_FRUITS, ITEMS);
    public static final RegistryObject<Item> cherry = register_food("cherry", 2, 0.3F, TAB_FRUITS, ITEMS);
    public static final RegistryObject<Item> grapes = register_food("grapes", 4, 0.3F, TAB_FRUITS, ITEMS);
    public static final RegistryObject<Item> banana = register_food("banana", 4, 0.4F, TAB_FRUITS, ITEMS);
//    public static final RegistryObject<Item> openCoconut = register_food("open_coconut", 2, 0.2F, TAB_FRUITS, ITEMS);
    public static final RegistryObject<Item> tomatoe = register_food("tomatoe", 2, 0.2F, TAB_FRUITS, ITEMS);
    public static final RegistryObject<Item> chili = register_food("chili", 4, 0.3F, TAB_FRUITS, ITEMS);
    public static final RegistryObject<Item> corn = register_food("corn", 3, 0.4F, TAB_FRUITS, ITEMS);
//    public static final RegistryObject<Item> chocolate = register_food("chocolate", 6, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> whiteChocolate = register_food("white_chocolate", 6, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> spicyChocolate = register_food("spicy_chocolate", 10, 0.6F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> bunWithChocolate = register_food("bun_with_chocolate", 4, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> whiteCookie = register_food("white_cookie", 1, 0.1F, TAB_DESSERTS, ITEMS);
//    public static final RegistryObject<Item> caramel = register_food("caramel", 1, 0.1F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> caramelApple = register_food("caramel_apple", 8, 0.6F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> chocolateIceCream = register_food("chocolate_ice_cream", 8, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> appleIceCream = register_food("apple_ice_cream", 6, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> melonIceCream = register_food("melon_ice_cream", 8, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> caramelIceCream = register_food("caramel_ice_cream", 10, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> cocoIceCream = register_food("coco_ice_cream", 6, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> grapeIceCream = register_food("grape_ice_cream", 6, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> bananaIceCream = register_food("banana_ice_cream", 6, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> strawberryIceCream = register_food("strawberry_ice_cream", 5, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> vanillaIceCream = register_food("vanilla_ice_cream", 5, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> cherryIceCream = register_food("cherry_ice_cream", 6, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> whiteChocolateIceCream = register_food("white_chocolate_ice_cream", 8, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> coffeeIceCream = register_food("coffee_ice_cream", 6, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> bananaSplit = register_food("banana_split", 18, 2.0F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> crepe = register_food("crepe", 2, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> caramelCrepe = register_food("caramel_crepe", 4, 0.5F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> chocolateCrepe = register_food("chocolate_crepe", 10, 0.8F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> cheeseCrepe = register_food("cheese_crepe", 8,DefaultFoodSat, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> sugarCrepe = register_food("sugar_crepe", 3, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> iceCreamSandwich = register_food("ice_cream_sandwich", 10, 1.0F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> caramelPudding = register_food("caramel_pudding", 5, 0.5F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> creamCaramel = register_food("cream_caramel", 8, 0.8F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> pie = register_food("pie", 6, 0.6F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> sweetPie = register_food("sweet_pie", 8, 0.8F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> applePie = register_food("apple_pie", 8, 1.0F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> lemonPie = register_food("lemon_pie", 6, 0.6F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> cherryPie = register_food("cherry_pie", 8, 0.8F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> pieSlices = register_food("pie_slices", 2, 0.3F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> mincePie = register_food("mince_pie", 7, 0.7F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> chocolateMilk = register_food("chocolate_milk", 6, 0.6F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> milkShake = register_food("milk_shake", 4, 0.4F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> creamMilkShake = register_food("cream_milk_shake", 6, 0.6F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> cake = register_food("cake", 12, 1.0F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> chocolateCake = register_food("chocolate_cake", 12, 1.0F, TAB_DESSERTS, ITEMS);
    public static final RegistryObject<Item> cheeseCake = register_food("cheese_cake", 8, 0.8F, TAB_DESSERTS, ITEMS);

*/