package com.KaminnoYoruneko.lots_of_food_reborn.mixin;


import com.KaminnoYoruneko.lots_of_food_reborn.blocks.CoconutBlock;
import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

//未完成

//@Mixin(Block.class)
public class BlockFaceRender {
//    private static final ThreadLocal<Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey>> OCCLUSION_CACHE = ThreadLocal.withInitial(() -> {
//        Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey> object2bytelinkedopenhashmap = new Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey>(2048, 0.25F) {
//            protected void rehash(int p_49979_) {
//            }
//        };
//        object2bytelinkedopenhashmap.defaultReturnValue((byte)127);
//        return object2bytelinkedopenhashmap;
//    });
//
//    /**
//     * @author KaminoYoruneko
//     * @reason 当周围方块为椰子时，直接强制渲染
//     */
//    @Inject(at=@At(value="RETURN",ordinal = 0),method = "shouldRenderFace",cancellable = true)
//    private static boolean shouldRenderFace(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction, BlockPos blockPos2) {
//        BlockState blockstate = blockGetter.getBlockState(blockPos2);
//        System.out.println("MY MIXIN==>");
//        if (blockstate.is(new CoconutBlock())){
//            System.out.println("I found a coconut!");
//            return true;
//        }
//        if (blockState.skipRendering(blockstate, direction)) {
//            return false;
//        } else if (blockState.supportsExternalFaceHiding() && blockstate.hidesNeighborFace(blockGetter, blockPos2, blockState, direction.getOpposite())) {
//            return false;
//        } else if (blockstate.canOcclude()) {
//            Block.BlockStatePairKey block$blockstatepairkey = new Block.BlockStatePairKey(blockState, blockstate, direction);
//            Object2ByteLinkedOpenHashMap<Block.BlockStatePairKey> object2bytelinkedopenhashmap = OCCLUSION_CACHE.get();
//            byte b0 = object2bytelinkedopenhashmap.getAndMoveToFirst(block$blockstatepairkey);
//            if (b0 != 127) {
//                return b0 != 0;
//            } else {
//                VoxelShape voxelshape = blockState.getFaceOcclusionShape(blockGetter, blockPos, direction);
//                if (voxelshape.isEmpty()) {
//                    return true;
//                } else {
//                    VoxelShape voxelshape1 = blockstate.getFaceOcclusionShape(blockGetter, blockPos2, direction.getOpposite());
//                    boolean flag = Shapes.joinIsNotEmpty(voxelshape, voxelshape1, BooleanOp.ONLY_FIRST);
//                    if (object2bytelinkedopenhashmap.size() == 2048) {
//                        object2bytelinkedopenhashmap.removeLastByte();
//                    }
//
//                    object2bytelinkedopenhashmap.putAndMoveToFirst(block$blockstatepairkey, (byte)(flag ? 1 : 0));
//                    return flag;
//                }
//            }
//        } else {
//            return true;
//        }
//    }
}
