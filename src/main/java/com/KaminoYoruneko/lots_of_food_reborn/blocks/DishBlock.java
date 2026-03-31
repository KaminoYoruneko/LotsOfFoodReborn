package com.KaminoYoruneko.lots_of_food_reborn.blocks;

import com.KaminoYoruneko.lots_of_food_reborn.Config;
import com.KaminoYoruneko.lots_of_food_reborn.blocks.entity.DishBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class DishBlock extends BaseEntityBlock {
    // 碰撞箱：极薄，高度为 1 像素 (1.0 / 16.0)
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 1.0D, 14.0D);

    public DishBlock() {
        super(BlockBehaviour.Properties.of(Material.DECORATION)
                .strength(0.5f)
                .noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        // 只能放在方块上表面
        return level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof DishBlockEntity dishBe) {
            ItemStackHandler handler = dishBe.getItemHandler();
            ItemStack stackInDish = handler.getStackInSlot(0);
            ItemStack handStack = player.getItemInHand(hand);

            // 潜行右键：进食逻辑
            if (player.isShiftKeyDown()) {
                if (!stackInDish.isEmpty() && stackInDish.getItem().isEdible()) {
                    if (player.canEat(stackInDish.getItem().getFoodProperties().canAlwaysEat())) {
                        // 进食逻辑：调用 finishUsingItem 确保容器返回
                        ItemStack resultStack = player.eat(level, stackInDish.copy());
                        handler.setStackInSlot(0, resultStack);
                        return InteractionResult.CONSUME;
                    }
                }
            } else {
                // 普通右键逻辑
                if (stackInDish.isEmpty()) {
                    // 存入物品
                    if (!handStack.isEmpty()) {
                        ItemStack toInsert = handStack.copy();
                        toInsert.setCount(1);
                        handler.setStackInSlot(0, toInsert);
                        if (!player.getAbilities().instabuild) {
                            handStack.shrink(1);
                        }
                        return InteractionResult.SUCCESS;
                    }
                } else {
                    // 取出物品（玩家空手且非潜行）
                    if (handStack.isEmpty()) {
                        ItemStack toGive = stackInDish.copy();
                        handler.setStackInSlot(0, ItemStack.EMPTY);
                        if (!player.getInventory().add(toGive)) {
                            player.drop(toGive, false);
                        }
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof DishBlockEntity dishBe) {
                ItemStack stack = dishBe.getItemHandler().getStackInSlot(0);
                if (!stack.isEmpty()) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack);
                }
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DishBlockEntity(pos, state);
    }
}
