package com.KaminoYoruneko.lots_of_food_reborn.items.blockitems;

import com.KaminoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import static com.KaminoYoruneko.lots_of_food_reborn.register.BlockRegister.chocolateBlock;

public class ChocolateBlockItem extends BlockItem /*implements registerable*/ {
    public ChocolateBlockItem() {
        super(chocolateBlock.get(),
                new Item.Properties()
                        
        );
    }

//    @Override
//    public DeferredItem<Item> ItemRegister(DeferredRegister.Items reg, String name) {
//        return reg.register(name,ChocolateBlockItem::new);
//    }
}
