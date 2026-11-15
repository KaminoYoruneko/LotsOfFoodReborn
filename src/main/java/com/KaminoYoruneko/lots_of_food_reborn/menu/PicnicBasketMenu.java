package com.KaminoYoruneko.lots_of_food_reborn.menu;

import com.KaminoYoruneko.lots_of_food_reborn.register.MenuRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class PicnicBasketMenu extends AbstractContainerMenu {
    /** 蛋糕类物品标签 */
    private static final TagKey<Item> CAKES = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("c", "cakes"));
    /** 野餐篮允许放入的特殊物品标签（如饮品等） */
    private static final TagKey<Item> PICNIC_ALLOWED = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(MODID, "picnic_basket_allowed"));

    private final ItemStack basketStack;
    private final ItemStackHandler itemHandler;
    private static final int CONTAINER_SIZE = 9;

    public PicnicBasketMenu(int windowId, Inventory playerInv, ItemStack basketStack) {
        super(MenuRegister.PICNIC_BASKET.get(), windowId);
        this.basketStack = basketStack;

        this.itemHandler = new ItemStackHandler(CONTAINER_SIZE) {
            @Override
            protected void onContentsChanged(int slot) {
                playerInv.setChanged();
            }
        };

        loadItemData(basketStack, playerInv.player.level().registryAccess());

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

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                addSlot(new Slot(playerInv,
                        col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            addSlot(new Slot(playerInv, col,
                    8 + col * 18,
                    142));
        }
    }

    public boolean isValidItem(ItemStack stack) {
        if (stack.isEmpty()) return false;

        // 1. 物品本身可食用
        if (stack.has(DataComponents.FOOD)) return true;

        // 2. 蛋糕类标签
        if (stack.is(CAKES)) return true;

        // 3. 野餐篮允许的特殊物品标签（饮品、药水等）
        return stack.is(PICNIC_ALLOWED);
    }

    private void loadItemData(ItemStack stack, HolderLookup.Provider registries) {
        CompoundTag tag = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (tag.contains("Items")) {
            itemHandler.deserializeNBT(registries, tag.getCompound("Items"));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copyStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            copyStack = slotStack.copy();

            if (index < CONTAINER_SIZE) {
                if (!moveItemStackTo(slotStack, CONTAINER_SIZE, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
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
        if (!player.level().isClientSide) {
            CompoundTag tag = new CompoundTag();
            tag.put("Items", itemHandler.serializeNBT(player.level().registryAccess()));
            CustomData.set(DataComponents.CUSTOM_DATA, basketStack, tag);
        }
        super.removed(player);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }
}
