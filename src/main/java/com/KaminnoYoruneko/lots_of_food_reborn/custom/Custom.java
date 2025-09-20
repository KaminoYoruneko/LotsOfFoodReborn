package com.KaminnoYoruneko.lots_of_food_reborn.custom;

import com.KaminnoYoruneko.lots_of_food_reborn.register.DrinkRegister;
import com.KaminnoYoruneko.lots_of_food_reborn.register.ItemRegister;
import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Custom {
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
    public static RegistryObject<Item> register_fortune_cookie(String name, int nutrition, double saturation, CreativeModeTab tab, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .build()
                        ).tab(tab)
        ){
            @Override
            public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
                if (!level.isClientSide && livingEntity instanceof Player) {
                    Player player = (Player) livingEntity;
                    //ITextComponent Message = new TextComponentTranslation("fortune.text.message" + String.valueOf(WorldIn.field_73012_v.nextInt(19)), new Object[0]);
                    // 创建本地化文本组件
                    Component message = Component.translatable("fortune.text.message"
                                    + String.valueOf(level.random.nextInt(19))
                            ).withStyle(ChatFormatting.LIGHT_PURPLE); // 可选的文字样式

                    // 发送带样式的系统消息给玩家
                    player.sendSystemMessage(message);
                }
                return super.finishUsingItem(itemStack, level, livingEntity);
            }
        });
    }
    public static RegistryObject<Item> register_candy(String name, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(3)
                                .saturationMod((float) 0.1F)
                                .fast()
                                .alwaysEat()
                                .build()
                        ).tab(MOD_TAB.TAB_DESSERTS)
        ){
            @Override
            public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
                if (!level.isClientSide && entity instanceof Player) {
                    Player player = (Player) entity;
                    int effectType = level.random.nextInt(6); // 0-5 对应6种效果

                    // 5秒 = 100 ticks (20 ticks/秒)
                    int duration = 100;

                    switch (effectType) {
                        case 0 ->
                                player.addEffect(new MobEffectInstance(
                                        MobEffects.MOVEMENT_SPEED,
                                        duration,
                                        0));
                        case 1 ->
                                player.addEffect(new MobEffectInstance(
                                        MobEffects.DIG_SPEED,
                                        duration,
                                        0));
                        case 2 ->
                                player.addEffect(new MobEffectInstance(
                                        MobEffects.DAMAGE_BOOST,
                                        duration,
                                        0));
                        case 3 ->
                                player.addEffect(new MobEffectInstance(
                                        MobEffects.JUMP,
                                        duration,
                                        0));
                        case 4 ->
                                player.addEffect(new MobEffectInstance(
                                        MobEffects.REGENERATION,
                                        duration,
                                        0));
                        default ->
                                player.addEffect(new MobEffectInstance(
                                        MobEffects.NIGHT_VISION,
                                        duration,
                                        0));
                    }
                }
                return super.finishUsingItem(itemStack, level, entity);
            }
        });
    }
    public static RegistryObject<Item> register_chili(String name,int nutrition, double saturation,CreativeModeTab tab, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .build()
                        ).tab(tab)
        ){
                    @Override
                    public ItemStack finishUsingItem(ItemStack p_41409_, Level level, LivingEntity entity) {
                        if (!level.isClientSide && entity instanceof Player) {
                            entity.setSecondsOnFire(3); // 服务器端触发着火效果‌:ml-citation{ref="1,2" data="citationList"}
                        }
                        return super.finishUsingItem(p_41409_, level, entity);
                    }
                }

        );
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
                        .stacksTo(32)
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
    public static RegistryObject<Block> register_algue(String name/*, int maxAge*/, RegistryObject<Item> seed, DeferredRegister<Block> BLOCKS){
        return BLOCKS.register(name,()->new CropBlock(
//                BlockBehaviour.Properties.copy(Blocks.WHEAT)
                        BlockBehaviour.Properties.of(Material.WATER_PLANT)
                ){
                    public static final int maxAge=3;
                    public static final IntegerProperty AGE=IntegerProperty.create("age",0,
                            maxAge
                    );

                    @Override
                    protected boolean mayPlaceOn(BlockState bs, BlockGetter getter, BlockPos bp) {
//                        return super.mayPlaceOn(p_52302_, p_52303_, p_52304_);
                        BlockState upper=getter.getBlockState(bp.above());

                        if (!upper.is(Blocks.WATER)){
                            return false;
                        }

                        return bs.is(Blocks.SAND)
                                ||bs.is(Blocks.GRAVEL)
                                ||bs.is(Blocks.DIRT)
                                ||bs.is(Blocks.FARMLAND);
                    }

                    @Override
                    protected ItemLike getBaseSeedId() {
                        return seed.get();
//                        return chiliSeeds.get();
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
    public static RegistryObject<Block> register_crop_block_age3(String name/*, int maxAge*/, RegistryObject<Item> seed, Block growOnBlock, DeferredRegister<Block> BLOCKS){
        return BLOCKS.register(name,()->new CropBlock(
//                BlockBehaviour.Properties.copy(Blocks.WHEAT)
                BlockBehaviour.Properties.copy(Blocks.POTATOES)
                ){
                    public static final int maxAge=3;
                    public static final IntegerProperty AGE=IntegerProperty.create("age",0,
                            maxAge
                    );

                    @Override
                    protected boolean mayPlaceOn(BlockState p_52302_, BlockGetter p_52303_, BlockPos p_52304_) {
//                        return super.mayPlaceOn(p_52302_, p_52303_, p_52304_);
                        return p_52302_.is(growOnBlock)||p_52302_.is(Blocks.FARMLAND);
                    }

                    @Override
                    protected ItemLike getBaseSeedId() {
                        return seed.get();
//                        return chiliSeeds.get();
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
    public static RegistryObject<Block> register_crop_block_age4(String name/*, int maxAge*/, RegistryObject<Item> seed, Block growOnBlock, DeferredRegister<Block> BLOCKS){
        return BLOCKS.register(name,()->new CropBlock(
//                BlockBehaviour.Properties.copy(Blocks.WHEAT)
                        BlockBehaviour.Properties.copy(Blocks.WHEAT)
                ){
                    public static final int maxAge=4;
                    public static final IntegerProperty AGE=IntegerProperty.create("age",0,
                            maxAge
                    );

                    @Override
                    protected boolean mayPlaceOn(BlockState p_52302_, BlockGetter p_52303_, BlockPos p_52304_) {
//                        return super.mayPlaceOn(p_52302_, p_52303_, p_52304_);
                        return p_52302_.is(growOnBlock)||p_52302_.is(Blocks.FARMLAND);
                    }

                    @Override
                    protected ItemLike getBaseSeedId() {
                        return seed.get();
//                        return chiliSeeds.get();
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
    public static RegistryObject<Block> register_crop_block_age5(String name/*, int maxAge*/, RegistryObject<Item> seed, Block growOnBlock, DeferredRegister<Block> BLOCKS){
        return BLOCKS.register(name,()->new CropBlock(
//                BlockBehaviour.Properties.copy(Blocks.WHEAT)
                        BlockBehaviour.Properties.copy(Blocks.POTATOES)
                ){
                    public static final int maxAge=5;
                    public static final IntegerProperty AGE=IntegerProperty.create("age",0,
                            maxAge
                    );

                    @Override
                    protected boolean mayPlaceOn(BlockState p_52302_, BlockGetter p_52303_, BlockPos p_52304_) {
//                        return super.mayPlaceOn(p_52302_, p_52303_, p_52304_);
                        return p_52302_.is(growOnBlock)||p_52302_.is(Blocks.FARMLAND);
                    }

                    @Override
                    protected ItemLike getBaseSeedId() {
                        return seed.get();
//                        return chiliSeeds.get();
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
    public static RegistryObject<Item> register_seeds(String name,RegistryObject<Block> cropBlock,DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()-> new ItemNameBlockItem(
                cropBlock.get(),
                new Item.Properties()
                        .tab(MOD_TAB.TAB_FRUITS)
            )
        );
//        return ITEMS.register(name,()-> new Item(
//                        new Item.Properties()
//                                .tab(MOD_TAB.TAB_FRUITS)
//                )
//        );
    }
    public static RegistryObject<Item> register_seeds_food(String name, int nutrition, double saturation,RegistryObject<Block> cropBlock,DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()-> new ItemNameBlockItem(
                cropBlock.get(),
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition)
                                .saturationMod((float) saturation)
                                .build()
                        )
                        .tab(MOD_TAB.TAB_FRUITS)
            )
        );
    }
    public static RegistryObject<Block> register_cake_block(String name, DeferredRegister<Block> BLOCKS){
        return BLOCKS.register(name,()->new CakeBlock(
                        BlockBehaviour.Properties.copy(Blocks.CAKE)
                )
        );
    }
    public static RegistryObject<Item> register_cake_item(String name,RegistryObject<Block> cakeBlock, DeferredRegister<Item> ITEMS){
        return ITEMS.register(name,()->new BlockItem(
                        cakeBlock.get(),
                        new Item.Properties()
                                .stacksTo(1)
                                .tab(MOD_TAB.TAB_DESSERTS)
                )
        );
    }
}
