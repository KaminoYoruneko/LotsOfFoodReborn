package com.KaminoYoruneko.lots_of_food_reborn.menu;

import com.KaminoYoruneko.lots_of_food_reborn.register.MenuRegister;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;


public class PicnicBasketMenu extends AbstractContainerMenu {
    /** 蛋糕类物品标签 */
    private static final TagKey<Item> CAKES = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "cakes"));
    /** 野餐篮允许放入的特殊物品标签（如饮品等） */
    private static final TagKey<Item> PICNIC_ALLOWED = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(MODID, "picnic_basket_allowed"));

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

        // 添加3x3容器槽位 (62, 17)为中心坐标
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                addSlot(new SlotItemHandler(itemHandler, row * 3 + col,
                        62 + col * 18,
                        17 + row * 18) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return isValidItem(stack);
                    }
                });
            }
        }


        // 添加玩家背包（27格）
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                addSlot(new Slot(playerInv,
                        col + row * 9 + 9,  // 背包槽位索引从9开始
                        8 + col * 18,       // X坐标
                        84 + row * 18));    // Y坐标
            }
        }

        // 添加快捷栏（9格）
        for (int col = 0; col < 9; ++col) {
            addSlot(new Slot(playerInv, col,
                    8 + col * 18,
                    142)); // 屏幕底部
        }
    }

    /**
     * 判断一个物品是否允许放入野餐篮
     * <p>
     * 允许的规则包括：
     * 1. 物品本身是可食用的（Edible）
     * 2. 物品属于蛋糕类标签（forge:cakes）
     * 3. 物品属于野餐篮允许的特殊标签（如饮品、药水等）
     *
     * @param stack 待验证的物品堆
     * @return 如果允许放入则返回 true，否则返回 false
     */
    public boolean isValidItem(ItemStack stack) {
        if (stack.isEmpty()) return false;
        
        // 1. 基础判断：是否可食用
        if (stack.getItem().isEdible()) return true;
        
        // 2. 标签判断：是否为蛋糕类
        if (stack.is(CAKES)) return true;
        
        // 3. 标签判断：是否为其他允许的特殊物品（如饮品、药水）
        if (stack.is(PICNIC_ALLOWED)) return true;
        
        return false;
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
            else {
                // 只有符合条件的物品才能转移入野餐篮
                if (isValidItem(slotStack)) {
                    if (!moveItemStackTo(slotStack, 0, CONTAINER_SIZE, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    return ItemStack.EMPTY;
                }
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
