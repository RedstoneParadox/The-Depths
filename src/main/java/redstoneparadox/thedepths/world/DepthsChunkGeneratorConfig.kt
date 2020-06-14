package redstoneparadox.thedepths.world

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks

class DepthsChunkGeneratorConfig {
    var defaultBlock: BlockState = Blocks.STONE.defaultState
    var defaultFluid: BlockState = Blocks.WATER.defaultState
}