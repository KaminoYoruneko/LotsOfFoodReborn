package com.KaminoYoruneko.lots_of_food_reborn.items.other;

import com.KaminoYoruneko.lots_of_food_reborn.menu.PicnicBasketMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PicnicBasket extends Item {
    public PicnicBasket() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            player.openMenu(new MenuProvider() {
                @Nullable
                @Override
                public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
                    return new PicnicBasketMenu(containerId, playerInventory, stack);
                }

                @Override
                public Component getDisplayName() {
                    return Component.translatable("container.lots_of_food_reborn.picnic_basket");
                }
            }, buf -> ItemStack.STREAM_CODEC.encode(buf, stack));
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
