package com.KaminoYoruneko.lots_of_food_reborn.items.blockitems;

import com.KaminoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import static com.KaminoYoruneko.lots_of_food_reborn.register.BlockRegister.caramelBlock;


public class CaramelBlockItem extends BlockItem /*implements registerable */{
    public CaramelBlockItem() {
        super(caramelBlock.get(),
                new Item.Properties()
                        .tab(MOD_TAB.TAB_BLOCK)
        );
    }

//    @Override
//    public RegistryObject<Item> ItemRegister(DeferredRegister<Item> reg, String name) {
//        return reg.register(name,CaramelBlockItem::new);
//    }
}
