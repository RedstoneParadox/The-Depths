package redstoneparadox.thedepths.world

import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.gen.chunk.ChunkGeneratorType
import redstoneparadox.thedepths.id
import java.util.function.Supplier

class DepthsChunkGeneratorType(buffetScreen: Boolean, configSupplier: Supplier<DepthsChunkGeneratorConfig>?) : ChunkGeneratorType<DepthsChunkGeneratorConfig, DepthsChunkGenerator>(null, buffetScreen, configSupplier) {

    companion object {
        val INSTANCE: DepthsChunkGeneratorType =
            Registry.register(
                Registry.CHUNK_GENERATOR_TYPE,
                id("thedepths"),
                DepthsChunkGeneratorType(
                    false,
                    Supplier { DepthsChunkGeneratorConfig() })
            )
    }

    override fun create(world: World, biomeSource: BiomeSource, config: DepthsChunkGeneratorConfig): DepthsChunkGenerator {
        return DepthsChunkGenerator(world, biomeSource, config)
    }
}