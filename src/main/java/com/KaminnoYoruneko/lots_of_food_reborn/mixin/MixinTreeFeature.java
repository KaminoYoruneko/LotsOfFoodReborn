package com.KaminnoYoruneko.lots_of_food_reborn.mixin;

import com.KaminnoYoruneko.lots_of_food_reborn.trees.conf.PalmTreeFoliagePlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
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
            WorldGenLevel level,
            RandomSource rand,
            BlockPos pos,
            BiConsumer<BlockPos, BlockState> p_225261_,
            BiConsumer<BlockPos, BlockState> p_225262_,
            BiConsumer<BlockPos, BlockState> p_225263_,
            TreeConfiguration treeConf) {
        boolean isPalm=false;

        int i = treeConf.trunkPlacer.getTreeHeight(rand);
        int j = treeConf.foliagePlacer.foliageHeight(rand, i, treeConf);//高度
//        if (j==0){
//            if (treeConf.foliagePlacer instanceof PalmTreeFoliagePlacer){//BlobFoliagePlacer.class
//                isPalm=true;
//            }
//        }
    //类型被强制转换为BlobFoliagePlacer，此处不可使用instanceof palmxxxx来判断传入的生成器

        int k = i - j;
        int l = treeConf.foliagePlacer.foliageRadius(rand, k);//半径

        System.out.println("l="+l+", j="+j+", Biome="+level.getBiome(pos));

        if(l==0&&j==0&&level.getBiome(pos).is(Biomes.BEACH)){
            isPalm=true;
        }

        BlockPos blockpos = treeConf.rootPlacer.map((p_225286_) -> {
            return p_225286_.getTrunkOrigin(pos, rand);
        }).orElse(pos);
        int i1 = Math.min(pos.getY(), blockpos.getY());
        int j1 = Math.max(pos.getY(), blockpos.getY()) + i + 1;
        if (i1 >= level.getMinBuildHeight() + 1 && j1 <= level.getMaxBuildHeight()) {
            OptionalInt optionalint = treeConf.minimumSize.minClippedHeight();
            int k1 = this.getMaxFreeTreeHeight(level, i, blockpos, treeConf);
            if (k1 >= i || !optionalint.isEmpty() && k1 >= optionalint.getAsInt()) {
                if (treeConf.rootPlacer.isPresent() && !treeConf.rootPlacer.get().placeRoots(level, p_225261_, rand, pos, blockpos, treeConf)) {
                    return false;
                } else {
                    List<FoliagePlacer.FoliageAttachment> list = treeConf.trunkPlacer.placeTrunk(level, p_225262_, rand, k1, blockpos, treeConf);
                    for (FoliagePlacer.FoliageAttachment p_225279_ : list) {
                        System.out.println("MIXIN:"+treeConf.foliagePlacer.toString()+" isPalm? "+isPalm);
                        if (isPalm) {
                            // 如果是棕榈树，使用自定义的 createPalmTreeFoliage 方法
//                            System.out.println("PALM==>"+pos.toString()+" "+level.getBlockEntity(pos).toString());
                            new PalmTreeFoliagePlacer(ConstantInt.of(3),ConstantInt.of(3),2)
                                    .createFoliage(level, p_225263_, rand, treeConf, k1, p_225279_, j, l);
                        } else {
                            // 否则，使用常规的 createFoliage 方法
                            treeConf.foliagePlacer.createFoliage(level, p_225263_, rand, treeConf, k1, p_225279_, j, l);
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
//            WorldGenLevel level, RandomSource rand, BlockPos pos, BiConsumer<BlockPos, BlockState> p_225261_, BiConsumer<BlockPos, BlockState> p_225262_, BiConsumer<BlockPos, BlockState> p_225263_, TreeConfiguration treeConf, CallbackInfoReturnable<Boolean> cir) {
//        int i = treeConf.trunkPlacer.getTreeHeight(rand);
//        int j = treeConf.foliagePlacer.foliageHeight(rand, i, treeConf);
//        boolean isPalm=false;
//        if (j==0){
//            isPalm=true;
//        }
//        BlockPos blockpos = treeConf.rootPlacer.map((p_225286_) -> {
//            return p_225286_.getTrunkOrigin(pos, rand);
//        }).orElse(pos);
//        int k = i - j;
//        int l = treeConf.foliagePlacer.foliageRadius(rand, k);
//        int k1 = this.getMaxFreeTreeHeight(level, i, blockpos, treeConf);
//        List<FoliagePlacer.FoliageAttachment> list = treeConf.trunkPlacer.placeTrunk(level, p_225262_, rand, k1, blockpos, treeConf);
//        for (FoliagePlacer.FoliageAttachment p_225279_ : list) {
//            if (isPalm) {
//                System.out.println("VOID-->PALM");
//                // 如果是棕榈树，使用自定义的 createPalmTreeFoliage 方法
//                new PalmTreeFoliagePlacer(ConstantInt.of(3),ConstantInt.of(3),2)
//                        .createFoliage(level, p_225263_, rand, treeConf, k1, p_225279_, j, l);
//            }
//        }
//    }

}