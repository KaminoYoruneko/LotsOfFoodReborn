package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.menu.PicnicBasketMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class MenuRegister {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);
    public static final RegistryObject<MenuType<PicnicBasketMenu>> PICNIC_BASKET = MENUS.register("picnic_basket",
            () -> IForgeMenuType.create((windowId, inv, data) ->
                    new PicnicBasketMenu(windowId, inv, data.readItem())
            )
    );
}
