package com.KaminnoYoruneko.lots_of_food_reborn.tab;

import com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister;
import com.KaminnoYoruneko.lots_of_food_reborn.register.DrinkRegister;
import com.KaminnoYoruneko.lots_of_food_reborn.register.ItemRegister;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MOD_TAB{
    public static final CreativeModeTab TAB_BLOCK=new CreativeModeTab("TAB_BLOCK"){

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockRegister.caramelBlock.get());
        }
    };
    public static final CreativeModeTab TAB_NOT_FOOD=new CreativeModeTab("TAB_NOT_FOOD"){

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegister.caramel.get());
        }
    };
    public static final CreativeModeTab TAB_MEAT=new CreativeModeTab("TAB_MEAT"){

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegister.rawHorse.get());
        }
    };
    public static final CreativeModeTab TAB_FRUITS=new CreativeModeTab("TAB_FRUITS"){

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegister.caramel.get());
        }
    };
    public static final CreativeModeTab TAB_DESSERTS=new CreativeModeTab("TAB_DESSERTS"){

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegister.chocolateDonut.get());
        }
    };
    public static final CreativeModeTab TAB_DRINKS=new CreativeModeTab("TAB_DRINKS"){

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(DrinkRegister.cactiJuice.get());
        }
    };
    public static final CreativeModeTab TAB_MEALS=new CreativeModeTab("TAB_MEALS"){

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegister.pasta.get());
        }
    };
    public static final CreativeModeTab TAB_DEBUG=new CreativeModeTab("TAB_DEBUG"){

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegister.cheff.get());
        }
    };
}
