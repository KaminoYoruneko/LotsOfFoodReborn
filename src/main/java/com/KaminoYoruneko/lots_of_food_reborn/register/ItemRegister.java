package com.KaminoYoruneko.lots_of_food_reborn.register;

import com.KaminoYoruneko.lots_of_food_reborn.items.blockitems.*;
import com.KaminoYoruneko.lots_of_food_reborn.items.drink.Glass;
import com.KaminoYoruneko.lots_of_food_reborn.items.drink.Mug;
import com.KaminoYoruneko.lots_of_food_reborn.items.food.Caramel;
import com.KaminoYoruneko.lots_of_food_reborn.items.food.Chocolate;
import com.KaminoYoruneko.lots_of_food_reborn.items.food.OpenCoconut;
import com.KaminoYoruneko.lots_of_food_reborn.items.normal.Coconut;
import com.KaminoYoruneko.lots_of_food_reborn.items.other.ArmourCheff;
import com.KaminoYoruneko.lots_of_food_reborn.items.other.PicnicBasket;
import com.KaminoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;
import static com.KaminoYoruneko.lots_of_food_reborn.custom.Custom.*;
import static com.KaminoYoruneko.lots_of_food_reborn.register.BlockRegister.*;
import static com.KaminoYoruneko.lots_of_food_reborn.tab.MOD_TAB.*;

public class ItemRegister {
    public static final double DefaultFoodSat=0.6;
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    //野餐篮子
    public static final DeferredItem<Item> picnicBasket = ITEMS.register(
            "picnic_basket",
            PicnicBasket::new
    );


    //帽子
    public static final DeferredItem<Item> cheff = ITEMS.register("cheff_hat",
            ArmourCheff::new
    );

    //普通物品
    public static final DeferredItem<Item> coconut = ITEMS.register("coconut",
            Coconut::new
    );

    //食品
    public static final DeferredItem<Item> caramel = ITEMS.register("caramel",
            Caramel::new
    );
    public static final DeferredItem<Item> chocolate = ITEMS.register("chocolate",
            Chocolate::new
    );
    public static final DeferredItem<Item> openCoconut = ITEMS.register("open_coconut",
            OpenCoconut::new
    );

