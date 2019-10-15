package redstoneparadox.thedepths.world

import net.minecraft.block.Blocks
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig

class DepthsChunkGeneratorConfig: OverworldChunkGeneratorConfig() {
    init {
        defaultBlock = Blocks.STONE.defaultState
        defaultFluid = Blocks.WATER.defaultState
    }
}