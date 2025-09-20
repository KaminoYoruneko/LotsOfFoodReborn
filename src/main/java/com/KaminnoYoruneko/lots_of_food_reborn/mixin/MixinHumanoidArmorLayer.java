package com.KaminnoYoruneko.lots_of_food_reborn.mixin;

import com.KaminnoYoruneko.lots_of_food_reborn.register.ItemRegister;
import com.KaminnoYoruneko.lots_of_food_reborn.renderer.CheffHatItemRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
@OnlyIn(Dist.CLIENT)
public class MixinHumanoidArmorLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> {

    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void render(
            PoseStack poseStack, MultiBufferSource bufferSource, int light, T entity,
            float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float tickDelta, CallbackInfo ci) {

        // 获取头部盔甲物品
        ItemStack headArmor = entity.getItemBySlot(EquipmentSlot.HEAD);
        // 检查是否是自定义的 'cheff' 物品
        if (headArmor.getItem() == ItemRegister.cheff.get()) {
            // 取消原有的渲染逻辑
//            ci.cancel();
//            System.out.println("It's cheff hat");
            // 渲染自定义的头盔模型
//            renderSpecialHat(poseStack, bufferSource, light, entity);
        }
    }

    // 自定义渲染方法（渲染 'cheff' 物品）
    private void renderSpecialHat(PoseStack poseStack, MultiBufferSource bufferSource, int light, T entity) {
        // 获取自定义渲染模型
        CheffHatItemRenderer model = new CheffHatItemRenderer();

        // 渲染该自定义的头盔模型
//        model.render(new ItemStack(ItemRegister.cheff.get()), poseStack, bufferSource, light, OverlayTexture.NO_OVERLAY);
        model.render(new ItemStack(ItemRegister.cheff.get()), ItemTransforms.TransformType.NONE, false, poseStack, bufferSource, light, OverlayTexture.NO_OVERLAY);
    }
}
