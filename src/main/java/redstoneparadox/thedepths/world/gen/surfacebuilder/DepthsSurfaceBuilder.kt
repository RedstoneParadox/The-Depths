package redstoneparadox.thedepths.world.gen.surfacebuilder

import com.mojang.serialization.Codec
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import redstoneparadox.thedepths.util.offsetSeed
import redstoneparadox.thedepths.world.noise.OpenSimplexSampler
import java.util.*
import kotlin.math.PI
import kotlin.math.cos

class DepthsSurfaceBuilder<T: DepthsSurfaceConfig>(codec: Codec<T>) : SurfaceBuilder<T>(codec) {

    private var pitSampler: OpenSimplexSampler = OpenSimplexSampler()
    private var stoneSampler: OpenSimplexSampler = OpenSimplexSampler()
    private var lowerSurfaceSampler: OpenSimplexSampler = OpenSimplexSampler()
    private val bedrock: BlockState = Blocks.BEDROCK.defaultState

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
        val primaryStone = config.primaryStone
        val secondaryStone = config.secondaryStone
        val liquid = config.fluid

        buildBedrock(chunk, x, y, z)
        if (chunk.getBlockState(BlockPos(x, y, z)).isAir) {
            if (y >= 64) buildPits(chunk, primaryStone, secondaryStone, config.cover, x, y, z, config.pitCutoff)
            else buildLowerSurface(chunk, primaryStone, secondaryStone, liquid, config.cover, x, y, z, config.floorMinHeight, config.floorMaxHeight)
        }
    }

    override fun initSeed(seed: Long) {
        pitSampler = OpenSimplexSampler(
            seed = offsetSeed(seed, 1),
            xStrech = 32.0,
            zStrech = 32.0
        )
        stoneSampler = OpenSimplexSampler(
            seed = seed,
            xStrech = 12.0,
            zStrech = 12.0
        )
        lowerSurfaceSampler = OpenSimplexSampler(
            seed = offsetSeed(seed, 2),
            xStrech = 32.0,
            zStrech = 32.0,
            noiseMultiplier = 4.0
        )
    }

    private fun buildBedrock(chunk: Chunk, x: Int, y: Int, z: Int) {
        if (y == 0 || y == 255) chunk.setBlockState(BlockPos(x, y, z), bedrock, true)
    }

    private fun buildPits(chunk: Chunk, primary: BlockState, secondary: BlockState, cover: Optional<BlockState>, x: Int, y: Int, z: Int, cutoff: Double) {
        val absolutePos = chunk.pos.toBlockPos(x, y, z)
        if (isStone(absolutePos.x, y, absolutePos.z, cutoff)) {
            val state = if (sampleDeepRockNoise(absolutePos.x, y, absolutePos.z)) {primary} else {secondary}
            chunk.setBlockState(BlockPos(x, y, z), state, true)
        }
        else if (y in 232..247 && isStone(absolutePos.x, y - 1, absolutePos.z, cutoff)) {
            cover.ifPresent {
                chunk.setBlockState(BlockPos(x, y, z), it, true)
            }
        }
    }

    private fun buildLowerSurface(chunk: Chunk, primaryStone: BlockState, secondaryStone: BlockState, liquid: Optional<BlockState>, cover: Optional<BlockState>, x: Int, y: Int, z: Int, minHeight: Int, maxHeight: Int) {
        val absolutePos = chunk.pos.toBlockPos(x, y, z)
        val state = if (sampleDeepRockNoise(absolutePos.x, y, absolutePos.z)) {primaryStone} else {secondaryStone}

        if (y < minHeight) {
            chunk.setBlockState(BlockPos(x, y, z), state, true)
        }
        else if (y in minHeight..maxHeight) {
            val height = lowerSurfaceSampler.eval(absolutePos.x, absolutePos.z)
            if (y <= height.toInt() + minHeight) chunk.setBlockState(BlockPos(x, y, z), state, true)
            else if (y <= height.toInt() + 1 + minHeight) cover.ifPresent {
                chunk.setBlockState(BlockPos(x, y, z), it, true)
            }
            else if (y < 16) liquid.ifPresent {
                chunk.setBlockState(BlockPos(x, y, z), it, true)
            }
        }
    }

    private fun sampleDeepRockNoise(x: Int, y: Int, z: Int): Boolean {
        return stoneSampler.eval(x, y, z) < 0.5
    }

    private fun isStone(x: Int, y: Int, z: Int, cutoff: Double): Boolean = when (y) {
        in 252..254 -> pitSampler.eval(x, z) < calculateCeilingSlope(calculatePitOffset(247, 16.0, cutoff), y - 248)
        251 -> pitSampler.eval(x, z) < cutoff * 1.25
        250 -> pitSampler.eval(x, z) < cutoff
        249 -> pitSampler.eval(x, z) < cutoff * 0.5
        248 -> pitSampler.eval(x, z) < cutoff * 0.25
        in 232..247 -> pitSampler.eval(x, z) < calculatePitOffset(y - 232, 16.0, cutoff)
        in 72..231 -> pitSampler.eval(x, z)  < cutoff
        in 64..71 -> pitSampler.eval(x, z)  < cutoff - calculatePitOffset(y - 64, 8.0, cutoff)
        else -> false
    }

    private fun calculatePitOffset(value: Int, divisor: Double, cutoff: Double): Double = (cos((PI * value)/divisor) + 1)/2 * (cutoff/2)

    private fun calculateCeilingSlope(initial: Double, value: Int): Double = -(value * initial)/4 + value
}