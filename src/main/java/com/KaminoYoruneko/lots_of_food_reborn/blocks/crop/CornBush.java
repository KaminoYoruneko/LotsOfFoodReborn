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
import net.minecraftforge.common.ForgeHooks;

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

        if ((currentAge == MAX_AGE) && (state.getValue(HaveTop))) return;

        boolean canGrow = state.getValue(CanGrowTop);

        // 首先是正常生长本层
        float growthSpeed = getGrowthSpeed(this, level, pos);
        if (ForgeHooks.onCropsGrowPre(level, pos, state,
                random.nextInt((int) (25.0F / growthSpeed) + 1) == 0)) {
            growCropTo(level, pos, state, currentAge + 1);
            ForgeHooks.onCropsGrowPost(level, pos, state);
        }

        // 基础生长逻辑
//        if (currentAge < MAX_AGE && canGrow) {
////            float growthSpeed = getGrowthSpeed(this, level, pos);
//            if (ForgeHooks.onCropsGrowPre(level, pos, state,
//                    random.nextInt((int) (25.0F / growthSpeed) + 1) == 0)) {
//                growCropTo(level, pos, state, currentAge + 1);
//                ForgeHooks.onCropsGrowPost(level, pos, state);
//            }
//        }

        // 茎秆成熟后尝试生成上层
        if (currentAge >= STEM_AGE && canGrow) {
            boolean isSuccess = tryGrowUpper(level, pos, state);
//            if (!isSuccess) {
////            float growthSpeed = getGrowthSpeed(this, level, pos);
//            if (ForgeHooks.onCropsGrowPre(level, pos, state,
//                    random.nextInt((int) (25.0F / growthSpeed) + 1) == 0)) {
//                growCropTo(level, pos, state, currentAge + 1);
//                ForgeHooks.onCropsGrowPost(level, pos, state);
//            }
//            }
        }
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int currentAge = getAge(state);
        BlockPos topPos = pos.above();
        BlockState topState = level.getBlockState(topPos);

        // 阶段一：茎干生长期
        if (currentAge < STEM_AGE) {
            selfGrowByBonemeal(state,level,pos);
            return;
        }

        // 阶段二：顶端生成期
        if (currentAge == STEM_AGE && topState.isAir()) {
            level.setBlock(topPos, CropRegister.cornBushTop.get().defaultBlockState(), 2);
            return;
        }

        // 阶段三：果实催熟期 (智能转发)
        if (topState.getBlock() instanceof CornBushTop topBlock) {
            int topAge = topState.getValue(topBlock.getAgeProperty());

            // 子逻辑：“一键成熟”
            if (currentAge == getMaxAge() - 1 && topAge == topBlock.getMaxAge() - 1) {
                level.setBlock(pos, state.setValue(AGE, getMaxAge()), 2);
                level.setBlock(topPos, topState.setValue(topBlock.getAgeProperty(), topBlock.getMaxAge()), 2);
            } 
            // 子逻辑：常规催熟
            else if (topAge < topBlock.getMaxAge()) {
                topBlock.performBonemeal(level, random, topPos, topState);
                selfGrowByBonemeal(state,level,pos);
            }else if(topAge == topBlock.getMaxAge()){
                selfGrowByBonemeal(state,level,pos);
            }
        }
    }

    private void selfGrowByBonemeal(BlockState state,ServerLevel level,BlockPos pos){
        int i = this.getAge(state) + this.getBonemealAgeIncrease(level);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }
        level.setBlock(pos, this.getStateForAge(i), 2);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
        int currentAge = getAge(state);
        if (currentAge >= MAX_AGE) {
            BlockState topState = level.getBlockState(pos.above());
            if (topState.getBlock() instanceof CornBushTop topBlock) {
                return !topBlock.isMaxAge(topState);
            }
            return false;
        }
        return true;
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
                    level.setBlock(pos, state.setValue(HaveTop, true), Block.UPDATE_ALL);
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
