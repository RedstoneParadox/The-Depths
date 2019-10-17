package redstoneparadox.thedepths.world.gen.surfacebuilder

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig
import redstoneparadox.thedepths.block.DepthsBlocks

open class DepthsSurfaceConfig(val secondaryStone: BlockState): SurfaceConfig {

    override fun getTopMaterial(): BlockState = Blocks.STONE.defaultState

    override fun getUnderMaterial(): BlockState = DepthsBlocks.DEEP_ROCK.defaultState

    fun getSecondaryUnderMaterial(): BlockState = secondaryStone

    companion object {
        val DEFAULT: DepthsSurfaceConfig = DepthsSurfaceConfig(Blocks.STONE.defaultState)
    }
}