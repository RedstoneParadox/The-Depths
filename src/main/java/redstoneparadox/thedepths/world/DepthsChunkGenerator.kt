package redstoneparadox.thedepths.world

import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ChunkRegion
import net.minecraft.world.Heightmap
import net.minecraft.world.IWorld
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.chunk.ChunkGenerator
import redstoneparadox.thedepths.world.noise.PitNoiseSampler

class DepthsChunkGenerator(world: IWorld, biomeSource: BiomeSource, config: DepthsChunkGeneratorConfig): ChunkGenerator<DepthsChunkGeneratorConfig>(world, biomeSource, config) {

    val BEDROCK = Blocks.BEDROCK.defaultState
    val STONE = Blocks.STONE.defaultState

    val pitNoiseSampler: PitNoiseSampler = PitNoiseSampler(world.seed, 0.3, 32.0)

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
                //chunk.setBlockState(BlockPos(x, 255, z), BEDROCK, true)
            }
        }
    }

    private fun buildStone(chunk: Chunk) {
        for (x in 0..15) {
            for (z in 0..15) {
                val absolutePos = chunk.pos.toBlockPos(x, 0, z)
                if (!pitNoiseSampler.isPit(absolutePos.x, absolutePos.z)) {
                    for (y in 64..254) {
                        chunk.setBlockState(BlockPos(x, y, z), STONE, true)
                    }
                }
            }
        }
    }
}