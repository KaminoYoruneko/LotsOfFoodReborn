package com.KaminnoYoruneko.lots_of_food_reborn.entities;
import com.KaminnoYoruneko.lots_of_food_reborn.register.EntityRegister;
import com.KaminnoYoruneko.lots_of_food_reborn.register.ItemRegister;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.core.BlockPos;

import java.awt.*;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.renderable.RenderedImageFactory;
//import net.minecraft.world.item.ItemEntity;

public class CoconutEntity extends ThrowableProjectile implements ItemSupplier {

    public CoconutEntity(Level level, Player player) {
        super(/*EntityType.SNOWBALL*/EntityRegister.coconutEntity.get(), player, level);  // 这里使用 `Snowball` 类型来做基础
    }


    public CoconutEntity(EntityType<CoconutEntity> coconutEntityEntityType, Level level) {
        super(coconutEntityEntityType, level);
    }

    @Override
    public void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        generateCoconuts(result.getBlockPos());
        this.discard(); // 销毁物品实体
    }

    @Override
    public void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (result.getEntity() instanceof Mob||result.getEntity() instanceof Animal||result.getEntity() instanceof Player) {
            result.getEntity().hurt(
                    new DamageSource("hit_by_coconut"),
                    2.0F
            );
            generateCoconuts(result.getEntity().blockPosition());
        }
        this.discard(); // 销毁物品实体
    }

    private void generateCoconuts(BlockPos position) {
        Level level = this.level;
        if (!level.isClientSide) {
            ItemStack opencoconut=new ItemStack(ItemRegister.openCoconut.get(),2);
//            ItemEntity coconut1=new ItemEntity(
//                    level,
//                    position.getX()+0.01,
//                    position.getY()+1,
//                    position.getZ(),
//                    opencoconut
//            ),coconut2=new ItemEntity(
//                    level,
//                    position.getX()-0.01,
//                    position.getY()+1,
//                    position.getZ(),
//                    opencoconut
//            );
            ItemEntity coconut1=new ItemEntity(
                    level,
                    position.getX()+0.01,
                    position.getY()+1,
                    position.getZ(),
                    opencoconut
            );
            coconut1.setPickUpDelay(20);
//            coconut2.setPickUpDelay(20);
            level.addFreshEntity(coconut1);
//            level.addFreshEntity(coconut2);
            // 在当前位置生成两个coconut物品
//            ItemEntity coconut1 = new ItemEntity(level, position.getX(), position.getY(), position.getZ(), new ItemStack(Items.APPLE));  // 使用Apple模拟coconut
//            ItemEntity coconut2 = new ItemEntity(level, position.getX(), position.getY(), position.getZ(), new ItemStack(Items.APPLE));  // 使用Apple模拟coconut
//            level.addFreshEntity(coconut1);
//            level.addFreshEntity(coconut2);
        }
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegister.coconut.get());
    }

    public static class EntityCoconutFactory extends EntityRenderer<CoconutEntity> {
        public EntityCoconutFactory(EntityRendererProvider.Context context) {
            super(context);
        }

//        public Render<? super EntityCoconut> createRenderFor(RenderManager Manager) {
//            return new RenderSnowball(Manager, ItemInit.COCONUT, Minecraft.func_71410_x().func_175599_af());
//        }
        @Override
        public ResourceLocation getTextureLocation(CoconutEntity p_114482_) {
            return ItemRegister.coconut.getId();
        }
    }
}
