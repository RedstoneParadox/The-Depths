package redstoneparadox.thedepths.world.biome

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.gen.feature.StructureFeature
import redstoneparadox.thedepths.world.biome.DepthsBiomes
import java.util.*

class DepthsBiomeSource(val seed: Long): BiomeSource(setOf(DepthsBiomes.DEPTHS_BIOME, DepthsBiomes.LUMA_BIOME)) {

    override fun getStoredBiome(x: Int, y: Int, z: Int): Biome {
        return getBiome(x * 4, y * 4, z * 4)
    }

    fun getBiome(x: Int, y: Int, z: Int): Biome {
        return if (y >= 228) {DepthsBiomes.LUMA_BIOME} else {DepthsBiomes.DEPTHS_BIOME}
    }

    override fun hasStructureFeature(feature: StructureFeature<*>): Boolean = false
}