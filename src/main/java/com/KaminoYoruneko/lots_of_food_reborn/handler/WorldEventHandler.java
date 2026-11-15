package com.KaminoYoruneko.lots_of_food_reborn.handler;

import com.KaminoYoruneko.lots_of_food_reborn.Config;
import com.KaminoYoruneko.lots_of_food_reborn.blocks.crop.CornBush;
import com.KaminoYoruneko.lots_of_food_reborn.blocks.crop.CornBushTop;
import com.KaminoYoruneko.lots_of_food_reborn.blocks.customBlock.AgeProvider;
import com.KaminoYoruneko.lots_of_food_reborn.register.ItemRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

@EventBusSubscriber(modid = MODID)
public class WorldEventHandler {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide() || !Config.allowRightClickHarvest) return;

        BlockPos pos = event.getPos();
        BlockState state = event.getLevel().getBlockState(pos);
        Block block = state.getBlock();

        if (!(block instanceof CropBlock cropBlock)) return;

        ResourceLocation registryName = BuiltInRegistries.BLOCK.getKey(block);
        if (registryName == null) return;
        boolean inScope = switch (Config.harvestScope) {
            case MOD_ONLY -> registryName.getNamespace().equals(MODID);
            case VANILLA_AND_MOD -> registryName.getNamespace().equals(MODID) || registryName.getNamespace().equals("minecraft");
            case ALL_CROPS -> true;
        };
        if (!inScope) return;

        // 手持骨粉时交给原版骨粉逻辑，绝不触发右键收割；
        // 否则对成熟玉米右键骨粉会直接收割，和“先催熟下层、再长上层”冲突。
        if (event.getItemStack().is(Items.BONE_MEAL)) return;

        if (cropBlock.isMaxAge(state)) {
            int resetAge = 0;
            if (block instanceof CornBush) {
                resetAge = 3;
                BlockPos abovePos = pos.above();
                BlockState aboveState = event.getLevel().getBlockState(abovePos);
                if (aboveState.getBlock() instanceof CornBushTop topBlock && topBlock.isMaxAge(aboveState)) {
                    performHarvest(event.getLevel(), abovePos, aboveState, topBlock, event.getEntity(), 0);
                }
            }

            performHarvest(event.getLevel(), pos, state, cropBlock, event.getEntity(), resetAge);

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }

    private static void performHarvest(Level level, BlockPos pos, BlockState state, CropBlock cropBlock, Player player, int resetAge) {
        List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos, null, player, player.getMainHandItem());

        IntegerProperty ageProperty = cropBlock instanceof AgeProvider provider
                ? provider.getAgeProperty()
                : CropBlock.AGE;
        level.setBlock(pos, state.setValue(ageProperty, resetAge), 3);

        for (ItemStack drop : drops) {
            if (Config.harvestDropsToInventory) {
                if (!player.getInventory().add(drop)) {
                    Block.popResource(level, pos, drop);
                }
            } else {
                Block.popResource(level, pos, drop);
            }
        }

        level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);
        player.swing(InteractionHand.MAIN_HAND, true);
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(ResourceLocation.fromNamespaceAndPath("minecraft", "gameplay/fishing/fish"))) {
            LootTable lootTable = event.getTable();
            LootPool existingPool = lootTable.getPool("main");

            if (existingPool != null) {
                LootPoolEntryContainer hakeEntry = LootItem.lootTableItem(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "hake")))
                        .setWeight(20)
                        .build();
                LootPoolEntryContainer troutEntry = LootItem.lootTableItem(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "trout")))
                        .setWeight(20)
                        .build();
                LootPoolEntryContainer bassEntry = LootItem.lootTableItem(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "bass")))
                        .setWeight(20)
                        .build();
                LootPoolEntryContainer soleEntry = LootItem.lootTableItem(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "sole")))
                        .setWeight(20)
                        .build();
                LootPoolEntryContainer goldenFishEntry = LootItem.lootTableItem(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "golden_fish")))
                        .setWeight(2)
                        .build();

                try {
                    Method ae = LootPool.class.getDeclaredMethod("addEntry", LootPoolEntryContainer.class);
                    ae.setAccessible(true);
                    ae.invoke(existingPool, hakeEntry);
                    ae.invoke(existingPool, troutEntry);
                    ae.invoke(existingPool, bassEntry);
                    ae.invoke(existingPool, soleEntry);
                    ae.invoke(existingPool, goldenFishEntry);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingEntityDeath(LivingDeathEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            LivingEntity entity = event.getEntity();
            final int[] bonus = {0};
            if (event.getSource().getDirectEntity() instanceof Player player) {
                bonus[0] = enchantLevel(player.level(), player.getMainHandItem(), Enchantments.LOOTING);
                bonus[0] = Math.max(bonus[0], enchantLevel(player.level(), player.getOffhandItem(), Enchantments.LOOTING));
            }
            RandomSource random = entity.level().random;

            if (entity instanceof Zombie) {
                if (random.nextInt(30) == 0) {
                    // 葡萄掉落尚未实现。
                }
            } else {
                ItemStack stack;
                ItemEntity item;
                if (entity instanceof Horse horse) {
                    stack = new ItemStack(ItemRegister.rawHorse.get(), 1 + random.nextInt(3 + bonus[0]));
                    if (entity.isOnFire()) {
                        stack = new ItemStack(ItemRegister.cookedHorse.get(), stack.getCount());
                    }
                    item = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack);
                    entity.level().addFreshEntity(item);
                } else if (entity instanceof Squid squid) {
                    stack = new ItemStack(ItemRegister.rawSquid.get(), 1 + random.nextInt(2 + bonus[0]));
                    if (squid.isOnFire()) {
                        stack = new ItemStack(ItemRegister.cookedSquid.get(), stack.getCount());
                    }
                    item = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack);
                    entity.level().addFreshEntity(item);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockHarvest(BlockEvent.BreakEvent event) {
        RandomSource rand = event.getLevel().getRandom();
        BlockState blockState = event.getState();
        Block block = blockState.getBlock();

        int fortune = enchantLevel(event.getPlayer().level(), event.getPlayer().getMainHandItem(), Enchantments.FORTUNE);
        fortune = Math.max(fortune, enchantLevel(event.getPlayer().level(), event.getPlayer().getOffhandItem(), Enchantments.FORTUNE));

        if (block instanceof net.minecraft.world.level.block.LeavesBlock) {
            if (blockState.is(Blocks.BIRCH_LEAVES)) {
                addExtraDrops(event, ItemRegister.cherry.get(), 2, fortune, rand);
            } else if (blockState.is(Blocks.JUNGLE_LEAVES)) {
                addExtraDrops(event, ItemRegister.banana.get(), 1, fortune, rand);
            }
        }
    }

    private static int enchantLevel(Level level, ItemStack stack, ResourceKey<Enchantment> key) {
        Holder<Enchantment> holder = level.registryAccess().holderOrThrow(key);
        return stack.getEnchantmentLevel(holder);
    }

    private static void addExtraDrops(BlockEvent.BreakEvent event, Item dropItem, int initial, int fortuneLevel, RandomSource rand) {
        fortuneLevel = Math.max(fortuneLevel, 0);
        int chance = 20;

        if (fortuneLevel > 0) {
            chance -= 5 * fortuneLevel;
            if (chance < 5) {
                chance = 5;
            }
        }

        if (rand.nextInt(chance) == 0) {
            int quantity = initial + rand.nextInt(fortuneLevel + 1);
            event.getPlayer().level().addFreshEntity(new ItemEntity(
                    event.getPlayer().level(),
                    event.getPos().getX(),
                    event.getPos().getY(),
                    event.getPos().getZ(),
                    new ItemStack(dropItem, quantity)));
        }
    }
}
