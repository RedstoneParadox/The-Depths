package redstoneparadox.thedepths.world.gen.surfacebuilder

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import redstoneparadox.thedepths.offsetSeed
import redstoneparadox.thedepths.world.noise.OpenSimplexNoise
import redstoneparadox.thedepths.world.noise.OpenSimplexSampler
import redstoneparadox.thedepths.world.noise.PitNoiseSampler
import java.util.*
import java.util.function.Function

class DepthsSurfaceBuilder<T: DepthsSurfaceConfig>(function_1: Function<Dynamic<*>, out T>?) : SurfaceBuilder<T>(function_1) {

    var pitSampler: PitNoiseSampler? = null
    private var stoneSampler: OpenSimplexNoise? = null
    private var lowerSurfaceNoise: OpenSimplexSampler? = null

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
        val primaryStone = config.primaryStone
        val secondaryStone = config.secondaryStone
        val liquid = config.fluid

        buildBedrock(chunk, x, y, z)
        if (chunk.getBlockState(BlockPos(x, y, z)).isAir) {
            if (y >= 64) buildPits(chunk, primaryStone, secondaryStone, x, y, z)
            else buildLowerSurface(chunk, primaryStone, secondaryStone, liquid, x, y, z, config.floorMinHeight, config.floorMaxHeight)
        }
    }

    override fun initSeed(seed: Long) {
        if (pitSampler == null) pitSampler = PitNoiseSampler(seed, 0.5, 32.0)
        if (stoneSampler == null) stoneSampler = OpenSimplexNoise(seed)
        if (lowerSurfaceNoise == null) lowerSurfaceNoise = OpenSimplexSampler(
            seed = offsetSeed(seed, 1),
            xStrech = 32.0,
            zStrech = 32.0,
            noiseMultiplier = 3.0
        )
    }

    private fun buildBedrock(chunk: Chunk, x: Int, y: Int, z: Int) {
        if (y == 0 || y == 255) chunk.setBlockState(BlockPos(x, y, z), BEDROCK, true)
    }

    private fun buildPits(chunk: Chunk, primary: BlockState, secondary: BlockState, x: Int, y: Int, z: Int) {
        val absolutePos = chunk.pos.toBlockPos(x, y, z)
        if (pitSampler!!.isStone(absolutePos.x, y, absolutePos.z)) {
            val state = if (sampleDeepRockNoise(absolutePos.x, y, absolutePos.z)) {primary} else {secondary}
            chunk.setBlockState(BlockPos(x, y, z), state, true)
        }
    }

    private fun buildLowerSurface(chunk: Chunk, primaryStone: BlockState, secondaryStone: BlockState, liquid: BlockState?, x: Int, y: Int, z: Int, minHeight: Int, maxHeight: Int) {
        val absolutePos = chunk.pos.toBlockPos(x, y, z)
        val state = if (sampleDeepRockNoise(absolutePos.x, y, absolutePos.z)) {primaryStone} else {secondaryStone}

        if (y < minHeight) {
            chunk.setBlockState(BlockPos(x, y, z), state, true)
        }
        else if (y in minHeight..maxHeight) {
            val height = lowerSurfaceNoise!!.eval(absolutePos.x, absolutePos.z)
            if (y <= height.toInt() + minHeight) chunk.setBlockState(BlockPos(x, y, z), state, true)
            else if (y < 16 && liquid != null) chunk.setBlockState(BlockPos(x, y, z), liquid, true)
        }
    }

    private fun sampleDeepRockNoise(x: Int, y: Int, z: Int): Boolean {
        val stretch = 12.0
        val cutoff = 0.5
        return stoneSampler?.eval(x.toDouble()/stretch, y.toDouble()/stretch, z.toDouble()/stretch)!! < cutoff
    }
}