package com.KaminnoYoruneko.lots_of_food_reborn.items.blockitems.customBlockItem;

import com.KaminnoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister.chocolateStairs;

public class CustomBlockItem extends BlockItem {
    //传入注册类注册过的台阶方块，注意不要与方块混淆
    public CustomBlockItem(RegistryObject<Block> StairBlock) {
        super(StairBlock.get(),
                new Item.Properties()
                        .tab(MOD_TAB.TAB_BLOCK)
        );
    }
}
