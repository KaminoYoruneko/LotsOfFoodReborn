package com.KaminnoYoruneko.lots_of_food_reborn.register;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.CaramelBlock;
import com.KaminnoYoruneko.lots_of_food_reborn.blocks.ChocolateBlock;
import com.KaminnoYoruneko.lots_of_food_reborn.blocks.SugarBricks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> caramelBlock = BLOCKS.register("caramel_block",
            CaramelBlock::new
    );
    public static final RegistryObject<Block> chocolateBlock = BLOCKS.register("chocolate_block",
            ChocolateBlock::new
    );
    public static final RegistryObject<Block> sugarBricks = BLOCKS.register("sugar_bricks",
            SugarBricks::new
    );
/////////////////////////////////////////////////////废弃
//    public static final RegistryObject<Block> caramelBlock = new CaramelBlock().BlockRegister(BLOCKS,"caramel_block");
//    public static final RegistryObject<Block> chocolateBlock = new ChocolateBlock().BlockRegister(BLOCKS,"chocolate_block");
//    public static final RegistryObject<Block> sugarBricks = new SugarBricks().BlockRegister(BLOCKS,"sugar_bricks");
}
