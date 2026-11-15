package com.KaminoYoruneko.lots_of_food_reborn.entities;

import com.KaminoYoruneko.lots_of_food_reborn.register.ItemRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class CoconutEntityRenderer extends ThrownItemRenderer<CoconutEntity> {
    public CoconutEntityRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }


//    @Override
//    public ResourceLocation getTextureLocation(CoconutEntity p_114482_) {
//        return ResourceLocation.fromNamespaceAndPath(MODID, "textures/entity/coconut.png");
////        return ItemRegister.coconut.getId();
//    }

    @Override
    public void render(CoconutEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
