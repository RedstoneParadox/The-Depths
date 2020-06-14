package io.github.redstoneparadox.thedepths.world.gen.feature

import net.minecraft.util.math.BlockPos
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*

class ScatterFeature : Feature<ScatterFeatureConfig>(ScatterFeatureConfig.CODEC) {
    override fun generate(world: ServerWorldAccess, structureAccessor: StructureAccessor, generator: ChunkGenerator, random: Random, pos: BlockPos, config: ScatterFeatureConfig): Boolean {
        if (config.state.canPlaceAt(world, pos)) {
            return world.setBlockState(pos, config.state, 0)
        }
        return false
    }
}