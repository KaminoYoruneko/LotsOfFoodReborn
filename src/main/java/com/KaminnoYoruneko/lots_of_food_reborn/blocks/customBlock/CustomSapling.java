//package com.KaminnoYoruneko.lots_of_food_reborn.blocks.customBlock;
//
//import com.KaminnoYoruneko.lots_of_food_reborn.register.BlockRegister;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Holder;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.SaplingBlock;
//import net.minecraft.world.level.block.SoundType;
//import net.minecraft.world.level.block.grower.AbstractTreeGrower;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
//import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
//import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
//import net.minecraft.world.level.material.Material;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.Random;
//
//public class CustomSapling extends SaplingBlock {
//    public CustomSapling() {
//        super(new AbstractTreeGrower() {
//                  @Nullable
//                  @Override
//                  protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
//                      TreeConfiguration treeConfig = createPalmTreeConfiguration(p_222910_);
//                      return Feature.TREE.configured(treeConfig);
//                  }
//              }, BlockBehaviour.Properties
//                        .of(Material.GRASS)
//                        .instabreak()
//                        .sound(SoundType.GRASS)
//        );
//        private TreeConfiguration createPalmTreeConfiguration(RandomSource random) {
//            // 设置树干和树叶
//            BlockState logState = Blocks.OAK_LOG.defaultBlockState();
//            BlockState leavesState = Blocks.OAK_LEAVES.defaultBlockState();
//
//            // 设置树的高度，随机在4到8之间
//            int treeHeight = 4 + randomSource.nextInt(5);  // 高度范围4到8
//
//            // 设置树顶端四个方向的椰子方块
//            BlockState coconutBlock = BlockRegister.caramelBlock.get().defaultBlockState();
//
//            // 构建树木配置
//            TreeConfiguration.TreeConfigurationBuilder treeBuilder = new TreeConfiguration.TreeConfigurationBuilder(
//                    new BlockStateProvider(logState) {
//                        @Override
//                        protected BlockStateProviderType<?> type() {
//                            return null;
//                        }
//
//                        @Override
//                        public BlockState getState(RandomSource p_225907_, BlockPos p_225908_) {
//                            return null;
//                        }
//                    },    // 树干
//                    new BlockStateProvider(leavesState) {
//                        @Override
//                        protected BlockStateProviderType<?> type() {
//                            return null;
//                        }
//
//                        @Override
//                        public BlockState getState(RandomSource p_225907_, BlockPos p_225908_) {
//                            return null;
//                        }
//                    }  // 树叶
//            ).setHeight(treeHeight);
//
//            // 模拟树顶端的椰子分布
//            treeBuilder.setTopBlocks(coconutBlock, 2, randomSource); // 假设每个方向掉落2~4个椰子方块
//
//            return treeBuilder.build();
//        }
//    }
//}
