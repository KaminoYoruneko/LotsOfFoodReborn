package com.KaminnoYoruneko.lots_of_food_reborn.entities;
import com.KaminnoYoruneko.lots_of_food_reborn.register.ItemRegister;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.core.BlockPos;
//import net.minecraft.world.item.ItemEntity;

public class CoconutEntity extends ThrowableProjectile {

    public CoconutEntity(Level level, Player player) {
        super(EntityType.SNOWBALL, player, level);  // 这里使用 `Snowball` 类型来做基础
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
        if (result.getEntity() instanceof Animal||result.getEntity() instanceof Player) {
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
            ItemStack opencoconut=new ItemStack(ItemRegister.openCoconut.get());
//            ItemEntity coconutEntity=
//                    new ItemEntity(
//                            level,
//                            position.getX(),
//                            position.getY(),
//                            position.getZ(),
//                            opencoconut
//                    );
            level.addFreshEntity(
                new ItemEntity(
                    level,
                    position.getX(),
                    position.getY()+1,
                    position.getZ(),
                    opencoconut
                )
            );
            level.addFreshEntity(
                new ItemEntity(
                    level,
                    position.getX(),
                    position.getY()+1,
                    position.getZ(),
                    opencoconut
                )
            );
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
}
