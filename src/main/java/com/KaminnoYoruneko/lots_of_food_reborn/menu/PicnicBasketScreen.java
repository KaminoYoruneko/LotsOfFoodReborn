package com.KaminnoYoruneko.lots_of_food_reborn.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PicnicBasketScreen extends AbstractContainerScreen<PicnicBasketMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("lots_of_food_reborn", "textures/gui/picnic_basket.png");

    public PicnicBasketScreen(PicnicBasketMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;  // GUI宽度
        this.imageHeight = 166; // GUI高度
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }
}
