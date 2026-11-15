package com.KaminoYoruneko.lots_of_food_reborn.register;

import com.KaminoYoruneko.lots_of_food_reborn.blocks.entity.DishBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.*;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class BlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DishBlockEntity>> DISH = BLOCK_ENTITIES.register("dish",
            () -> BlockEntityType.Builder.of(DishBlockEntity::new, BlockRegister.dish.get()).build(null));
}
