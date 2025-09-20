package com.KaminnoYoruneko.lots_of_food_reborn.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class CheffHatItemRenderer extends ItemRenderer {
    public final BakedModel bakedModel;

    public CheffHatItemRenderer() {
        super(
            Minecraft.getInstance().getTextureManager(),
            Minecraft.getInstance().getModelManager(),
            Minecraft.getInstance().getItemColors(),
            Minecraft.getInstance().getItemRenderer().getBlockEntityRenderer()
    );
        bakedModel = Minecraft.getInstance().getModelManager()
                .getModel(new ResourceLocation("lots_of_food_reborn:item/armor/helmet_cheff_hat"));

//        System.out.println("Helmet Baked Launched");
    }

//    @Override
    public void render(ItemStack stack, ItemTransforms.TransformType transformType, boolean bool, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        Minecraft.getInstance().getItemRenderer().render(stack, transformType, bool, poseStack, multiBufferSource, light, overlay, this.bakedModel);
//        System.out.println("Stack"+stack.getItem().toString());
//        System.out.println("NOOOO");
    }

}