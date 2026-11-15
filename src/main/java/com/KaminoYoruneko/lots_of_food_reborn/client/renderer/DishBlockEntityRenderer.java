package com.KaminoYoruneko.lots_of_food_reborn.client.renderer;

import com.KaminoYoruneko.lots_of_food_reborn.blocks.entity.DishBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
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
        poseStack.translate(0.5, 0.0625, 0.5);
        poseStack.scale(0.5f, 0.5f, 0.5f);

        // 绕 X 轴旋转 90 度，使物品平贴在盘子上。
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, buffer, be.getLevel(), (int) be.getBlockPos().asLong());
        poseStack.popPose();
    }
}
