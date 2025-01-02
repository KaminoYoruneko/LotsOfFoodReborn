package com.KaminnoYoruneko.lots_of_food_reborn.items.normal;

import com.KaminnoYoruneko.lots_of_food_reborn.entities.CoconutEntity;
import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//import static net.minecraft.world.InteractionResult.sidedSuccess;

public class Coconut extends Item {
    public Coconut() {
        super(new Item.Properties()
                .tab(MOD_TAB.TAB_FOOD));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        return super.useOn(p_220235_);
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            CoconutEntity coconutEntity = new CoconutEntity(level, player);

            // 设置抛出速度与重力
            coconutEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 1.5F);
            level.addFreshEntity(coconutEntity);
        }

        itemStack.shrink(1); // 扣除一个物品
        return InteractionResultHolder.sidedSuccess(itemStack,level.isClientSide);
    }
}
