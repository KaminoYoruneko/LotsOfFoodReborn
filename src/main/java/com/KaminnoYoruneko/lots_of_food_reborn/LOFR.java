package com.KaminnoYoruneko.lots_of_food_reborn;

import com.KaminnoYoruneko.lots_of_food_reborn.entities.CoconutEntity;
import com.KaminnoYoruneko.lots_of_food_reborn.entities.CoconutEntityRenderer;
import com.KaminnoYoruneko.lots_of_food_reborn.register.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LOFR.MODID)
@Mod.EventBusSubscriber(modid = "lots_of_food_reborn", bus = Mod.EventBusSubscriber.Bus.MOD)
public class LOFR
{
    public static final String MODID = "lots_of_food_reborn";
    private static final Logger LOGGER = LogUtils.getLogger();
//    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
//    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

//    public static final RegistryObject<Block> CaramelBlock = BLOCKS.register("caramel_block",
//            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
//                    .strength(3.0f)
//                    .sound(SoundType.STONE)
//            ));
//    public static final RegistryObject<Item> CaramelBlockItem = ITEMS.register("caramel_block",
//            () -> new BlockItem(CaramelBlock.get(),
//                    new Item.Properties()
//                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS))
//
//    );
    public static IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public LOFR()
    {


        modEventBus.addListener(this::commonSetup);

        BlockRegister.BLOCKS.register(modEventBus);
        ItemRegister.ITEMS.register(modEventBus);
        DrinkRegister.ITEMS.register(modEventBus);
        EntityRegister.ITEM_ENTITY.register(modEventBus);
        FeatureRegistration.FEATURES.register(modEventBus);
        FeatureRegistration.PLACED_FEATURES.register(modEventBus);

//        registerFeatures();


        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

//    private void registerFeatures() {
//        for (int i=0;i<1000;i++){i=i+0;}
//        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
//        modEventBus.addListener(this::commonSetup);
//        FeatureRegistration.FEATURES.register(modEventBus);
//        FeatureRegistration.PLACED_FEATURES.register(modEventBus);
//        MinecraftForge.EVENT_BUS.register(this);
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
//    }

//    @SubscribeEvent
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            EntityRenderers.register(EntityRegister.coconutEntity.get(),
                    CoconutEntityRenderer::new);
//            registerFeatures();
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
