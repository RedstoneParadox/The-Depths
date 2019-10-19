package redstoneparadox.thedepths.world.gen.surfacebuilder

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig
import redstoneparadox.thedepths.block.DepthsBlocks

open class DepthsSurfaceConfig(
    val primaryStone: BlockState = DepthsBlocks.DEEP_ROCK.defaultState,
    val secondaryStone: BlockState = Blocks.STONE.defaultState,
    val fluid: BlockState = Blocks.WATER.defaultState,
    val floorMinHeight: Int = 16,
    val floorMaxHeight: Int = 18
): SurfaceConfig {

    override fun getTopMaterial(): BlockState = primaryStone

    override fun getUnderMaterial(): BlockState = primaryStone
}