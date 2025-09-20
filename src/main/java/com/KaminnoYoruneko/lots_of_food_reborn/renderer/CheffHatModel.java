//package com.KaminnoYoruneko.lots_of_food_reborn.renderer;
//
//import net.minecraft.client.model.geom.ModelPart;
//import net.minecraft.client.renderer.block.model.BakedQuad;
//import net.minecraft.client.renderer.block.model.ItemOverrides;
//import net.minecraft.client.renderer.block.model.ItemTransforms;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.resources.model.BakedModel;
//import net.minecraft.core.Direction;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.level.block.state.BlockState;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class CheffHatModel implements BakedModel {
//    private final ModelPart mainModel;
//
//    public CheffHatModel() {
//        // 创建Cube列表，用于存放不同层次的方块
//        List<ModelPart.Cube> cubes = new ArrayList<>();
//        Map<String, ModelPart> children = new HashMap<>();
//        ModelPart.Cube cube1=new ModelPart.Cube(0, 0, -4.0F, -14.0F, -4.0F, 8.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F, false, 0.0F, 0.0F);
//        ModelPart.Cube cube2=new ModelPart.Cube(0, 0, -5.0F, -18.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, 0.0F, 0.0F, false, 0.0F, 0.0F);
//        ModelPart.Cube cube3=new ModelPart.Cube(0, 0, -2.0F, -8.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F, false, 0.0F, 0.0F);
//
//        cubes.add(cube1);
//        cubes.add(cube2);
//        cubes.add(cube3);
//
//        this.mainModel = new ModelPart(cubes,children); // 自定义尺寸
//    }
//
//    @Override
//    public List<BakedQuad> getQuads(@Nullable BlockState p_235039_, @Nullable Direction p_235040_, RandomSource p_235041_) {
//        return null;
//    }
//
//    @Override
//    public boolean useAmbientOcclusion() {
//        return false;
//    }
//
//    @Override
//    public boolean isGui3d() {
//        return false;
//    }
//
//    @Override
//    public boolean usesBlockLight() {
//        return false;
//    }
//
//    @Override
//    public boolean isCustomRenderer() {
//        return false;
//    }
//
//    @Override
//    public TextureAtlasSprite getParticleIcon() {
//        return null;
//    }
//
//    @Override
//    public ItemOverrides getOverrides() {
//        return null;
//    }
//}
