package com.KaminnoYoruneko.lots_of_food_reborn.trees.conf;

import com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.material.Material;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;

import static com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister.coconutBlock; // 假设椰子方块已经注册

public class PalmTreeFoliagePlacer extends BlobFoliagePlacer {
    public static final Codec<PalmTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((p_68427_) -> {
        return blobParts(p_68427_).apply(p_68427_, PalmTreeFoliagePlacer::new);
    });

    private final int height;// 树叶的高度
    public final int leafrad;
    public static final BlockState LOG = Blocks.JUNGLE_WOOD.defaultBlockState();  // 树干的 BlockState
    public static final BlockState LEAF = Blocks.JUNGLE_LEAVES.defaultBlockState();  // 树叶的 BlockState
    public static final BlockState COCONUT = coconutBlock.get().defaultBlockState();  // 椰子的 BlockState


    // 构造函数
    public PalmTreeFoliagePlacer(IntProvider p_161356_, IntProvider p_161357_, int p_161358_) {
        super(ConstantInt.of(0), p_161357_, p_161358_);
        this.height = p_161358_; // 将高度传递给 BlobFoliagePlacer
        this.leafrad=p_161356_.getMaxValue();
    }

    public BlockPos lastPos;
    @Override
    protected void createFoliage(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> p_225521_, RandomSource p_225522_, TreeConfiguration p_225523_, int p_225524_, FoliageAttachment p_225525_, int p_225526_, int p_225527_, int p_225528_) {
        // 获取树的顶端位置
        BlockPos topWoodPos = p_225525_.pos().above(p_225524_ - 1); // 树干顶部的位置
        int leafRadius = leafrad; // 树叶的半径

        System.out.println("GenHead");

        // 先调用父类的 createFoliage 方法生成基本树叶
//        super.createFoliage(level, p_225521_, p_225522_, p_225523_, p_225524_, p_225525_, p_225526_, p_225527_, p_225528_);

        // 以下代码处理棕榈树生成逻辑

        // 获取生成棕榈树的相关随机值
        int PalmType = p_225522_.nextInt(3);  // 随机生成棕榈树类型
        int PalmHeight = 7 + p_225522_.nextInt(5);  // 随机生成棕榈树高度
        int Orientation = p_225522_.nextInt(4);  // 随机生成树干的朝向

        BlockPos InitialPos = p_225525_.pos();  // 初始位置为树干的底部
        Set<BlockPos> LogsCoords = new HashSet<BlockPos>(){
            @Override
            public boolean add(BlockPos blockPos) {
                lastPos=blockPos;
                return super.add(blockPos);
            }
        };  // 用于存储树干位置的集合
        Set<BlockPos> LeavesCoords = new HashSet<>();  // 用于存储树叶位置的集合
        Set<BlockPos> CoconutCoords = new HashSet<>();  // 用于存储椰子位置的集合

        int i;

        // 根据不同的棕榈树类型进行生成
        if (PalmType == 0) {
            // Type 0: 生成简单的棕榈树干
//            System.out.println("NormalTree");
            for (i = 0; i < PalmHeight; ++i) {
                LogsCoords.add(InitialPos);
                InitialPos = InitialPos.above();
            }
        } else if (PalmType == 1) {
            // Type 1: 生成带有分枝的棕榈树
//            System.out.println("BranchedTree");
            for (i = 0; i < PalmHeight; ++i) {
                LogsCoords.add(InitialPos);
                if (PalmHeight > 8 && (i == PalmHeight - 7 || i == PalmHeight - 5 || i == PalmHeight - 3)) {
                    // 根据方向生成分枝
                    if (Orientation == 0) {
                        InitialPos = InitialPos.east();
                    } else if (Orientation == 1) {
                        InitialPos = InitialPos.west();
                    } else if (Orientation == 2) {
                        InitialPos = InitialPos.south();
                    } else {
                        InitialPos = InitialPos.north();
                    }
                    LogsCoords.add(InitialPos);
                }
                InitialPos = InitialPos.above();
            }
        } else {
            // Type 2: 生成带有更复杂结构的棕榈树
//            System.out.println("CrackedTree");
            PalmHeight = Math.max(PalmHeight, 10);
            for (int dir = 0; dir < 4; ++dir) {
                int xVerifDebut, xVerifFin, zVerifDebut, zVerifFin;
                switch (dir) {
                    case 0:
                        xVerifDebut = InitialPos.getX() - 1;
                        xVerifFin = InitialPos.getX() + 1;
                        zVerifDebut = InitialPos.getZ() - 6;
                        zVerifFin = InitialPos.getZ() - 1;
                        break;
                    case 1:
                        xVerifDebut = InitialPos.getX() + 1;
                        xVerifFin = InitialPos.getX() + 6;
                        zVerifDebut = InitialPos.getZ() - 1;
                        zVerifFin = InitialPos.getZ() + 1;
                        break;
                    case 2:
                        xVerifDebut = InitialPos.getX() - 1;
                        xVerifFin = InitialPos.getX() + 1;
                        zVerifDebut = InitialPos.getZ() + 1;
                        zVerifFin = InitialPos.getZ() + 6;
                        break;
                    default:
                        xVerifDebut = InitialPos.getX() - 6;
                        xVerifFin = InitialPos.getX() - 1;
                        zVerifDebut = InitialPos.getZ() - 1;
                        zVerifFin = InitialPos.getZ() + 1;
                }

//                boolean added = false;
//                for (int j = xVerifDebut; j <= xVerifFin && !added; ++j) {
//                    for (int k = InitialPos.getY() - 2; k <= InitialPos.getY() && !added; ++k) {
//                        for (int l = zVerifDebut; l <= zVerifFin; ++l) {
//                            if (level.getBlockState(new BlockPos(j, k, l)).getBlock() == Blocks.SAND) {
//                                added = true;
//                                break;
//                            }
//                        }
//                    }
//                }
                boolean added = false;

// 遍历 x、y 和 z 范围
                for (int j = xVerifDebut; j <= xVerifFin && !added; ++j) {
                    for (int k = InitialPos.getY() - 2; k <= InitialPos.getY() && !added; ++k) {
                        for (int l = zVerifDebut; l <= zVerifFin && !added; ++l) {
                            BlockPos checkPos = new BlockPos(j, k, l);

                            // 使用 isStateAtPosition 方法检查当前位置是否是沙子方块
                            if (level.isStateAtPosition(checkPos, state -> state.getBlock() == Blocks.SAND)) {
                                added = true;  // 找到沙子方块
                                break;  // 跳出内层循环
                            }
                        }
                    }
                }

                //ends
            }

            // 根据方向生成树干和树叶
            for (i = 0; i < PalmHeight; ++i) {
                LogsCoords.add(InitialPos);
                if (i == PalmHeight - 1) {
                    InitialPos = InitialPos.above();
                    LogsCoords.add(InitialPos);
                }
                if (i != PalmHeight - 1) {
                    if (Orientation == 0) {
                        InitialPos = InitialPos.east();
                    } else if (Orientation == 1) {
                        InitialPos = InitialPos.west();
                    } else if (Orientation == 2) {
                        InitialPos = InitialPos.south();
                    } else {
                        InitialPos = InitialPos.north();
                    }
                }
            }
        }

        // 树叶的位置
        LeavesCoords.add(InitialPos.east());
        LeavesCoords.add(InitialPos.west());
        LeavesCoords.add(InitialPos);
        LeavesCoords.add(InitialPos.north());
        LeavesCoords.add(InitialPos.south());

        // 添加一些额外的树叶位置
//        for (i = 0; i < 3; ++i) {
//            LeavesCoords.add(InitialPos.offset(-2 - i, -i, 0));
//            LeavesCoords.add(InitialPos.offset(2 + i, -i, 0));
//            LeavesCoords.add(InitialPos.offset(0, -i, -2 - i));
//            LeavesCoords.add(InitialPos.offset(0, -i, 2 + i));
//            LeavesCoords.add(InitialPos.offset(-1 - i, -i, -1 - i));
//            LeavesCoords.add(InitialPos.offset(-1 - i, -i, 1 + i));
//            LeavesCoords.add(InitialPos.offset(1 + i, -i, -1 - i));
//            LeavesCoords.add(InitialPos.offset(1 + i, -i, 1 + i));
//        }
        for (i = 0; i < 3; ++i) {
            // 现有的树叶扩展
            LeavesCoords.add(InitialPos.offset(-2 - i, -i, 0));  // 向左
            LeavesCoords.add(InitialPos.offset(2 + i, -i, 0));   // 向右
            LeavesCoords.add(InitialPos.offset(0, -i, -2 - i));  // 向前
            LeavesCoords.add(InitialPos.offset(0, -i, 2 + i));   // 向后
            LeavesCoords.add(InitialPos.offset(-1 - i, -i, -1 - i));  // 向左前
            LeavesCoords.add(InitialPos.offset(-1 - i, -i, 1 + i));   // 向左后
            LeavesCoords.add(InitialPos.offset(1 + i, -i, -1 - i));   // 向右前
            LeavesCoords.add(InitialPos.offset(1 + i, -i, 1 + i));    // 向右后

            // 添加额外的延伸格子
            LeavesCoords.add(InitialPos.offset(-3 - i, -i, 0));  // 向左再延伸
            LeavesCoords.add(InitialPos.offset(3 + i, -i, 0));   // 向右再延伸
            LeavesCoords.add(InitialPos.offset(0, -i, -3 - i));  // 向前再延伸
            LeavesCoords.add(InitialPos.offset(0, -i, 3 + i));   // 向后再延伸
            LeavesCoords.add(InitialPos.offset(-2 - i, -i, -2 - i));  // 向左前再延伸
            LeavesCoords.add(InitialPos.offset(-2 - i, -i, 2 + i));   // 向左后再延伸
            LeavesCoords.add(InitialPos.offset(2 + i, -i, -2 - i));   // 向右前再延伸
            LeavesCoords.add(InitialPos.offset(2 + i, -i, 2 + i));    // 向右后再延伸
        }

        // 椰子的位置
        CoconutCoords.add(InitialPos.offset(-1, -1, 0));
        CoconutCoords.add(InitialPos.offset(1, -1, 0));
        CoconutCoords.add(InitialPos.offset(0, -1, -1));
        CoconutCoords.add(InitialPos.offset(0, -1, 1));

        // 放置树干、树叶和椰子
        if (
                true
//                   this.canPlaceAt(level, LogsCoords)
//                && this.canPlaceAt(level, LeavesCoords)
//                && this.canPlaceAt(level, CoconutCoords)
        ) {
            LogsCoords.forEach(logPos -> {
                if (level.isStateAtPosition(logPos, state -> {
                    return state.getMaterial().isReplaceable()&&!state.is(Blocks.JUNGLE_WOOD);
                })){
                    p_225521_.accept(logPos, LOG);
                }
            });  // 放置树干
//            if (lastPos!=null){
//                p_225521_.accept(lastPos, Blocks.JUNGLE_LOG.defaultBlockState());
//            }

            LeavesCoords.forEach(leavePos -> {
                if (level.isStateAtPosition(leavePos, state -> {
                    return state.getMaterial().isReplaceable()&&!state.is(Blocks.JUNGLE_WOOD);
                })){
                    p_225521_.accept(
                            leavePos, LEAF.setValue(
                                    LeavesBlock.PERSISTENT,true
                            )
                    );
                }
            });  // 放置树叶
            CoconutCoords.forEach(coconutPos -> {
                if (level.isStateAtPosition(coconutPos, state -> {
                    return state.getMaterial().isReplaceable()&&!state.is(Blocks.JUNGLE_WOOD);
                })){
                    p_225521_.accept(coconutPos, COCONUT);
                }
            });  // 放置椰子
        }
    }

//    private boolean canPlaceAt(Level level, Collection<BlockPos> coords) {
//        for (BlockPos pos : coords) {
//            BlockState state = level.getBlockState(pos); // 获取当前坐标位置的块状态
//
//            // 如果当前状态不是可替换的（如水、泥土等），或当前块是棕榈树幼苗，就不允许放置
//            if (!state.getMaterial().isReplaceable() || state.getMaterial() == Material.WATER || state.is(BlockRegister.palmTreeSapling.get())) {
//                return false;
//            }
//        }
//        return true; // 如果所有坐标都符合放置条件，则返回true
//    }


