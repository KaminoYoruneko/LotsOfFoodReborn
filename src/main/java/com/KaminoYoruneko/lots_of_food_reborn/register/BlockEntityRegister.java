package com.KaminoYoruneko.lots_of_food_reborn.register;

import com.KaminoYoruneko.lots_of_food_reborn.blocks.entity.DishBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class BlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static final RegistryObject<BlockEntityType<DishBlockEntity>> DISH = BLOCK_ENTITIES.register("dish",
            () -> BlockEntityType.Builder.of(DishBlockEntity::new, BlockRegister.dish.get()).build(null));
}
