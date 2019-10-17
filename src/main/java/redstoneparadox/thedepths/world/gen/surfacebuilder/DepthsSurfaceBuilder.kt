package redstoneparadox.thedepths.world.gen.surfacebuilder

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import redstoneparadox.thedepths.block.DepthsBlocks
import redstoneparadox.thedepths.world.noise.OpenSimplexNoise
import redstoneparadox.thedepths.world.noise.PitNoiseSampler
import java.util.*
import java.util.function.Function

class DepthsSurfaceBuilder<T: DepthsSurfaceConfig>(function_1: Function<Dynamic<*>, out T>?) : SurfaceBuilder<T>(function_1) {

    var pitSampler: PitNoiseSampler? = null
    private var stoneSampler: OpenSimplexNoise? = null

    val BEDROCK = Blocks.BEDROCK.defaultState

    override fun generate(
        random: Random,
        chunk: Chunk,
        biome: Biome,
        x: Int,
        y: Int,
        z: Int,
        var7: Double,
        defaultBlock: BlockState?,
        defaultFluid: BlockState?,
        var11: Int,
        seed: Long,
        config: T
    ) {
        val primaryStone = config.underMaterial
        val secondaryStone = config.secondaryStone

        buildBedrock(chunk, x, y, z)
        buildStone(chunk, primaryStone, secondaryStone, x, y, z)
    }

    override fun initSeed(seed: Long) {
        if (pitSampler == null) pitSampler = PitNoiseSampler(seed, 0.5, 32.0)
        if (stoneSampler == null) stoneSampler = OpenSimplexNoise(seed)
    }

    private fun buildBedrock(chunk: Chunk, x: Int, y: Int, z: Int) {
        if (y == 0 || y == 255) chunk.setBlockState(BlockPos(x, y, z), BEDROCK, true)
    }

    private fun buildStone(chunk: Chunk, primary: BlockState, seconadry: BlockState, x: Int, y: Int, z: Int) {
        val absolutePos = chunk.pos.toBlockPos(x, y, z)
        if (pitSampler!!.isStone(absolutePos.x, y, absolutePos.z)) {
            val state = if (sampleDeepRockNoise(absolutePos.x, y, absolutePos.z)) {primary} else {seconadry}
            chunk.setBlockState(BlockPos(x, y, z), state, true)
        }
    }

    private fun sampleDeepRockNoise(x: Int, y: Int, z: Int): Boolean {
        val stretch = 12.0
        val cutoff = 0.5
        return stoneSampler?.eval(x.toDouble()/stretch, y.toDouble()/stretch, z.toDouble()/stretch)!! < cutoff
    }
}