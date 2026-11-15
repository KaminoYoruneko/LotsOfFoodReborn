package com.KaminoYoruneko.lots_of_food_reborn.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import static com.KaminoYoruneko.lots_of_food_reborn.LOFR.MODID;

/**
 * 旧版 1.12 CheffHat$CheffHatModel 的 1.21 移植：
 * 两个镜像 box 分别对应帽顶和帽檐，UV 使用 64x32 的 cheff_hat_layer_1.png。
 */
public class ChefHatArmorModel extends HumanoidModel<LivingEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MODID, "cheff_hat"), "main");

    public ChefHatArmorModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();

        // 替换默认头部立方体，只保留帽子；帽子组件跟随 head 的旋转。
        root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).mirror()
                        .addBox(-4.0F, -14.0F, -4.0F, 8.0F, 6.0F, 8.0F)
                        .texOffs(0, 16).mirror()
                        .addBox(-5.0F, -18.0F, -5.0F, 10.0F, 4.0F, 10.0F),
                PartPose.ZERO);
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(mesh, 64, 32);
    }
}
