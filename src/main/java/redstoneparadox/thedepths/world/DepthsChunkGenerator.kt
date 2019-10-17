package redstoneparadox.thedepths.world

import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ChunkRegion
import net.minecraft.world.Heightmap
import net.minecraft.world.IWorld
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.ChunkRandom
import net.minecraft.world.gen.chunk.ChunkGenerator
import redstoneparadox.thedepths.block.DepthsBlocks
import redstoneparadox.thedepths.world.noise.OpenSimplexNoise
import redstoneparadox.thedepths.world.noise.PitNoiseSampler

class DepthsChunkGenerator(world: IWorld, biomeSource: BiomeSource, config: DepthsChunkGeneratorConfig): ChunkGenerator<DepthsChunkGeneratorConfig>(world, biomeSource, config) {

    override fun buildSurface(region: ChunkRegion, chunk: Chunk) {
        //buildBedrock(chunk)
        //buildStone(chunk)
        val chunkPos = chunk.pos
        val chunkX = chunkPos.x
        val chunkZ = chunkPos.z
        val rand = ChunkRandom()
        rand.setSeed(chunkX, chunkZ)
        val chunkStartX = chunkPos.startX
        val chunkStartZ = chunkPos.startZ
        val blockPos = BlockPos.Mutable()

        for (x in 0..15) {
            for (z in 0..15) {
                for (y in 0..255) {
                    val pos = blockPos.set(chunkStartX + x, y, chunkStartZ + z)
                    val biome = region.getBiome(pos)
                    biome.buildSurface(rand, chunk, pos.x, pos.y, pos.z, 0.0, config.defaultBlock, config.defaultFluid, seaLevel, seed)
                }
            }
        }
    }

    override fun populateNoise(world: IWorld, chunk: Chunk) {

    }

    override fun getHeightOnGround(x: Int, z: Int, heightMapType: Heightmap.Type): Int = 64

    override fun getSpawnHeight(): Int = 64
}