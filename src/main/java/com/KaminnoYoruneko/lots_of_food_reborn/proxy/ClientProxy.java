package com.KaminnoYoruneko.lots_of_food_reborn.proxy;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.*;

//未实现
public class ClientProxy extends CommonProxy {
    public ClientProxy() {
    }

    public void registerModel(Item ItemIn, int Meta) {
//        ModelLoader.setCustomModelResourceLocation(ItemIn, Meta, new ModelResourceLocation(ItemIn.getRegistryName(), "inventory"));
    }

    public void registerModel(Item ItemIn, int Meta, String Id) {
//        ModelLoader.setCustomModelResourceLocation(ItemIn, Meta, new ModelResourceLocation(ItemIn.getRegistryName() + "_" + Id, "inventory"));
    }
}
