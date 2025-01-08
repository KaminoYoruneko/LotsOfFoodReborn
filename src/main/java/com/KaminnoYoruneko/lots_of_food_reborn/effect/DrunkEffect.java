//package com.KaminnoYoruneko.lots_of_food_reborn.effect;
//
//import net.minecraft.client.player.LocalPlayer;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.GameRenderer;
//import net.minecraft.world.effect.MobEffect;
//import net.minecraft.world.effect.MobEffectCategory;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.entity.LivingEntity;
//
//public class DrunkEffect extends MobEffect {
//    public DrunkEffect() {
//        super(MobEffectCategory.HARMFUL, 0x5A6C81); // 设置效果类型和颜色
//    }
//
//    @Override
//    public void applyEffectTick(LivingEntity entity, int amplifier) {
//        // 自定义效果逻辑（如果需要额外行为）
//        super.applyEffectTick(entity, amplifier);
//    }
//
//    @Override
//    public boolean isDurationEffectTick(int duration, int amplifier) {
//        return true; // 让效果每 tick 生效
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public static void applyCustomDistortionEffect(Minecraft mc, float partialTicks) {
//        // 调整屏幕扭曲度
//        LocalPlayer player = mc.player;
//        if (player != null) {
//            MobEffectInstance effect = player.getEffect(MyEffects.CUSTOM_CONFUSION.get());
//            if (effect != null) {
//                int amplifier = effect.getAmplifier();
//                float distortion = (1.0F + amplifier) * 0.5F; // 扭曲幅度减小一半
//                mc.levelRenderer.screenEffectDistortion = distortion;
//            }
//        }
//    }
//}