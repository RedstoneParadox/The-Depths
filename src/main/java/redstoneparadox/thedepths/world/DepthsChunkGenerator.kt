package redstoneparadox.thedepths.world

import net.minecraft.util.crash.CrashException
import net.minecraft.util.crash.CrashReport
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.ChunkRegion
import net.minecraft.world.Heightmap
import net.minecraft.world.IWorld
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.ChunkRandom
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.chunk.ChunkGenerator

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
                    biome.buildSurface(rand, chunk, x, y, z, 0.0, config.defaultBlock, config.defaultFluid, seaLevel, seed)
                }
            }
        }
    }

    override fun populateNoise(world: IWorld, chunk: Chunk) {

    }

    override fun getHeightOnGround(x: Int, z: Int, heightMapType: Heightmap.Type): Int = 64

    override fun getSpawnHeight(): Int = 64

    override fun generateFeatures(chunkRegion: ChunkRegion) {
        val chunkX = chunkRegion.centerChunkX
        val chunkZ = chunkRegion.centerChunkZ
        val startX = chunkX*16
        val startZ = chunkZ*16

        val pos = BlockPos.Mutable(startX, 0, startZ)
        var previousBiome: Biome? = null

        for (biomeY in 0..63) {
            pos.y += 4
            val biome = this.getDecorationBiome(chunkRegion.getBiomeAccess(), pos)
            if (biome == previousBiome) {
                continue
            }
            previousBiome = biome
            val rand = ChunkRandom()
            val long = rand.setSeed(chunkRegion.getSeed(), startX, startZ)
            val var11 = GenerationStep.Feature.values()
            val var12 = var11.size

            for (var13 in 0 until var12) {
                val feature = var11[var13]

                try {
                    biome.generateFeatureStep(feature, this, chunkRegion, long, rand, pos)
                } catch (var17: Exception) {
                    val crashReport_1 = CrashReport.create(var17, "Biome decoration")
                    crashReport_1.addElement("Generation").add("CenterX", chunkX).add("CenterZ", chunkZ)
                        .add("Step", feature as Any).add("Seed", long)
                        .add("Biome", Registry.BIOME.getId(biome) as Any?)
                    throw CrashException(crashReport_1)
                }

            }
        }
    }
}