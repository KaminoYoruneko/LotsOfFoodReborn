package com.KaminnoYoruneko.lots_of_food_reborn.mixin;

import com.KaminnoYoruneko.lots_of_food_reborn.trees.conf.PalmTreeFoliagePlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;


@Mixin(TreeFeature.class)
public abstract class MixinTreeFeature {
    private int getMaxFreeTreeHeight(LevelSimulatedReader p_67216_, int p_67217_, BlockPos p_67218_, TreeConfiguration p_67219_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int i = 0; i <= p_67217_ + 1; ++i) {
            int j = p_67219_.minimumSize.getSizeAtHeight(p_67217_, i);

            for(int k = -j; k <= j; ++k) {
                for(int l = -j; l <= j; ++l) {
                    blockpos$mutableblockpos.setWithOffset(p_67218_, k, i, l);
                    if (!p_67219_.trunkPlacer.isFree(p_67216_, blockpos$mutableblockpos) || !p_67219_.ignoreVines && isVine(p_67216_, blockpos$mutableblockpos)) {
                        return i - 2;
                    }
                }
            }
        }

        return p_67217_;
    }

    private static boolean isVine(LevelSimulatedReader p_67278_, BlockPos p_67279_) {
        return p_67278_.isStateAtPosition(p_67279_, (p_225299_) -> {
            return p_225299_.is(Blocks.VINE);
        });
    }

//    @Inject(method = "createFoliage", at = @At("HEAD"), cancellable = true)
/**
 * @author KaminoYoruneko
 * @reason I need palms, so sorry for this code of shit.)))
 */
@Overwrite
    private boolean doPlace(
            WorldGenLevel p_225258_,
            RandomSource p_225259_,
            BlockPos p_225260_,
            BiConsumer<BlockPos, BlockState> p_225261_,
            BiConsumer<BlockPos, BlockState> p_225262_,
            BiConsumer<BlockPos, BlockState> p_225263_,
            TreeConfiguration treeConf) {
        int i = treeConf.trunkPlacer.getTreeHeight(p_225259_);
        int j = treeConf.foliagePlacer.foliageHeight(p_225259_, i, treeConf);
        boolean isPalm=false;
        if (j==0){
            isPalm=true;
        }
        int k = i - j;
        int l = treeConf.foliagePlacer.foliageRadius(p_225259_, k);
        BlockPos blockpos = treeConf.rootPlacer.map((p_225286_) -> {
            return p_225286_.getTrunkOrigin(p_225260_, p_225259_);
        }).orElse(p_225260_);
        int i1 = Math.min(p_225260_.getY(), blockpos.getY());
        int j1 = Math.max(p_225260_.getY(), blockpos.getY()) + i + 1;
        if (i1 >= p_225258_.getMinBuildHeight() + 1 && j1 <= p_225258_.getMaxBuildHeight()) {
            OptionalInt optionalint = treeConf.minimumSize.minClippedHeight();
            int k1 = this.getMaxFreeTreeHeight(p_225258_, i, blockpos, treeConf);
            if (k1 >= i || !optionalint.isEmpty() && k1 >= optionalint.getAsInt()) {
                if (treeConf.rootPlacer.isPresent() && !treeConf.rootPlacer.get().placeRoots(p_225258_, p_225261_, p_225259_, p_225260_, blockpos, treeConf)) {
                    return false;
                } else {
                    List<FoliagePlacer.FoliageAttachment> list = treeConf.trunkPlacer.placeTrunk(p_225258_, p_225262_, p_225259_, k1, blockpos, treeConf);
                    for (FoliagePlacer.FoliageAttachment p_225279_ : list) {
                        System.out.println("MIXIN:"+treeConf.foliagePlacer.toString()+" isPalm? "+isPalm);
                        if (isPalm) {
                            // 如果是棕榈树，使用自定义的 createPalmTreeFoliage 方法
                            new PalmTreeFoliagePlacer(ConstantInt.of(3),ConstantInt.of(3),2)
                                    .createFoliage(p_225258_, p_225263_, p_225259_, treeConf, k1, p_225279_, j, l);
                        } else {
                            // 否则，使用常规的 createFoliage 方法
                            treeConf.foliagePlacer.createFoliage(p_225258_, p_225263_, p_225259_, treeConf, k1, p_225279_, j, l);
                        }
                    }
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

//    @Inject(at=@At(value="RETURN",ordinal = 0),method = "doPlace",cancellable = true)
//    private void doPlace(
//            WorldGenLevel p_225258_, RandomSource p_225259_, BlockPos p_225260_, BiConsumer<BlockPos, BlockState> p_225261_, BiConsumer<BlockPos, BlockState> p_225262_, BiConsumer<BlockPos, BlockState> p_225263_, TreeConfiguration treeConf, CallbackInfoReturnable<Boolean> cir) {
//        int i = treeConf.trunkPlacer.getTreeHeight(p_225259_);
//        int j = treeConf.foliagePlacer.foliageHeight(p_225259_, i, treeConf);
//        boolean isPalm=false;
//        if (j==0){
//            isPalm=true;
//        }
//        BlockPos blockpos = treeConf.rootPlacer.map((p_225286_) -> {
//            return p_225286_.getTrunkOrigin(p_225260_, p_225259_);
//        }).orElse(p_225260_);
//        int k = i - j;
//        int l = treeConf.foliagePlacer.foliageRadius(p_225259_, k);
//        int k1 = this.getMaxFreeTreeHeight(p_225258_, i, blockpos, treeConf);
//        List<FoliagePlacer.FoliageAttachment> list = treeConf.trunkPlacer.placeTrunk(p_225258_, p_225262_, p_225259_, k1, blockpos, treeConf);
//        for (FoliagePlacer.FoliageAttachment p_225279_ : list) {
//            if (isPalm) {
//                System.out.println("VOID-->PALM");
//                // 如果是棕榈树，使用自定义的 createPalmTreeFoliage 方法
//                new PalmTreeFoliagePlacer(ConstantInt.of(3),ConstantInt.of(3),2)
//                        .createFoliage(p_225258_, p_225263_, p_225259_, treeConf, k1, p_225279_, j, l);
//            }
//        }
//    }

}