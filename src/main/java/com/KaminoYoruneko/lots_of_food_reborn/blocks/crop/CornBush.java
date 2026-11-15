package com.KaminoYoruneko.lots_of_food_reborn.blocks.crop;

import com.KaminoYoruneko.lots_of_food_reborn.blocks.customBlock.CropAge4;
import com.KaminoYoruneko.lots_of_food_reborn.register.CropRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.common.CommonHooks;

public class CornBush extends CropAge4 {

    public static final BooleanProperty CanGrowTop = BooleanProperty.create("can_grow_top");
    public static final BooleanProperty HaveTop = BooleanProperty.create("have_top");
    private static final int STEM_AGE = 3; // 茎秆成熟阶段，此时上层可生长
    private static final int MAX_AGE = 4; // 最终成熟阶段

    public CornBush() {
        super();
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(AGE, 0)
                .setValue(CanGrowTop, true)
                .setValue(HaveTop, false)
        );
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return CropRegister.corn.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CanGrowTop);
        builder.add(HaveTop);
    }

    // ========== 核心生长逻辑 ==========
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return;

        int currentAge = getAge(state);

        // 下层成熟后不再继续增加 AGE，只在允许时补齐上层
        if (currentAge >= MAX_AGE) {
            if (state.getValue(CanGrowTop)) {
                tryGrowUpper(level, pos, state);
            }
            return;
        }

        float growthSpeed = getGrowthSpeed(state, level, pos);
        if (CommonHooks.canCropGrow(level, pos, state,
                random.nextInt((int) (25.0F / growthSpeed) + 1) == 0)) {
            int newAge = currentAge + 1;
            BlockState grown = state.setValue(AGE, newAge);
            level.setBlock(pos, grown, Block.UPDATE_ALL);
            CommonHooks.fireCropGrowPost(level, pos, state);

            if (newAge >= STEM_AGE && grown.getValue(CanGrowTop)) {
                tryGrowUpper(level, pos, grown);
            }
        }
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int currentAge = getAge(state);
        BlockPos topPos = pos.above();
        BlockState topState = level.getBlockState(topPos);

        // 1. 下层还没长到能支撑上层的阶段：只催熟下层
        if (currentAge < STEM_AGE) {
            level.setBlock(pos, state.setValue(AGE, currentAge + 1), Block.UPDATE_ALL);
            return;
        }

        // 2. 下层已经能支撑上层：先长出上层，再考虑下层玉米
        if (currentAge < MAX_AGE) {
            if (topState.isAir()) {
                tryGrowUpper(level, pos, state);
            } else {
                // 上层已经存在，优先让下层结出玉米
                level.setBlock(pos, state.setValue(AGE, MAX_AGE), Block.UPDATE_ALL);
            }
            return;
        }

        // 3. 下层已成熟：催熟上层
        if (topState.getBlock() instanceof CornBushTop topBlock) {
            int topAge = topState.getValue(topBlock.getAgeProperty());
            if (topAge < topBlock.getMaxAge()) {
                level.setBlock(topPos,
                        topState.setValue(topBlock.getAgeProperty(), topAge + 1),
                        Block.UPDATE_ALL);
            }
            return;
        }

        // 下层成熟但上层缺失：补种上层
        if (topState.isAir()) {
            tryGrowUpper(level, pos, state);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        int currentAge = getAge(state);
        if (currentAge < MAX_AGE) {
            return true;
        }

        BlockState topState = level.getBlockState(pos.above());
        if (topState.getBlock() instanceof CornBushTop topBlock) {
            return !topBlock.isMaxAge(topState);
        }
        return topState.isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    private boolean tryGrowUpper(ServerLevel level, BlockPos pos, BlockState state) {
        BlockPos abovePos = pos.above();
        if (level.isEmptyBlock(abovePos)) {
            BlockState upperState = CropRegister.cornBushTop.get().defaultBlockState();
            if (upperState.canSurvive(level, abovePos)) {
                boolean isSuccess = level.setBlock(abovePos, upperState, Block.UPDATE_ALL);
                if (isSuccess) {
                    level.setBlock(pos,
                            state.setValue(HaveTop, true).setValue(CanGrowTop, false),
                            Block.UPDATE_ALL);
                }
                return isSuccess;
            }
        }
        return false;
    }

    private void growCropTo(ServerLevel level, BlockPos pos, BlockState state, int newAge) {
        level.setBlock(pos, state.setValue(AGE, newAge), Block.UPDATE_ALL);
        if (newAge >= STEM_AGE) {
            tryGrowUpper(level, pos, state);
        }
    }

    // ========== 方块更新逻辑 ==========
    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState,
                                  LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (dir == Direction.UP) {
            boolean isTopAir = level.getBlockState(pos.above()).is(Blocks.AIR);
            boolean isHaveTop = level.getBlockState(pos.above()).is(CropRegister.cornBushTop.get());
            return state.setValue(CanGrowTop, isTopAir).setValue(HaveTop, isHaveTop);
        }
        return super.updateShape(state, dir, neighborState, level, pos, neighborPos);
    }

    // ========== 环境验证 ==========
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos groundPos = pos.below();
        BlockState groundState = level.getBlockState(groundPos);
        return groundState.is(Blocks.FARMLAND) || groundState.is(Blocks.GRASS_BLOCK);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos,
                                Block neighborBlock, BlockPos neighborPos, boolean moved) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, moved);
        if (neighborPos.equals(pos.above())) {
            boolean isTopAir = level.getBlockState(pos.above()).is(Blocks.AIR);
            boolean isHaveTop = level.getBlockState(pos.above()).is(CropRegister.cornBushTop.get());
            level.setBlock(pos, state.setValue(CanGrowTop, isTopAir).setValue(HaveTop, isHaveTop), Block.UPDATE_CLIENTS);
        }
    }
}
