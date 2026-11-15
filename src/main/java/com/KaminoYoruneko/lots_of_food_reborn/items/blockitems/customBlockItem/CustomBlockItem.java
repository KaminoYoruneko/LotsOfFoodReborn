package com.KaminoYoruneko.lots_of_food_reborn.items.blockitems.customBlockItem;

import com.KaminoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.*;

public class CustomBlockItem extends BlockItem {
    //传入注册类注册过的台阶方块，注意不要与方块混淆
    public CustomBlockItem(DeferredBlock<Block> StairBlock) {
        super(StairBlock.get(),
                new Item.Properties()
                        
        );
    }
}
