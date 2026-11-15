package com.KaminoYoruneko.lots_of_food_reborn;

import com.KaminoYoruneko.lots_of_food_reborn.client.renderer.DishBlockEntityRenderer;
import com.KaminoYoruneko.lots_of_food_reborn.entities.CoconutEntityRenderer;
import com.KaminoYoruneko.lots_of_food_reborn.menu.PicnicBasketScreen;
import com.KaminoYoruneko.lots_of_food_reborn.proxy.CommonProxy;
import com.KaminoYoruneko.lots_of_food_reborn.register.*;
import com.KaminoYoruneko.lots_of_food_reborn.tab.MOD_TAB;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(LOFR.MODID)
public class LOFR
{
    public static final String MODID = "lots_of_food_reborn";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static CommonProxy proxy;
    public static IEventBus modEventBus;

    public LOFR(IEventBus modEventBus, ModContainer modContainer)
    {
        LOFR.modEventBus = modEventBus;

        modEventBus.addListener(this::commonSetup);

        ItemRegister.ITEMS.register(modEventBus);
        BlockRegister.BLOCKS.register(modEventBus);
        BlockEntityRegister.BLOCK_ENTITIES.register(modEventBus);

        DrinkRegister.ITEMS.register(modEventBus);
        EntityRegister.ITEM_ENTITY.register(modEventBus);
        FeatureRegistration.FOLIAGE_PLACER_TYPES.register(modEventBus);
        CropRegister.ITEMS.register(modEventBus);
        CropRegister.BLOCKS.register(modEventBus);

        MenuRegister.MENUS.register(modEventBus);
        MOD_TAB.TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        if (Config.items != null)
            Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            EntityRenderers.register(EntityRegister.coconutEntity.get(),
                    CoconutEntityRenderer::new);
            BlockEntityRenderers.register(BlockEntityRegister.DISH.get(),
                    DishBlockEntityRenderer::new);

            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event)
        {
            event.register(MenuRegister.PICNIC_BASKET.get(), PicnicBasketScreen::new);
        }
    }
}
