package redstoneparadox.thedepths.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.feature.Feature
import redstoneparadox.thedepths.block.DepthsBlocks
import java.util.*
import java.util.function.Function

class ScatterFeature(func: Function<Dynamic<*>, out ScatterFeatureConfig>) : Feature<ScatterFeatureConfig>(func) {

    override fun generate(iworld: IWorld, gen: ChunkGenerator<out ChunkGeneratorConfig>, rand: Random, pos: BlockPos, config: ScatterFeatureConfig): Boolean {
        if (config.state.block.canPlaceAt(config.state, iworld, pos)) {
            return iworld.setBlockState(pos, config.state, 0)
        }
        return false
    }
}