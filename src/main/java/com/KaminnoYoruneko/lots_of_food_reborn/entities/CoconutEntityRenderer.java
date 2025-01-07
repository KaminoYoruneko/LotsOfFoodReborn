package com.KaminnoYoruneko.lots_of_food_reborn.entities;

import com.KaminnoYoruneko.lots_of_food_reborn.register.ItemRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Snowball;

import static com.KaminnoYoruneko.lots_of_food_reborn.LOFR.MODID;
import static net.minecraftforge.versions.forge.ForgeVersion.MOD_ID;

public class CoconutEntityRenderer extends ThrownItemRenderer<CoconutEntity> {
    public CoconutEntityRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }


//    @Override
//    public ResourceLocation getTextureLocation(CoconutEntity p_114482_) {
//        return new ResourceLocation(MODID, "textures/entity/coconut.png");
////        return ItemRegister.coconut.getId();
//    }

    @Override
    public void render(CoconutEntity p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) {
        System.out.println(ItemRegister.coconut.getId());
        System.out.println("Shit, where is the Texture???");
        super.render(p_114485_, p_114486_, p_114487_, p_114488_, p_114489_, p_114490_);
    }
}
