package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.entities.CoconutEntity;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ITEM_ENTITY= DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
//    private static <T extends Entity> EntityType<T> register(String p_20635_, EntityType.Builder<T> p_20636_) {
////        return Registry.register(Registry.ENTITY_TYPE, p_20635_, p_20636_.build(p_20635_));
//        return ITEM_ENTITY.register(p_20635_, p_20636_.build(p_20635_));
//    }
//    public static final EntityType<Snowball> coconutEntityType = register(
//            "coconut",
//            EntityType.Builder.<Snowball>of(Snowball::new, MobCategory.MISC)
//                    .sized(0.25F, 0.25F)
//                    .clientTrackingRange(4)
//                    .updateInterval(10)
//    );

public static final RegistryObject<EntityType> coconutEntity= ITEM_ENTITY.register(
        "coconut",
        ()->EntityType.Builder.<CoconutEntity>of(CoconutEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(new ResourceLocation(MODID,"coconut").toString())
);

    //    public static final DeferredRegister<ItemEntity> ITEM_ENTITY= DeferredRegister.create(ForgeRegistries, MODID);
//    public static final RegistryObject<?> coconutEntity = ITEM_ENTITY.register("coconut",
//            CoconutEntity::new
//    );
}
