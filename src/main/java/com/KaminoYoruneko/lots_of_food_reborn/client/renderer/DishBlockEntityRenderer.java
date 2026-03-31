package com.KaminoYoruneko.lots_of_food_reborn.client.renderer;

import com.KaminoYoruneko.lots_of_food_reborn.blocks.entity.DishBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public class DishBlockEntityRenderer implements BlockEntityRenderer<DishBlockEntity> {
    public DishBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(DishBlockEntity be, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        ItemStack stack = be.getItemHandler().getStackInSlot(0);
        if (stack.isEmpty()) return;

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        poseStack.pushPose();
        // 1. 移动到盘子中心，Y 轴提升约 1 像素 (1/16 = 0.0625)
        poseStack.translate(0.5, 0.0625, 0.5);
        
        // 2. 缩放
        poseStack.scale(0.5f, 0.5f, 0.5f);

        // 3. 绕 X 轴旋转 90 度，使物品平贴在盘子上
        poseStack.mulPose(Vector3f.XP.rotationDegrees(90));

        itemRenderer.renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLight, combinedOverlay, poseStack, buffer, (int) be.getBlockPos().asLong());
        poseStack.popPose();
    }
}