    private boolean canPlaceAt(LevelSimulatedReader level, Collection<BlockPos> coords) {
        // 遍历所有给定的坐标
        for (BlockPos pos : coords) {
            // 检查该位置是否是可替换的（例如泥土、水等不可替换的方块）
            boolean isReplaceable = level.isStateAtPosition(pos, state -> {
                        return state.getMaterial().isReplaceable();
                    });

            // 检查方块是否是水或棕榈树幼苗（我们需要避免在水或幼苗上放置其他方块）
            boolean isWater = level.isStateAtPosition(pos, state -> state.getMaterial() == Material.WATER);
            boolean isPalmSapling = level.isStateAtPosition(pos, state -> state.is(BlockRegister.palmTreeSapling.get()));

            // 如果是不可替换的方块，或者是水，或者是棕榈树幼苗，则不能放置
            if (!isReplaceable || isWater || isPalmSapling) {
                return false;
            }
        }

        // 如果所有坐标都符合条件，则返回true
        return true;
    }

    @Override
    public int foliageHeight(RandomSource p_225516_, int p_225517_, TreeConfiguration p_225518_) {
        return this.height;  // 返回树叶的高度
    }

    // 可选：你可以覆盖该方法来定制生成树叶时哪些位置会跳过
    @Override
    protected boolean shouldSkipLocation(RandomSource p_225509_, int p_225510_, int p_225511_, int p_225512_, int p_225513_, boolean p_225514_) {
        return super.shouldSkipLocation(p_225509_, p_225510_, p_225511_, p_225512_, p_225513_, p_225514_);
    }

    // 你还可以覆盖其他方法来进一步定制生成行为
}