package redstoneparadox.thedepths.world

import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ChunkRegion
import net.minecraft.world.Heightmap
import net.minecraft.world.IWorld
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.chunk.ChunkGenerator
import redstoneparadox.thedepths.block.DepthsBlocks
import redstoneparadox.thedepths.world.noise.OpenSimplexNoise
import redstoneparadox.thedepths.world.noise.PitNoiseSampler

class DepthsChunkGenerator(world: IWorld, biomeSource: BiomeSource, config: DepthsChunkGeneratorConfig): ChunkGenerator<DepthsChunkGeneratorConfig>(world, biomeSource, config) {

    val BEDROCK = Blocks.BEDROCK.defaultState
    val STONE = DepthsBlocks.DEEP_ROCK.defaultState

    val pitNoiseSampler: PitNoiseSampler = PitNoiseSampler(world.seed, 0.5, 32.0)

    val deepRockNoise: OpenSimplexNoise = OpenSimplexNoise(world.seed)

    override fun buildSurface(region: ChunkRegion, chunk: Chunk) {
        buildBedrock(chunk)
        buildStone(chunk)
    }

    override fun populateNoise(world: IWorld, chunk: Chunk) {

    }

    override fun getHeightOnGround(x: Int, z: Int, heightMapType: Heightmap.Type): Int = 64

    override fun getSpawnHeight(): Int = 64

    private fun buildBedrock(chunk: Chunk) {
        for (x in 0..15) {
            for (z in 0..15) {
                chunk.setBlockState(BlockPos(x, 0, z), BEDROCK, true)
                chunk.setBlockState(BlockPos(x, 255, z), BEDROCK, true)
            }
        }
    }

    private fun buildStone(chunk: Chunk) {
        for (x in 0..15) {
            for (z in 0..15) {
                val absolutePos = chunk.pos.toBlockPos(x, 0, z)
                for (y in 0..255) {
                    if (pitNoiseSampler.isStone(absolutePos.x, y, absolutePos.z)) {
                        val state = if (sampleDeepRockNoise(absolutePos.x, y, absolutePos.z)) {DepthsBlocks.DEEP_ROCK} else {Blocks.STONE}.defaultState
                        chunk.setBlockState(BlockPos(x, y, z), state, true)
                    }
                }
            }
        }
    }

    private fun sampleDeepRockNoise(x: Int, y: Int, z: Int): Boolean {
        val stretch = 4.0
        val cutoff = 0.2
        return deepRockNoise.eval(x.toDouble()/stretch, y.toDouble()/stretch, z.toDouble()/stretch) < cutoff
    }
}