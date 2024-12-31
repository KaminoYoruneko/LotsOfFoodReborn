package com.KaminnoYoruneko.lots_of_food_reborn.items.other;

import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;

public class PicnicBasket extends Item {
    private NonNullList<ItemStack> items=NonNullList.withSize(9,ItemStack.EMPTY);
    private ContainerOpenersCounter openersCounter=new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level p_155460_, BlockPos p_155461_, BlockState p_155462_) {

        }

        @Override
        protected void onClose(Level p_155473_, BlockPos p_155474_, BlockState p_155475_) {

        }

        @Override
        protected void openerCountChanged(Level p_155463_, BlockPos p_155464_, BlockState p_155465_, int p_155466_, int p_155467_) {

        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof ChestMenu){
                Container container=((ChestMenu) player.containerMenu).getContainer();
                return container==PicnicBasket.this;
            }
            return false;
        }
    };

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    public PicnicBasket() {
        super(new Item.Properties()
                .tab(MOD_TAB.TAB_DEBUG));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(net.minecraft.world.level.Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()){
            return super.use(level,player,hand);
        }
        player.openItemGui();

//                .openGui(lots_of_food_revoved.instance, 1, WorldIn, (int)PlayerIn.field_70165_t, (int)PlayerIn.field_70163_u, (int)PlayerIn.field_70161_v);
    }

}