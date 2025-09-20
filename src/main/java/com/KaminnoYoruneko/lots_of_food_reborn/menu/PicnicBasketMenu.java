package com.KaminnoYoruneko.lots_of_food_reborn.menu;

import com.KaminnoYoruneko.lots_of_food_reborn.register.MenuRegister;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;


public class PicnicBasketMenu extends AbstractContainerMenu {
    private final ItemStack basketStack;
    private final ItemStackHandler itemHandler;
    private static final int CONTAINER_SIZE = 9;

    public PicnicBasketMenu(int windowId, Inventory playerInv, ItemStack basketStack) {
        super(MenuRegister.PICNIC_BASKET.get(), windowId);
        this.basketStack = basketStack;

        // 初始化物品处理器
        this.itemHandler = new ItemStackHandler(CONTAINER_SIZE) {
            @Override
            protected void onContentsChanged(int slot) {
                playerInv.setChanged();
            }
        };

        // 从NBT加载数据
        loadItemData(basketStack);

        // 新1x9横向布局
        int startX = 8;  // 起始X坐标
        int startY = 18; // 固定Y坐标
        for (int col = 0; col < 9; col++) {
            addSlot(new SlotItemHandler(itemHandler, col,
                    startX + col * 18,  // 每格间距18像素
                    startY));
        }

//        // 添加3x3容器槽位 (62, 17)为中心坐标
//        for (int row = 0; row < 3; ++row) {
//            for (int col = 0; col < 3; ++col) {
//                addSlot(new SlotItemHandler(itemHandler, row * 3 + col,
//                        62 + col * 18,
//                        17 + row * 18));
//            }
//        }


        // 添加玩家背包（27格）
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                addSlot(new Slot(playerInv,
                        col + row * 9 + 9,  // 背包槽位索引从9开始
                        8 + col * 18,       // X坐标
                        50 + row * 18));    // Y坐标
            }
        }

        // 添加快捷栏（9格）
        for (int col = 0; col < 9; ++col) {
            addSlot(new Slot(playerInv, col,
                    8 + col * 18,
                    108)); // 屏幕底部
        }
    }

    private void loadItemData(ItemStack stack) {
        if (stack.hasTag()) {
            CompoundTag tag = stack.getOrCreateTag();
            if (tag.contains("Items")) {
                itemHandler.deserializeNBT(tag.getCompound("Items"));
            }
        }
    }

    // 物品快速转移（Shift+点击）
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copyStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            copyStack = slotStack.copy();

            // 从容器转移到玩家背包
            if (index < CONTAINER_SIZE) {
                if (!moveItemStackTo(slotStack, CONTAINER_SIZE, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            // 从玩家背包转移到容器
            else if (!moveItemStackTo(slotStack, 0, CONTAINER_SIZE, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return copyStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getMainHandItem() == basketStack || player.getOffhandItem() == basketStack;
    }

    @Override
    public void removed(Player player) {
        // 只在服务端保存
        if (!player.level.isClientSide) {
            CompoundTag tag = basketStack.getOrCreateTag();
            tag.put("Items", itemHandler.serializeNBT());
        }
        super.removed(player);
    }

    // 获取容器物品处理器
    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }
}
