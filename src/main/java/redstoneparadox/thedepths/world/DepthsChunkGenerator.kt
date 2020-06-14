package redstoneparadox.thedepths.world

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Blocks
import net.minecraft.util.crash.CrashException
import net.minecraft.util.crash.CrashReport
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.*
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.ChunkRandom
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.StructuresConfig
import net.minecraft.world.gen.chunk.VerticalBlockSample

class DepthsChunkGenerator(biomeSource: BiomeSource): ChunkGenerator(biomeSource, StructuresConfig(false)) {

    override fun buildSurface(region: ChunkRegion, chunk: Chunk) {
        val chunkPos = chunk.pos
        val chunkX = chunkPos.x
        val chunkZ = chunkPos.z
        val rand = ChunkRandom()
        val seed = rand.setTerrainSeed(chunkX, chunkZ)
        val chunkStartX = chunkPos.startX
        val chunkStartZ = chunkPos.startZ
        val blockPos = BlockPos.Mutable()

        for (x in 0..15) {
            for (z in 0..15) {
                for (y in 0..255) {
                    val pos = blockPos.set(chunkStartX + x, y, chunkStartZ + z)
                    val biome = region.getBiome(pos)
                    biome.buildSurface(rand, chunk, x, y, z, 0.0, Blocks.STONE.defaultState, Blocks.WATER.defaultState, seaLevel, seed)
                }
            }
        }
    }


    override fun getColumnSample(i: Int, j: Int): BlockView {
        return VerticalBlockSample(arrayOf())
    }

    override fun getHeightOnGround(x: Int, z: Int, heightMapType: Heightmap.Type): Int = 64

    override fun method_28506(): Codec<out ChunkGenerator> {
        return CODEC
    }

    //TODO: Figure out the proper return value.
    override fun getHeight(i: Int, j: Int, type: Heightmap.Type?): Int {
        return 255
    }

    override fun getSpawnHeight(): Int = 64

    override fun populateNoise(worldAccess: WorldAccess?, structureAccessor: StructureAccessor?, chunk: Chunk?) {

    }

    override fun withSeed(l: Long): ChunkGenerator {
        return DepthsChunkGenerator(biomeSource.withSeed(l))
    }

    override fun generateFeatures(chunkRegion: ChunkRegion, structureAccessor: StructureAccessor) {
        val chunkX = chunkRegion.centerChunkX
        val chunkZ = chunkRegion.centerChunkZ
        val startX = chunkX*16
        val startZ = chunkZ*16
        val pos = BlockPos.Mutable(startX, 0, startZ)
        var previousBiome: Biome? = null

        for (biomeY in 0..63) {
            pos.y += 4
            // val biome = getDecorationBiome(chunkRegion.getBiomeAccess(), pos)
            val biome = biomeSource.getBiomeForNoiseGen(pos.x, pos.y, pos.z)
            if (biome == previousBiome) {
                continue
            }
            previousBiome = biome
            val rand = ChunkRandom()
            val long = rand.setPopulationSeed(chunkRegion.seed, startX, startZ)
            val var11 = GenerationStep.Feature.values()
            val var12 = var11.size

            for (var13 in 0 until var12) {
                val feature = var11[var13]

                try {
                    biome.generateFeatureStep(feature, structureAccessor, this, chunkRegion, long, rand, pos)
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

    companion object {
        val CODEC: Codec<DepthsChunkGenerator> = RecordCodecBuilder.create { instance ->
            instance.group(
                BiomeSource.field_24713.fieldOf("biome_source").forGetter { generator -> return@forGetter generator.biomeSource }
            ).apply(instance, ::DepthsChunkGenerator)
        }
    }
}