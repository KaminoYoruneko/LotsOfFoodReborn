package com.KaminnoYoruneko.lots_of_food_reborn.handler;

import com.KaminnoYoruneko.lots_of_food_reborn.register.ItemRegister;
import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;
import static net.minecraftforge.versions.forge.ForgeVersion.MOD_ID;

@Mod.EventBusSubscriber
public class WorldEventHandler {
    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        System.out.println("LOAD-->"+event.getName());
        if (event.getName().equals(new ResourceLocation(MODID, "gameplay/fishing/fish"))){
            System.out.println("FISH LOADED");
        }
        if (event.getName().equals(new ResourceLocation("minecraft", "gameplay/fishing/fish"))) {
            LootTable lootTable = event.getTable();
            LootPool existingPool = lootTable.getPool("main");  // 获取主掉落池

            if (existingPool != null) {
                // 创建新的 LootItem 条目
                LootPoolEntryContainer hakeEntry = LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation(MODID, "hake")))
                        .setWeight(20)
                        .build();
                LootPoolEntryContainer troutEntry = LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation(MODID, "trout")))
                        .setWeight(20)
                        .build();
                LootPoolEntryContainer bassEntry = LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation(MODID, "bass")))
                        .setWeight(20)
                        .build();
                LootPoolEntryContainer soleEntry = LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation(MODID, "sole")))
                        .setWeight(20)
                        .build();
                LootPoolEntryContainer goldenFishEntry = LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation(MODID, "golden_fish")))
                        .setWeight(2)
                        .build();

                // 使用 addEntry 方法将新的条目添加到现有的 LootPool
                try {
                    Method ae=LootPool.class.getDeclaredMethod("addEntry",LootPoolEntryContainer.class) ;
                    ae.setAccessible(true);
                    ae.invoke(existingPool,hakeEntry);
                    ae.invoke(existingPool,troutEntry);
                    ae.invoke(existingPool,bassEntry);
                    ae.invoke(existingPool,soleEntry);
                    ae.invoke(existingPool,goldenFishEntry);
//                    existingPool.addEntry(hakeEntry);
//                    existingPool.addEntry(troutEntry);
//                    existingPool.addEntry(bassEntry);
//                    existingPool.addEntry(soleEntry);
//                    existingPool.addEntry(goldenFishEntry);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }

//            LootTable lootTable = event.getTable();
//            LootPool p=event.getTable().getPool("main");
//            System.out.println(">>YES I FOUND FISHING, main is null? "+(p==null));
//            // 添加新的掉落池
//            LootPool pool = LootPool.lootPool()
//                    .add(LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(
//                            new ResourceLocation(MODID, "hake")
//                    )).setWeight(20))
//                    .add(LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(
//                            new ResourceLocation(MODID, "trout")
//                    )).setWeight(20))
//                    .add(LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(
//                            new ResourceLocation(MODID, "bass")
//                    )).setWeight(20))
//                    .add(LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(
//                            new ResourceLocation(MODID, "sole")
//                    )).setWeight(20))
//                    .add(LootItem.lootTableItem(ForgeRegistries.ITEMS.getValue(
//                            new ResourceLocation(MODID, "golden_fish")
//                    )).setWeight(2))
//                    .build();
//
//            lootTable.addPool(pool);
        }
    }
    @SubscribeEvent
    public static void onLivingEntityDeath(LivingDeathEvent event) {
        // Ensure we're not on the client side (field_70170_p.field_72995_K is client-side check)
        if (!event.getEntity().level.isClientSide) {
            LivingEntity entity = event.getEntity();
            final int[] bonus = {0};
            if (event.getSource().getDirectEntity() instanceof Player){
                Entity p=event.getSource().getDirectEntity();
                p.getHandSlots().forEach(itemStack -> {
                    if (!itemStack.isEmpty()){
                        int level=itemStack.getEnchantmentLevel(Enchantments.MOB_LOOTING);
                        bonus[0] =level> bonus[0] ?level: bonus[0];
                    }
                });
            }
            RandomSource random = entity.level.random;

            // If the entity is a Zombie
            if (entity instanceof Zombie) {
                if (random.nextInt(30) == 0) {
                    // Drop grapes item
                    //还没写grape qwq
//                    ItemEntity item = new ItemEntity(entity.level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ItemRegister.GRAPES, 1+ random.nextInt(2*bonus[0])));
//                    entity.level.addFreshEntity(item);
                }
            } else /*if (ConfigHandler.allowExtraMeat) */{
                ItemStack stack;
                ItemEntity item;
                // If the entity is a Horse
                if (entity instanceof Horse horse) {
                    stack = new ItemStack(ItemRegister.rawHorse.get(), 1 + random.nextInt(3+bonus[0])); // Drop 1-3 raw horse meat
                    if (entity.isOnFire()) {
                        stack = new ItemStack(ItemRegister.cookedHorse.get(), stack.getCount()); // If the horse is adult, drop cooked horse meat
                    }

                    item = new ItemEntity(entity.level, entity.getX(), entity.getY(), entity.getZ(), stack);
                    entity.level.addFreshEntity(item);
                }
                // If the entity is a Squid
                else if (entity instanceof Squid squid) {
                    stack = new ItemStack(ItemRegister.rawSquid.get(), 1 + random.nextInt(2+bonus[0])); // Drop 1-2 raw squid meat
                    if (squid.isOnFire()) {
                        stack = new ItemStack(ItemRegister.cookedSquid.get(), stack.getCount()); // If squid is adult, drop cooked squid meat
                    }

                    item = new ItemEntity(entity.level, entity.getX(), entity.getY(), entity.getZ(), stack);
                    entity.level.addFreshEntity(item);
                }
            }
        }
    }
}


/*
{
  "type": "minecraft:fishing",
  "pools": [
    {
      "bonus_rolls": 0.0,
      "entries": [
        {
          "type": "minecraft:item",
          "name": "lots_of_food_reborn:hake",
          "weight": 20
        },
        {
          "type": "minecraft:item",
          "name": "lots_of_food_reborn:trout",
          "weight": 20
        },
        {
          "type": "minecraft:item",
          "name": "lots_of_food_reborn:bass",
          "weight": 20
        },
        {
          "type": "minecraft:item",
          "name": "lots_of_food_reborn:sole",
          "weight": 20
        },
        {
          "type": "minecraft:item",
          "name": "lots_of_food_reborn:golden_fish",
          "weight": 2,
          "functions": [
            {
              "function": "minecraft:set_count",
              "count": {
                "max": 2,
                "min": 1
              }
            }
          ]
        }
      ],
      "rolls": 1.0
    }
  ]
}
*/