    // 旧配方引用但之前漏注册的两个简单物品。
    public static final DeferredItem<Item> dogCookie = register_food("dog_cookie", 2, 0.1F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> cheeseBucket = register_item("cheese_bucket", TAB_MEALS, ITEMS);

    //饮料
    public static final DeferredItem<Item> glass = ITEMS.register("glass",
            Glass::new
    );
    public static final DeferredItem<Item> mug = ITEMS.register("mug",
            Mug::new
    );

    //blockitems
    public static final DeferredItem<Item> caramelBlockItem = ITEMS.register("caramel_block",
            CaramelBlockItem::new
    );
    public static final DeferredItem<Item> chocolateBlockItem = ITEMS.register("chocolate_block",
            ChocolateBlockItem::new
    );
    public static final DeferredItem<Item> sugarBlockItem = ITEMS.register("sugar_bricks",
            SugarBricksItem::new
    );
    public static final DeferredItem<Item> chocolateStairsItem = ITEMS.register("chocolate_stairs",
            ChocolateStairsItem::new
    );
    public static final DeferredItem<Item> caramelStairsItem = ITEMS.register("caramel_stairs",
            CaramelStairsItem::new
    );
    public static final DeferredItem<Item> sugarBricksStairsItem = ITEMS.register("sugar_bricks_stairs",
            SugarBricksStairsItem::new
    );
    public static final DeferredItem<Item> chocolateFenceItem = ITEMS.register("chocolate_fence",
            ChocolateFenceItem::new
    );
    public static final DeferredItem<Item> CaramelFenceItem = ITEMS.register("caramel_fence",
            CaramelFenceItem::new
    );
    public static final DeferredItem<Item> SugarBricksFenceItem = ITEMS.register("sugar_bricks_fence",
            SugarBricksFenceItem::new
    );
    public static final DeferredItem<Item> coconutBlockItem = ITEMS.register("coconut_block",
            CoconutBlockItem::new
    );
    public static final DeferredItem<Item> palmTreeSaplingItem = ITEMS.register("palm_tree_sapling",
            PalmTreeSaplingItem::new
    );

    public static final DeferredItem<Item> dishItem = ITEMS.register("dish",
            () -> new net.minecraft.world.item.BlockItem(BlockRegister.dish.get(), new Item.Properties())
    );

    //采用更便捷的方式，以下由AI处理
    ////193~208
    public static final DeferredItem<Item> rawHorse = register_food("raw_horse", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> cookedHorse = register_food("cooked_horse", 8, 0.8F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> rawSquid = register_food("raw_squid", 2, 0.2F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> cookedSquid = register_food("cooked_squid", 4, 0.4F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> bacon = register_food("bacon", 1, 0.1F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> cookedBacon = register_food("cooked_bacon", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> bass = register_food("bass", 4, 0.3F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> cookedBass = register_food("cooked_bass", 8, 0.6F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> hake = register_food("hake", 4, 0.3F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> cookedHake = register_food("cooked_hake", 8, 0.6F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> sole = register_food("sole", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> cookedSole = register_food("cooked_sole", 7, 0.6F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> trout = register_food("trout", 3, 0.3F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> cookedTrout = register_food("cooked_trout", 7, 0.6F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> goldenFish = register_food("golden_fish", 20, 2.0F, TAB_MEAT, ITEMS);
//    public static final DeferredItem<Item> cookedGoldenFish = register_special_fish("cooked_golden_fish", 20, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> cookedGoldenFish=ITEMS.register("cooked_golden_fish",
        ()->new Item(
                new Item.Properties().food(
                    new FoodProperties.Builder()
                        .nutrition(20)
                        .saturationModifier((float) DefaultFoodSat)
                        .alwaysEdible()
                        .build()
                )
                .rarity(Rarity.EPIC)
        ){
            @Override
            public ItemStack finishUsingItem(ItemStack stack, net.minecraft.world.level.Level world, net.minecraft.world.entity.LivingEntity entity) {
//                super.finishUsingItem(stack, world, entity);

//                System.out.println("WHERE IS MY GLASS QAQ");
                // 如果是玩家，则给予 glass 物品
                if (entity instanceof net.minecraft.world.entity.player.Player player) {
                    player.heal(player.getMaxHealth());//回复满生命值
                }
                return super.finishUsingItem(stack, world, entity);
            }

            @Override
            public boolean isFoil(ItemStack p_41453_) {
                return true;
            }
            @Override
            public void appendHoverText(ItemStack stack, Item.TooltipContext worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                ChatFormatting style=ChatFormatting.GOLD;;
                tooltip.add(Component.translatable("lots_of_food_reborn.text.fullrecover").withStyle(style));
            }
        }
    );

    //209~225
    public static final DeferredItem<Item> caramelPork = register_food("caramel_pork", 12, 1.0F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> tartarSteak = register_food("tartar_steak", 6, DefaultFoodSat, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> chorizo = register_food("chorizo", 6, DefaultFoodSat, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> meatSkewer = register_food("meat_skewer", 5, 0.5F, TAB_MEAT, ITEMS);
    public static final DeferredItem<Item> purifiedFlesh = register_food("purified_flesh", 4, 0.2F, TAB_MEAT, ITEMS);
//    public static final DeferredItem<Item> strawberry = register_seeds_food("strawberry", 3, 0.3F, Blocks.field_150349_c, TAB_FRUITS, ITEMS);
    public static final DeferredItem<Item> cherry = register_food("cherry", 2, 0.3F, TAB_FRUITS, ITEMS);
//    public static final DeferredItem<Item> grapes = register_seeds_food("grapes", 4, 0.3F, Blocks.field_150458_ak, TAB_FRUITS, ITEMS);
    public static final DeferredItem<Item> banana = register_food("banana", 4, 0.4F, TAB_FRUITS, ITEMS);
//    public static final DeferredItem<Item> openCoconut = register_food("open_coconut", 2, 0.2F, TAB_FRUITS, ITEMS);
    public static final DeferredItem<Item> tomatoe = register_food("tomatoe", 2, 0.2F, TAB_FRUITS, ITEMS);
//    public static final DeferredItem<Item> chili = register_chili_food("chili", 4, 0.3F, TAB_FRUITS, ITEMS);
//    public static final DeferredItem<Item> corn = register_seeds_food("corn", 3, 0.4F, Blocks.field_150349_c, TAB_FRUITS, ITEMS);
//    public static final DeferredItem<Item> chocolate = register_food("chocolate", 6, 0.4F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> whiteChocolate = register_food("white_chocolate", 6, 0.4F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> spicyChocolate = register_food("spicy_chocolate", 10, 0.6F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> bunWithChocolate = register_food("bun_with_chocolate", 4, 0.4F, TAB_DESSERTS, ITEMS);

    //226~245
    public static final DeferredItem<Item> whiteCookie = register_food("white_cookie", 1, 0.1F, TAB_DESSERTS, ITEMS);
//    public static final DeferredItem<Item> caramel = register_food("caramel", 1, 0.1F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> caramelApple = register_stick_food("caramel_apple", 8, 0.6F, ITEMS);
    //堆叠设置32
    public static final DeferredItem<Item> chocolateIceCream = register_glass_food("chocolate_ice_cream", 8, 0.3F, ITEMS);
    public static final DeferredItem<Item> appleIceCream = register_glass_food("apple_ice_cream", 6, 0.3F, ITEMS);
    public static final DeferredItem<Item> melonIceCream = register_glass_food("melon_ice_cream", 8, 0.3F, ITEMS);
    public static final DeferredItem<Item> caramelIceCream = register_glass_food("caramel_ice_cream", 10, 0.3F, ITEMS);
    public static final DeferredItem<Item> cocoIceCream = register_glass_food("coco_ice_cream", 6, 0.3F, ITEMS);
    public static final DeferredItem<Item> grapeIceCream = register_glass_food("grape_ice_cream", 6, 0.3F, ITEMS);
    public static final DeferredItem<Item> bananaIceCream = register_glass_food("banana_ice_cream", 6, 0.3F, ITEMS);
    public static final DeferredItem<Item> strawberryIceCream = register_glass_food("strawberry_ice_cream", 5, 0.3F, ITEMS);
    public static final DeferredItem<Item> vanillaIceCream = register_glass_food("vanilla_ice_cream", 5, 0.3F, ITEMS);
    public static final DeferredItem<Item> cherryIceCream = register_glass_food("cherry_ice_cream", 6, 0.3F, ITEMS);
    public static final DeferredItem<Item> whiteChocolateIceCream = register_glass_food("white_chocolate_ice_cream", 8, 0.3F, ITEMS);
    public static final DeferredItem<Item> coffeeIceCream = register_glass_food("coffee_ice_cream", 6, 0.3F, ITEMS);
    public static final DeferredItem<Item> bananaSplit = register_food("banana_split", 18, 2.0F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> crepe = register_food("crepe", 2, 0.3F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> caramelCrepe = register_food("caramel_crepe", 4, 0.5F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> chocolateCrepe = register_food("chocolate_crepe", 10, 0.8F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> cheeseCrepe = register_food("cheese_crepe", 8, DefaultFoodSat, TAB_DESSERTS, ITEMS);

    //246~266
    public static final DeferredItem<Item> sugarCrepe = register_food("sugar_crepe", 3, 0.4F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> chocolateDonut = register_food("chocolate_donut", 3, 0.4F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> glazedDonut = register_food("glazed_donut", 3, 0.4F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> strawberryDonut = register_food("strawberry_donut", 3, 0.4F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> grapeDonut = register_food("grape_donut", 3, 0.4F, TAB_DESSERTS, ITEMS);
//    public static final DeferredItem<Item> candy = register_candy("candy", TAB_DESSERTS, ITEMS);
//    public static final DeferredItem<Item> fortuneCookie = register_fortune_cookie("fortune_cookie", 2, 0.2F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> marshmallow = register_food("marshmallow", 2, 0.1F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> marshmallowStick = register_stick_food("marshmallow_stick", 2, 0.1F, ITEMS);
    //堆叠应该为1
    public static final DeferredItem<Item> cookedMarshmallowStick = register_stick_food("cooked_marshmallow_stick", 4, 0.2F, ITEMS);
    //堆叠应该为1
    public static final DeferredItem<Item> cupcake = register_food("cupcake", 2, 0.1F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> chocolateCupcake = register_food("chocolate_cupcake", 3, 0.1F, TAB_DESSERTS, ITEMS);
    public static final DeferredItem<Item> strawberryCupcake = register_food("strawberry_cupcake", 4, 0.2F, TAB_DESSERTS, ITEMS);

// 以下物品已注册，不需要重复代码
// public static final DeferredItem<Item> appleJuice = register_glass_drink("apple_juice", 5, TAB_DRINKS, ITEMS);
// public static final DeferredItem<Item> melonJuice = register_glass_drink("melon_juice", 5, TAB_DRINKS, ITEMS);
// public static final DeferredItem<Item> grapeJuice = register_glass_drink("grape_juice", 5, TAB_DRINKS, ITEMS);
// public static final DeferredItem<Item> bananaJuice = register_glass_drink("banana_juice", 5, TAB_DRINKS, ITEMS);
// public static final DeferredItem<Item> cocoMilk = register_glass_drink("coco_milk", 5, TAB_DRINKS, ITEMS);
// public static final DeferredItem<Item> cactiJuice = register_glass_drink("cacti_juice", 3, TAB_DRINKS, ITEMS);
// public static final DeferredItem<Item> tomateJuice = register_glass_drink("tomate_juice", 3, TAB_DRINKS, ITEMS);
// public static final DeferredItem<Item> carrotJuice = register_glass_drink("carrot_juice", 5, TAB_DRINKS, ITEMS);

    //267-287
    public static final DeferredItem<Item> chocolateMilkshake = register_glass_food("chocolate_milkshake", 8, DefaultFoodSat, ITEMS);
    public static final DeferredItem<Item> strawberryMilkshake = register_glass_food("strawberry_milkshake", 8, DefaultFoodSat, ITEMS);
    public static final DeferredItem<Item> caramelMilkshake = register_glass_food("caramel_milkshake", 8, DefaultFoodSat, ITEMS);
    public static final DeferredItem<Item> vanillaMilkshake = register_glass_food("vanilla_milkshake", 8, DefaultFoodSat, ITEMS);
    public static final DeferredItem<Item> whiteChocolateMilkshake = register_glass_food("white_chocolate_milkshake", 8, DefaultFoodSat, ITEMS);

    public static final DeferredItem<Item> hotChocolate = register_mug("hot_chocolate", 6, 0.2F, ITEMS);
    // 给予玩家效果：恢复饥饿值6点，持续800秒提供抗火效果（效果强度0）
    public static final DeferredItem<Item> coffee = register_mug("coffee", 4, 0.3F, ITEMS);
    // 给予玩家效果：恢复饥饿值4点，持续800秒提供速度效果（效果强度0）

    //下列已经注册过
//    public static final DeferredItem<Item> greenTe = register_tea("green_te", TAB_DRINKS, ITEMS);
//    public static final DeferredItem<Item> blackTe = register_tea("black_te", TAB_DRINKS, ITEMS);
//    public static final DeferredItem<Item> whiteTe = register_tea("white_te", TAB_DRINKS, ITEMS);
//    public static final DeferredItem<Item> cider = register_alcohol("cider", 5, TAB_DRINKS, ITEMS);
//    public static final DeferredItem<Item> wine = register_alcohol("wine", 5, TAB_DRINKS, ITEMS);
//    public static final DeferredItem<Item> rum = register_alcohol("rum", 5, TAB_DRINKS, ITEMS);
//    public static final DeferredItem<Item> vodka = register_alcohol("vodka", 7, TAB_DRINKS, ITEMS);

    public static final DeferredItem<Item> cheese = register_food("cheese", 4, 0.4F, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> pizza = register_food("pizza", 3, 0.4F, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> popcorn = register_food("popcorn", 5, 0.2F, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> cookedEgg = register_food("cooked_egg", 2, 0.2F, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> sushi = register_food("sushi", 4, 0.3F, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> fishAndChips = register_food("fish_and_chips", 8, 0.7F, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> steakAndFries = register_food("steak_and_fries", 12, 1.0F, TAB_MEALS, ITEMS);

    //288-308
    public static final DeferredItem<Item> takoyaki = register_food("takoyaki", 7, 0.4F, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> boxBread = register_food("box_bread", 2, 0.3F, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> cheeseSandwich = register_food("cheese_sandwich", 10, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> hamSandwich = register_food("ham_sandwich", 10, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> fishSandwich = register_food("fish_sandwich", 12, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> chickenSandwich = register_food("chicken_sandwich", 12, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> baconSandwich = register_food("bacon_sandwich", 12, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> steakSandwich = register_food("steak_sandwich", 14, DefaultFoodSat, TAB_MEALS, ITEMS);
    public static final DeferredItem<Item> fishSoup = register_soup_food("fish_soup", 6, DefaultFoodSat, ITEMS);
    public static final DeferredItem<Item> chickenSoup = register_soup_food("chicken_soup", 6, 0.5F, ITEMS);
    public static final DeferredItem<Item> pumpkinSoup = register_soup_food("pumpkin_soup", 6, DefaultFoodSat, ITEMS);
    public static final DeferredItem<Item> cactiSoup = register_soup_food("cacti_soup", 2, 0.2F, ITEMS);
    public static final DeferredItem<Item> vegeSoup = register_soup_food("vege_soup", 7, 0.5F, ITEMS);
    public static final DeferredItem<Item> algueSoup = register_soup_food("algue_soup", 3, 0.2F, ITEMS);
    public static final DeferredItem<Item> phoSoup = register_soup_food("pho_soup", 11, 1.0F, ITEMS);
    public static final DeferredItem<Item> puree = register_soup_food("puree", 13, 0.3F, ITEMS);
    public static final DeferredItem<Item> pasta = register_soup_food("pasta", 8, 0.8F, ITEMS);
    public static final DeferredItem<Item> chickenPasta = register_soup_food("chicken_pasta", 14, 1.0F, ITEMS);
    public static final DeferredItem<Item> spicyPasta = register_soup_food("spicy_pasta", 12, 1.0F, ITEMS);
    public static final DeferredItem<Item> tomatePasta = register_soup_food("tomate_pasta", 12, 1.0F, ITEMS);
    public static final DeferredItem<Item> mushroomPasta = register_soup_food("mushroom_pasta", 12, 1.0F, ITEMS);

    //309-312 DONE
    public static final DeferredItem<Item> carbonara = register_soup_food("carbonara", 18, 1.0F, ITEMS);
    public static final DeferredItem<Item> chiliPasta = register_soup_food("chili_pasta", 12, 1.0F, ITEMS);
    public static final DeferredItem<Item> fruitSalad = register_soup_food("fruit_salad", 8, DefaultFoodSat, ITEMS);
    public static final DeferredItem<Item> hungerCake = register_hunger_food("hunger_cake", -12, 0.0F, TAB_MEALS, ITEMS); // 该物品恢复负饥饿，极端特殊食物

    //蛋糕们
    public static final DeferredItem<Item> chocolateCake = register_cake_item("chocolate_cake",chocolateCakeBlock,ITEMS);
    public static final DeferredItem<Item> appleCake = register_cake_item("apple_cake",appleCakeBlock,ITEMS);
    public static final DeferredItem<Item> cheeseCake = register_cake_item("cheesecake",cheeseCakeBlock,ITEMS);
    public static final DeferredItem<Item> carrotCake = register_cake_item("carrot_cake",carrotCakeBlock,ITEMS);
    public static final DeferredItem<Item> strawberryCake = register_cake_item("strawberry_cake",strawberryCakeBlock,ITEMS);
    public static final DeferredItem<Item> blackforestCake = register_cake_item("blackforest_cake",blackforestCakeBlock,ITEMS);





    //糖果
    public static final DeferredItem<Item> WHITE_CANDY = register_candy("white_candy", ITEMS);
    public static final DeferredItem<Item> ORANGE_CANDY = register_candy("orange_candy", ITEMS);
    public static final DeferredItem<Item> MAGENTA_CANDY = register_candy("magenta_candy", ITEMS);
    public static final DeferredItem<Item> LIGHT_BLUE_CANDY = register_candy("light_blue_candy", ITEMS);
    public static final DeferredItem<Item> YELLOW_CANDY = register_candy("yellow_candy", ITEMS);
    public static final DeferredItem<Item> LIME_CANDY = register_candy("lime_candy", ITEMS);
    public static final DeferredItem<Item> PINK_CANDY = register_candy("pink_candy", ITEMS);
    public static final DeferredItem<Item> GRAY_CANDY = register_candy("gray_candy", ITEMS);
    public static final DeferredItem<Item> LIGHT_GRAY_CANDY = register_candy("light_gray_candy", ITEMS);
    public static final DeferredItem<Item> CYAN_CANDY = register_candy("cyan_candy", ITEMS);
    public static final DeferredItem<Item> PURPLE_CANDY = register_candy("purple_candy", ITEMS);
    public static final DeferredItem<Item> BLUE_CANDY = register_candy("blue_candy", ITEMS);
    public static final DeferredItem<Item> BROWN_CANDY = register_candy("brown_candy", ITEMS);
    public static final DeferredItem<Item> GREEN_CANDY = register_candy("green_candy", ITEMS);
    public static final DeferredItem<Item> RED_CANDY = register_candy("red_candy", ITEMS);
    public static final DeferredItem<Item> BLACK_CANDY = register_candy("black_candy", ITEMS);

    //幸运饼干
    public static final DeferredItem<Item> fortuneCookie = register_fortune_cookie("fortune_cookie", 2, 0.2F, TAB_DESSERTS, ITEMS);

    static {
        // 直接 ITEMS.register 的物品在这里补充分类；按名字在展示时解析。
        MOD_TAB.add(MOD_TAB.Tab.NOT_FOOD, "picnic_basket");
        MOD_TAB.add(MOD_TAB.Tab.DEBUG, "cheff_hat");
        MOD_TAB.add(MOD_TAB.Tab.NOT_FOOD, "coconut");
        MOD_TAB.add(MOD_TAB.Tab.DESSERTS, "caramel");
        MOD_TAB.add(MOD_TAB.Tab.DESSERTS, "chocolate");
        MOD_TAB.add(MOD_TAB.Tab.DESSERTS, "open_coconut");
        MOD_TAB.add(MOD_TAB.Tab.MEAT, "cooked_golden_fish");
        MOD_TAB.add(MOD_TAB.Tab.DRINKS, "glass");
        MOD_TAB.add(MOD_TAB.Tab.DRINKS, "mug");
        for (String name : new String[]{
                "caramel_block", "chocolate_block", "sugar_bricks",
                "chocolate_stairs", "caramel_stairs", "sugar_bricks_stairs",
                "chocolate_fence", "caramel_fence", "sugar_bricks_fence",
                "coconut_block", "palm_tree_sapling", "dish"
        }) {
            MOD_TAB.add(MOD_TAB.Tab.BLOCK, name);
        }
    }
}

