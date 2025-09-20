package com.KaminnoYoruneko.lots_of_food_reborn.blocks.crop;

import com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock.CropAge4;
import com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister;
import com.KaminnoYoruneko.lots_of_food_reborn.register.CropRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
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
                .setValue(HaveTop,false)
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

        if ((currentAge==MAX_AGE) && (state.getValue(HaveTop)==true)) return;//如果成熟了且上层有了，那么直接就不管了）

        boolean canGrow = state.getValue(CanGrowTop);

        System.out.println("Random Tick==>CurAge="+currentAge+" CanGrow="+canGrow);

        // 基础生长逻辑
        if (currentAge < MAX_AGE && canGrow) {
            float growthSpeed = getGrowthSpeed(this, level, pos);
            System.out.println("**Random Tick==>Try grow below");
            if (ForgeHooks.onCropsGrowPre(level, pos, state,
                    random.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                System.out.println("**Random Tick==>Try grow below Success");
                growCrop(level, pos, state, currentAge + 1);
                ForgeHooks.onCropsGrowPost(level, pos, state);
            }
        }

        // 茎秆成熟后尝试生成上层
        if (currentAge >= STEM_AGE && canGrow) {
            System.out.println("**Random Tick==>Try grow upper");
            boolean isSuccess = tryGrowUpper(level, pos, state);
            if (!isSuccess){
                System.out.println("**Random Tick==>Try grow upper Failed, try grow below");
                //如果生长失败，那么直接生长自身
                float growthSpeed = getGrowthSpeed(this, level, pos);
                if (ForgeHooks.onCropsGrowPre(level, pos, state,
                        random.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                    System.out.println("**Random Tick==>Try grow below Success");
                    growCrop(level, pos, state, currentAge + 1);
                    ForgeHooks.onCropsGrowPost(level, pos, state);
                }
            }
        }
    }

    private boolean tryGrowUpper(ServerLevel level, BlockPos pos, BlockState state) {
        BlockPos abovePos = pos.above();
        if (level.isEmptyBlock(abovePos)) {
            BlockState upperState = CropRegister.cornBushTop.get().defaultBlockState();
            if (upperState.canSurvive(level, abovePos)) {
                boolean isSuccess = level.setBlock(abovePos, upperState, Block.UPDATE_ALL);
                if (isSuccess){
                    //如果生长上层成功，则“已有上层”
                    level.setBlock(pos, state.setValue(HaveTop, true), Block.UPDATE_ALL);
                }
                return isSuccess;
            }
        }
        return false;
    }

    // ========== 方块更新逻辑 ==========
    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState,
                                  LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (dir == Direction.UP) {
            boolean isTopAir = level.getBlockState(pos.above()).is(Blocks.AIR);
            boolean isHaveTop = level.getBlockState(pos.above()).is(CropRegister.cornBushTop.get());

            //上方是空气，那么可以生长
            //反之不可以
            level.setBlock(pos, state.setValue(CanGrowTop, isTopAir), Block.UPDATE_CLIENTS);

            //上方是top，那么有上层
            //反之没有
            level.setBlock(pos, state.setValue(HaveTop, isHaveTop), Block.UPDATE_CLIENTS);
//            //方向为上时
//            //如果此时age>=3且上层无cornBushTop，那么可以生长，设置为true，反之false
//            boolean hasUpper = neighborState.is(CropRegister.cornBushTop.get());
//            return state.setValue(CanGrowTop,
//                    (!hasUpper)&&(getAge(state)>=STEM_AGE)//还没生长上层且茎秆成熟
//            );
        }
        return super.updateShape(state, dir, neighborState, level, pos, neighborPos);
    }

    // ========== 骨粉处理 ==========
    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int currentAge = getAge(state);
        boolean canGrow = state.getValue(CanGrowTop);
        boolean haveTop = state.getValue(HaveTop);

        System.out.println("Bone Meal==>CurAge="+currentAge+" CanGrow="+canGrow+" HaveTop="+haveTop);

        //若年龄0~StemAge，生长下层
        //若年龄>=StemAge
        //  若上层存在
        //      若下层是MaxAge
        //          若上层年龄小于3，则生长上层
        //      反之则生长下层
        //  反之，若没有上层
        //      若可以生长上层，则强制生长出上层
        //          若生长上层失败，则生长下层

        if (currentAge < STEM_AGE){
            System.out.println("**Bone Meal==>Stem not fully grown");
            // 加速下层生长
            boostGrow(currentAge,level,random, pos, state);
        } else {
            System.out.println("**Bone Meal==>Stem fully grown");
            if (haveTop) {
                System.out.println("**Bone Meal==>Have top");
                if (currentAge == MAX_AGE) {
                    System.out.println("**Bone Meal==>Now max age");
                    if (level.getBlockState(pos.above()).is(CropRegister.cornBushTop.get())){
                        System.out.println("**Bone Meal==>Upper is top");
                        //保险起见，判断是否为top
                        if (level.getBlockState(pos.above()).getValue(AGE) < 3){
                            System.out.println("**Bone Meal==>Upper not fully grown, grow upper");
                            boostGrow(currentAge,level,random, pos.above(), state);
                        }
                    }
                }else{
                    System.out.println("**Bone Meal==>Not max age");
                    boostGrow(currentAge,level,random, pos, state);
                }
            }else {
                System.out.println("**Bone Meal==>No top");
                if (canGrow){
                    System.out.println("**Bone Meal==>Can grow, try setblock upper");
                    boolean isSuccess = tryGrowUpper(level,pos,state);
                    if (!isSuccess){
                        System.out.println("**Bone Meal==>Failed");
                        if (currentAge < MAX_AGE){
                            System.out.println("**Bone Meal==>Not max age, grow below");
                            boostGrow(currentAge,level,random, pos, state);
                        }
                    }
                }
            }
        }

