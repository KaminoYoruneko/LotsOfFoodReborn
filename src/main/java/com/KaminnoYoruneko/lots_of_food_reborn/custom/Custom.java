package com.KaminnoYoruneko.lots_of_food_reborn.custom;

import com.KaminnoYoruneko.lots_of_food_reborn.register.DrinkRegister;
import com.KaminnoYoruneko.lots_of_food_reborn.register.ItemRegister;
import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.internal.TextComponentMessageFormatHandler;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Custom {
    public Custom() {}
    public static RegistryObject<Item> register_item(String name, CreativeModeTab tab, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .tab(tab)
        ));
    }
    public static RegistryObject<Item> register_glass(String name, int nutrition, double saturation, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .alwaysEat()
                                .build()
                        )
                        .stacksTo(16)
                        .tab(MOD_TAB.TAB_DRINKS)
        ){
            @Override
            public SoundEvent getEatingSound() {
                return SoundEvents.GENERIC_DRINK;
            }

            @Override
            public UseAnim getUseAnimation(ItemStack p_41452_) {
                return UseAnim.DRINK;
            }
            @Override
            public ItemStack finishUsingItem(ItemStack stack, net.minecraft.world.level.Level world, net.minecraft.world.entity.LivingEntity entity) {
//                super.finishUsingItem(stack, world, entity);

//                System.out.println("WHERE IS MY GLASS QAQ");
                // 如果是玩家，则给予 glass 物品
                if (entity instanceof net.minecraft.world.entity.player.Player player) {
                    ItemStack glassStack = new ItemStack(ItemRegister.glass.get(),1);

                    // 如果玩家未处于创造模式，将 glass 添加到玩家的物品栏
                    if (!player.getAbilities().instabuild) {
//                        stack.shrink(1);
//                        System.out.println("I have "+stack.getCount());
                        if (stack.getCount() == 1) {
                            // 饮料喝完的时候，直接返还 glass 到玩家主手
                            player.setItemInHand(player.getUsedItemHand(), glassStack);
                        } else {
                            // 饮料堆叠大于 1，尝试将 glass 添加到背包
                            boolean addedToInventory = player.addItem(glassStack);

                            // 如果物品栏已满，则掉落在地上
                            if (!addedToInventory) {
                                player.drop(glassStack, false);
                            }
                        }
                    }
                }

//                return stack.isEmpty() ? ItemStack.EMPTY : stack;

                return super.finishUsingItem(stack,world,entity);
            }
        });
    }
    public static RegistryObject<Item> register_mug(String name, int nutrition, double saturation, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .alwaysEat()
                                .build()
                        )
                        .stacksTo(16)
                        .tab(MOD_TAB.TAB_DRINKS)
        ){
            @Override
            public SoundEvent getEatingSound() {
                return SoundEvents.GENERIC_DRINK;
            }

            @Override
            public UseAnim getUseAnimation(ItemStack p_41452_) {
                return UseAnim.DRINK;
            }
            @Override
            public ItemStack finishUsingItem(ItemStack stack, net.minecraft.world.level.Level world, net.minecraft.world.entity.LivingEntity entity) {
//                super.finishUsingItem(stack, world, entity);

//                System.out.println("WHERE IS MY GLASS QAQ");
                // 如果是玩家，则给予 glass 物品
                if (entity instanceof net.minecraft.world.entity.player.Player player) {
                    ItemStack glassStack = new ItemStack(ItemRegister.mug.get(),1);

                    // 如果玩家未处于创造模式，将 glass 添加到玩家的物品栏
                    if (!player.getAbilities().instabuild) {
//                        stack.shrink(1);
                        if (stack.getCount() == 1) {
                            // 饮料堆叠为 1，直接返还 glass 到玩家主手
                            player.setItemInHand(player.getUsedItemHand(), glassStack);
                        } else {
                            // 饮料堆叠大于 1，尝试将 glass 添加到背包
                            boolean addedToInventory = player.addItem(glassStack);

                            // 如果物品栏已满，则掉落在地上
                            if (!addedToInventory) {
                                player.drop(glassStack, false);
                            }
                        }
                    }
                }

//                return stack.isEmpty() ? ItemStack.EMPTY : stack;

                return super.finishUsingItem(stack,world,entity);
            }
        });
    }
    public static RegistryObject<Item> register_wine(String name, int nutrition, double saturation, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .alwaysEat()
                                .build()
                        )
                        .stacksTo(16)
                        .tab(MOD_TAB.TAB_DRINKS)
        ){
            @Override
            public SoundEvent getEatingSound() {
                return SoundEvents.GENERIC_DRINK;
            }

            @Override
            public UseAnim getUseAnimation(ItemStack p_41452_) {
                return UseAnim.DRINK;
            }
            @Override
            public ItemStack finishUsingItem(ItemStack stack, net.minecraft.world.level.Level world, net.minecraft.world.entity.LivingEntity entity) {
//                super.finishUsingItem(stack, world, entity);
//                System.out.println("WHERE IS MY GLASS QAQ");
                // 如果是玩家，则给予 glass 物品
                if (entity instanceof net.minecraft.world.entity.player.Player player) {
                    if (stack.getItem() == DrinkRegister.vodka.get()) {
                        // 恢复生命值并添加反胃效果
                        if (!world.isClientSide) {
                            player.heal(7.0F); // 恢复 7 点生命值
                            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 800, 1)); // 给予反胃效果（持续 800 ticks）
                        }
                    } else {
                        // 其他饮品的效果
                        if (!world.isClientSide) {
                            player.heal(5.0F); // 恢复 5 点生命值
                            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 600, 1)); // 给予反胃效果（持续 600 ticks）
                        }
                    }
                    ItemStack glassStack = new ItemStack(ItemRegister.glass.get(),1);

                    // 如果玩家未处于创造模式，将 glass 添加到玩家的物品栏
                    if (!player.getAbilities().instabuild) {
//                        stack.shrink(1);
                        if (stack.getCount() == 1) {
                            // 饮料堆叠为 1，直接返还 glass 到玩家主手
                            player.setItemInHand(player.getUsedItemHand(), glassStack);
                        } else {
                            // 饮料堆叠大于 1，尝试将 glass 添加到背包
                            boolean addedToInventory = player.addItem(glassStack);

                            // 如果物品栏已满，则掉落在地上
                            if (!addedToInventory) {
                                player.drop(glassStack, false);
                            }
                        }
                    }
                }

