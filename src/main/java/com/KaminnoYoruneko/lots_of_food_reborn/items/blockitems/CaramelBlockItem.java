package com.KaminnoYoruneko.lots_of_food_reborn.items.blockitems;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.CaramelBlock;
import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import com.KaminnoYoruneko.lots_of_food_reborn.util.registerable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister.caramelBlock;


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
