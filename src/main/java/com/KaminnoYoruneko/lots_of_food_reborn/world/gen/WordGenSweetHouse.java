//package com.KaminnoYoruneko.lots_of_food_reborn.world.gen;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.LevelAccessor;
//import net.minecraft.world.level.chunk.ChunkAccess;
//import net.minecraft.world.level.levelgen.structure.*;
//import net.minecraft.world.level.levelgen.structure.structures.OceanMonumentPieces;
//
//public class WorldGenSweetHouse {
//    private static final String STRUCTURE_NAME = "sweet_house";
//
//    public WorldGenSweetHouse() {
//    }
//
//    public boolean place(ChunkAccess chunk) {
//        LevelAccessor level = chunk.getWorldForge();
//        RandomSource rand = level.getRandom();
//        BlockPos pos = chunk.getPos().getWorldPosition();
//
//        Template template = this.getHouse(level.getStructureManager(), pos, world);
//
//        if (template != null) {
//            Vector3i size = template.getSize();
//            Rotation rot = Rotation.values()[rand.nextInt(4)];
//            if (this.canSpawnAt(size, pos, world, rot) && !this.isNearVillage(world, pos)) {
//                template.placeInWorld(world, pos, pos, rot, world.getRandom(), 2);
//                this.setEntity(world, pos, size, rot);
//                if (ConfigHandler.allowStructureSpawnLog) {
//                    LotsOfFoodRev.LOGGER.info("Structure: \"{}\" spawn at {}!", STRUCTURE_NAME, pos);
//                }
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private Template getHouse(TemplateManager manager, BlockPos origin, ServerWorld world) {
//        ResourceLocation location;
//        if (world.getBlockState(origin).getBlock() == Blocks.SAND) {
//            location = new ResourceLocation("lotsoffoodrev", "sweet_house_sand");
//        } else {
//            location = new ResourceLocation("lotsoffoodrev", "sweet_house");
//        }
//        return manager.get(location);
//    }
//
//    private boolean isNearVillage(ServerWorld world, BlockPos origin) {
//        // Assuming this is how villages are handled in 1.19.2
//        BlockPos villagePos = world.getStructureManager().getStructureAt(origin, "Village");
//        return villagePos != null && origin.distSqr(villagePos) <= 1000.0;
//    }
//
//    private void setEntity(ServerWorld world, BlockPos origin, Vector3i size, Rotation rot) {
//        BlockPos center = this.getRotatedCenter(origin, size, rot);
//        WitchEntity witch = new WitchEntity(world);
//        witch.setPos(center.getX(), center.getY(), center.getZ());
//        world.addFreshEntity(witch);
//    }
//
//    private boolean canSpawnAt(Vector3i size, BlockPos pos, ServerWorld world, Rotation rot) {
//        int airCount = 0;
//        int groundCount = 0;
//        BlockPos limit = this.getRotatedVector(pos, size, rot);
//        for (int x = pos.getX(); x < limit.getX(); ++x) {
//            for (int z = pos.getZ(); z < limit.getZ(); ++z) {
//                BlockPos blockPos = new BlockPos(x, pos.getY(), z);
//                BlockState state = world.getBlockState(blockPos);
//                if (isGround(state)) {
//                    groundCount++;
//                } else {
//                    airCount++;
//                }
//            }
//        }
//        return groundCount >= airCount && corner(world, new BlockPos(pos.getX(), pos.getY(), pos.getZ())) && corner(world, new BlockPos(limit.getX(), pos.getY(), limit.getZ()));
//    }
//
//    private static boolean corner(ServerWorld world, BlockPos pos) {
//        return isGround(world.getBlockState(pos.below())) && (world.getBlockState(pos).isSolidRender(world, pos) || world.getBlockState(pos.below()).isSolidRender(world, pos));
//    }
//
//    private static boolean isGround(BlockState state) {
//        return state.getMaterial() == Material.STONE || state.getMaterial().isSolid();
//    }
//
//    private BlockPos getRotatedCenter(BlockPos origin, Vector3i size, Rotation rot) {
//        switch (rot) {
//            case CLOCKWISE_180:
//                return origin.offset(-size.getX() / 2, 2, -size.getZ() / 2);
//            case CLOCKWISE_90:
//                return origin.offset(-size.getX() / 2, 2, size.getZ() / 2);
//            case COUNTERCLOCKWISE_90:
//                return origin.offset(size.getX() / 2, 2, -size.getZ() / 2);
//            case NONE:
//                return origin.offset(size.getX() / 2, 2, size.getZ() / 2);
//            default:
//                return origin.offset(size.getX() / 2, 2, size.getZ() / 2);
//        }
//    }
//
//    private BlockPos getRotatedVector(BlockPos origin, Vector3i size, Rotation rot) {
//        switch (rot) {
//            case CLOCKWISE_180:
//                return origin.offset(-size.getX(), 0, -size.getZ());
//            case CLOCKWISE_90:
//                return origin.offset(-size.getX(), 0, size.getZ());
//            case COUNTERCLOCKWISE_90:
//                return origin.offset(size.getX(), 0, -size.getZ());
//            case NONE:
//                return origin.offset(size.getX(), 0, size.getZ());
//            default:
//                return origin.offset(size.getX(), 0, size.getZ());
//        }
//    }
//}