//        if(currentAge==MAX_AGE) {//下层彻底长好
//            System.out.println("**Bone Meal==>Below fully grown");
//            if (haveTop&&level.getBlockState(pos.above()).getValue(AGE)==3){
//                //存在上层
//                //上层最高年龄为3
//                //我测，我写的什么jb代码，好一个硬编码捏妈妈的
//                System.out.println("**Bone Meal==>Try grow upper.");
//                // 促进上层生长
//                BlockState upperState = level.getBlockState(pos.above());
//                if (upperState.getBlock() instanceof BonemealableBlock growable) {
//                    growable.performBonemeal(level, random, pos.above(), upperState);
//                }
//            }
//        }
    }

    /*
     *通过骨粉催生
     * */
    private void boostGrow(int currentAge,ServerLevel level, RandomSource random, BlockPos pos, BlockState state){
        int boost = Mth.nextInt(random, 1, 4);
        int newAge = currentAge + boost;
        growCrop(level, pos, state, newAge);
        ForgeHooks.onCropsGrowPost(level, pos, state);
    }

    private void growCrop(ServerLevel level, BlockPos pos, BlockState state, int newAge) {
        level.setBlock(pos, state.setValue(AGE, newAge), Block.UPDATE_ALL);
        if (newAge >= STEM_AGE) {
            tryGrowUpper(level, pos, state);
        }
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
        if (neighborPos.equals(pos.above())){
            //如果改变来源方向是上方
            boolean isTopAir = level.getBlockState(pos.above()).is(Blocks.AIR);
            boolean isHaveTop = level.getBlockState(pos.above()).is(CropRegister.cornBushTop.get());

            //上方是空气，那么可以生长
            //反之不可以
            level.setBlock(pos, state.setValue(CanGrowTop, isTopAir), Block.UPDATE_CLIENTS);

            //上方是top，那么有上层
            //反之没有
            level.setBlock(pos, state.setValue(HaveTop, isHaveTop), Block.UPDATE_CLIENTS);
        }
    }
    
//    public static final BooleanProperty CanGrowTop = BooleanProperty.create("CanGrowTop");
//    @Override
//    protected ItemLike getBaseSeedId() {
//        return CropRegister.corn.get();
//    }
//
//    //通过随机刻自然生长上层
//    @Override
//    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
//        if (!level.isAreaLoaded(pos, 1)) return;
//
//        // 先执行基础生长逻辑
//        super.randomTick(state, level, pos, random);
//
//        // 检查是否达到最大生长阶段
//        if (isMaxAge(state) && level.isEmptyBlock(pos.above())) {
//            BlockPos abovePos = pos.above();
//            BlockState upperBlock = CropRegister.cornBushTop.get().defaultBlockState();
//
//            // 检查上层位置是否可放置
//            if (upperBlock.canSurvive(level, abovePos)) {
//                level.setBlock(abovePos, upperBlock, Block.UPDATE_ALL);
//                // 重置当前作物年龄以便继续生长
//                level.setBlock(pos, state.setValue(AGE, getMaxAge() - 1), Block.UPDATE_ALL);
//            }
//        }
//    }
//
//    // 通过骨粉手动生长上层
//    @Override
//    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
//        int currentAge = getAge(state);
//        int growthAmount = getBonemealAgeIncrease(level);
//
//        if (currentAge < getMaxAge()) {
//            // 正常生长逻辑
//            int newAge = Math.min(currentAge + growthAmount, getMaxAge());
//            level.setBlock(pos, state.setValue(AGE, newAge), Block.UPDATE_ALL);
//        } else {
//            // 尝试促进上层生长
//            BlockPos abovePos = pos.above();
//            BlockState aboveState = level.getBlockState(abovePos);
//
//            if (aboveState.getBlock() instanceof BonemealableBlock upperCrop) {
//                if (upperCrop.isValidBonemealTarget(level, abovePos, aboveState, false)) {
//                    upperCrop.performBonemeal(level, random, abovePos, aboveState);
//                }
//            } else if (level.isEmptyBlock(abovePos)) {
//                // 创建上层作物
//                BlockState upperBlock = CropRegister.cornBushTop.get().defaultBlockState();
//                if (upperBlock.canSurvive(level, abovePos)) {
//                    level.setBlock(abovePos, upperBlock, Block.UPDATE_ALL);
//                }
//            }
//        }
//    }
//
//    // 获取骨粉加速的生长量
//    protected int getBonemealAgeIncrease(Level level) {
//        return Mth.nextInt(level.random, 2, 4);
//    }
//
//    // 处理方块更新
//    @Override
//    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean moved) {
//        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, moved);
//
//        // 如果上层方块被破坏
//        if (neighborPos.equals(pos.above())) {
//            BlockState aboveState = level.getBlockState(neighborPos);
//            if (!aboveState.is(CropRegister.cornBushTop.get())) {
//                // 重置年龄以允许重新生长
//                level.setBlock(pos, state.setValue(AGE, getMaxAge() - 1), Block.UPDATE_ALL);
//            }
//        }
//    }
}