//                return stack.isEmpty() ? ItemStack.EMPTY : stack;

                return super.finishUsingItem(stack,world,entity);
            }

            @Override
            public Rarity getRarity(ItemStack stack) {
                if (stack.getItem() == DrinkRegister.vodka.get()){
                    return Rarity.RARE;
                }else {
                    return Rarity.UNCOMMON;
                }
            }

            @Override
            public boolean isFoil(ItemStack p_41453_) {
                return true;
            }
        });
    }
    public static RegistryObject<Item> register_tea(String name,DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .alwaysEat()
                                .build()
                        )
                        .stacksTo(16)
                        .tab(MOD_TAB.TAB_DRINKS)
        ){
            @Override
            public SoundEvent getEatingSound() {
                return SoundEvents.GENERIC_DRINK;
            }

            @Override
            public UseAnim getUseAnimation(ItemStack p_41452_) {
                return UseAnim.DRINK;
            }
            @Override
            public ItemStack finishUsingItem(ItemStack stack, net.minecraft.world.level.Level world, net.minecraft.world.entity.LivingEntity entity) {
//                super.finishUsingItem(stack, world, entity);
//                System.out.println("WHERE IS MY GLASS QAQ");
                // 如果是玩家，则给予 glass 物品
                if (entity instanceof net.minecraft.world.entity.player.Player player) {
                    giveEffect(stack,player);
                    ItemStack glassStack = new ItemStack(ItemRegister.mug.get(),1);
                    // 如果玩家未处于创造模式，将 glass 添加到玩家的物品栏
                    if (!player.getAbilities().instabuild) {
//                        stack.shrink(1);
                        if (stack.getCount() == 1) {
                            // 饮料堆叠为 1，直接返还 glass 到玩家主手
                            player.setItemInHand(player.getUsedItemHand(), glassStack);
                        } else {
                            // 饮料堆叠大于 1，尝试将 glass 添加到背包
                            boolean addedToInventory = player.addItem(glassStack);

                            // 如果物品栏已满，则掉落在地上
                            if (!addedToInventory) {
                                player.drop(glassStack, false);
                            }
                        }
                    }
                }

//                return stack.isEmpty() ? ItemStack.EMPTY : stack;

                return super.finishUsingItem(stack,world,entity);
            }

            @Override
            public Rarity getRarity(ItemStack p_41461_) {
                return Rarity.UNCOMMON;
            }

            @Override
            public boolean isFoil(ItemStack p_41453_) {
                return false;
            }

            private void giveEffect(ItemStack stack, LivingEntity entity) {
                // 根据不同茶叶类型为玩家添加对应的效果
                if (stack.getItem() == DrinkRegister.green_te.get()) {
                    // 添加力量效果，持续 20 秒（400 tick）
                    entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400, 0, false, true));
                } else if (stack.getItem() == DrinkRegister.black_te.get()) {
                    // 添加水下呼吸效果，持续 20 秒（400 tick）
                    entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 400, 0, false, true));
                } else if (stack.getItem() == DrinkRegister.white_te.get()) {
                    // 添加抗火效果，持续 20 秒（400 tick）
                    entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 0, false, true));
                }
            }

            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                MutableComponent mutablecomponent;
                MobEffectInstance effect = null;
                ChatFormatting style=ChatFormatting.GOLD;;
                //如果是 GREEN_TE 茶
                if (stack.getItem() == DrinkRegister.green_te.get()) {
                    effect=new MobEffectInstance(MobEffects.DAMAGE_BOOST,400);
//                    style=ChatFormatting.GOLD;
                }
                // 如果是 BLACK_TE 茶
                else if (stack.getItem() == DrinkRegister.black_te.get()) {
                    effect=new MobEffectInstance(MobEffects.WATER_BREATHING,400);
//                    style=ChatFormatting.GOLD;
                }
                // 如果是 WHITE_TE 茶
                else if (stack.getItem() == DrinkRegister.white_te.get()) {
                    effect=new MobEffectInstance(MobEffects.FIRE_RESISTANCE,400);
//                    style=ChatFormatting.GOLD;
                }
                mutablecomponent = Component.translatable(effect.getDescriptionId());
                tooltip.add(Component.translatable("potion.withAmplifier", mutablecomponent,
                        MobEffectUtil.formatDuration(effect, 1.0f)
                ).withStyle(style));
            }

        });
    }
    public static RegistryObject<Item> register_food(String name,int nutrition, double saturation,CreativeModeTab tab, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .build()
                        ).tab(tab)
        ));
    }
    public static RegistryObject<Item> register_hunger_food(String name,int nutrition, double saturation,CreativeModeTab tab, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .alwaysEat()
                                .build()
                        ).tab(tab)
        ));
    }

    public static RegistryObject<Item> register_glass_food(String name, int nutrition, double saturation, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .build()
                        )
                        .stacksTo(16)
                        .tab(MOD_TAB.TAB_DRINKS)
        ){
            @Override
            public ItemStack finishUsingItem(ItemStack stack, net.minecraft.world.level.Level world, net.minecraft.world.entity.LivingEntity entity) {
//                super.finishUsingItem(stack, world, entity);

//                System.out.println("WHERE IS MY GLASS QAQ");
                // 如果是玩家，则给予 glass 物品
                if (entity instanceof net.minecraft.world.entity.player.Player player) {
                    ItemStack glassStack = new ItemStack(ItemRegister.glass.get(),1);

                    // 如果玩家未处于创造模式，将 glass 添加到玩家的物品栏
                    if (!player.getAbilities().instabuild) {
//                        stack.shrink(1);
                        if (stack.getCount() == 1) {
                            // 饮料堆叠为 1，直接返还 glass 到玩家主手
                            player.setItemInHand(player.getUsedItemHand(), glassStack);
                        } else {
                            // 饮料堆叠大于 1，尝试将 glass 添加到背包
                            boolean addedToInventory = player.addItem(glassStack);

                            // 如果物品栏已满，则掉落在地上
                            if (!addedToInventory) {
                                player.drop(glassStack, false);
                            }
                        }
                    }
                }

//                return stack.isEmpty() ? ItemStack.EMPTY : stack;

                return super.finishUsingItem(stack,world,entity);
            }
        });
    }
    public static RegistryObject<Item> register_stick_food(String name, int nutrition, double saturation, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .build()
                        )
                        .stacksTo(64)
                        .tab(MOD_TAB.TAB_DESSERTS)
        ){
            @Override
            public ItemStack finishUsingItem(ItemStack stack, net.minecraft.world.level.Level world, net.minecraft.world.entity.LivingEntity entity) {
//                super.finishUsingItem(stack, world, entity);

//                System.out.println("WHERE IS MY GLASS QAQ");
                // 如果是玩家，则给予 glass 物品
                if (entity instanceof net.minecraft.world.entity.player.Player player) {
                    ItemStack glassStack = new ItemStack(Items.STICK,1);

                    // 如果玩家未处于创造模式，将 glass 添加到玩家的物品栏
                    if (!player.getAbilities().instabuild) {
//                        stack.shrink(1);
                        if (stack.getCount() == 1) {
                            // 饮料堆叠为 1，直接返还 glass 到玩家主手
                            player.setItemInHand(player.getUsedItemHand(), glassStack);
                        } else {
                            // 饮料堆叠大于 1，尝试将 glass 添加到背包
                            boolean addedToInventory = player.addItem(glassStack);

                            // 如果物品栏已满，则掉落在地上
                            if (!addedToInventory) {
                                player.drop(glassStack, false);
                            }
                        }
                    }
                }

//                return stack.isEmpty() ? ItemStack.EMPTY : stack;

                return super.finishUsingItem(stack,world,entity);
            }
        });
    }
    public static RegistryObject<Item> register_soup_food(String name, int nutrition, double saturation, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .build()
                        )
                        .stacksTo(1)
                        .tab(MOD_TAB.TAB_MEALS)
        ){
            @Override
            public ItemStack finishUsingItem(ItemStack stack, net.minecraft.world.level.Level world, net.minecraft.world.entity.LivingEntity entity) {
//                super.finishUsingItem(stack, world, entity);

//                System.out.println("WHERE IS MY GLASS QAQ");
                // 如果是玩家，则给予 glass 物品
                if (entity instanceof net.minecraft.world.entity.player.Player player) {
                    ItemStack glassStack = new ItemStack(Items.BOWL,1);

                    // 如果玩家未处于创造模式，将 glass 添加到玩家的物品栏
                    if (!player.getAbilities().instabuild) {
//                        stack.shrink(1);
                        if (stack.getCount() == 1) {
                            // 饮料堆叠为 1，直接返还 glass 到玩家主手
                            player.setItemInHand(player.getUsedItemHand(), glassStack);
                        } else {
                            // 饮料堆叠大于 1，尝试将 glass 添加到背包
                            boolean addedToInventory = player.addItem(glassStack);

                            // 如果物品栏已满，则掉落在地上
                            if (!addedToInventory) {
                                player.drop(glassStack, false);
                            }
                        }
                    }
                }

//                return stack.isEmpty() ? ItemStack.EMPTY : stack;

                return super.finishUsingItem(stack,world,entity);
            }
        });
    }
    public static RegistryObject<Block> register_crop_block(String name, int maxAge, RegistryObject<Item> seed, DeferredRegister<Block> BLOCKS){
        return BLOCKS.register(name,()->new CropBlock(
                BlockBehaviour.Properties.copy(Blocks.WHEAT)
                ){
                    public final IntegerProperty AGE=IntegerProperty.create("age",0,maxAge);
                    @Override
                    protected ItemLike getBaseSeedId() {
                        return seed.get();
                    }

                    @Override
                    public IntegerProperty getAgeProperty() {
                        return AGE;
                    }

                    @Override
                    public int getMaxAge() {
                        return maxAge;
                    }

                    @Override
                    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
                        builder.add(AGE);
                    }
                }
        );
    }
}
