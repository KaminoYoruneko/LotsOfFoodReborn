package com.KaminnoYoruneko.lots_of_food_reborn.blockbase;

import com.KaminnoYoruneko.lots_of_food_reborn.util.IHasModel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class BlockBase extends Block /*implements IHasModel*/ {
    public BlockBase(String Name, SoundType Sound, Material Material) {
        super(BlockBehaviour.Properties.of(Material)
                        .sound(Sound)
                );
//        this.func_149663_c(Name);
//        this.setRegistryName(Name);
//        this.func_149672_a(Sound);
//        this.func_149647_a(Tabs.BLOCKS_TAB);
//        BlockInit.BLOCKS.add(this);
//        ItemInit.ITEMS.add((new ItemBlock(this)).setRegistryName(this.getRegistryName()));
    }

//好像没用到，不写了
//    public void registerModels() {
//        LotsOfFoodRev.proxy.registerModel(Item.func_150898_a(this), 0);
//    }
}
