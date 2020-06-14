package io.github.redstoneparadox.thedepths.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

class MossBlock: Block(FabricBlockSettings.copy(Blocks.OAK_LEAVES).build()) {
    private val SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0)

    override fun getOutlineShape(blockState: BlockState, blockView: BlockView, blockPos: BlockPos, context: ShapeContext): VoxelShape {
        return SHAPE
    }

    override fun getStateForNeighborUpdate(state1: BlockState, direction: Direction?, state2: BlockState, world: WorldAccess, pos1: BlockPos, pos2: BlockPos): BlockState {
        return if (!state1.canPlaceAt(world, pos1)) Blocks.AIR.defaultState else super.getStateForNeighborUpdate(state1, direction, state2, world, pos1, pos2)
    }

    override fun canPlaceAt(state: BlockState, worldView: WorldView, pos: BlockPos): Boolean {
        return worldView.getBlockState(pos.down(1)).isFullCube(worldView, pos.down(1)) && worldView.getBlockState(pos).isAir
    }
}
