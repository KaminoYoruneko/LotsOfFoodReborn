package com.KaminoYoruneko.lots_of_food_reborn.register;

import com.KaminoYoruneko.lots_of_food_reborn.menu.PicnicBasketMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.*;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class MenuRegister {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, MODID);
    public static final DeferredHolder<MenuType<?>, MenuType<PicnicBasketMenu>> PICNIC_BASKET = MENUS.register("picnic_basket",
            () -> IMenuTypeExtension.create((windowId, inv, data) ->
                    new PicnicBasketMenu(windowId, inv, ItemStack.STREAM_CODEC.decode(data))
            )
    );
}
