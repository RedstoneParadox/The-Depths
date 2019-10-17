package redstoneparadox.thedepths.world.gen.surfacebuilder

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import redstoneparadox.thedepths.world.noise.PitNoiseSampler
import java.util.*
import java.util.function.Function

class DepthsSurfaceBuilder<T: DepthsSurfaceConfig>(function_1: Function<Dynamic<*>, out T>?) : SurfaceBuilder<T>(function_1) {

    var pitNoiseSampler: PitNoiseSampler? = null

    override fun generate(
        random: Random,
        chunk: Chunk,
        biome: Biome,
        x: Int,
        y: Int,
        z: Int,
        var7: Double,
        var9: BlockState?,
        var10: BlockState?,
        var11: Int,
        var12: Long,
        config: T
    ) {

    }

    override fun initSeed(seed: Long) {
        pitNoiseSampler = PitNoiseSampler(seed, 0.5, 32.0)
    }
}