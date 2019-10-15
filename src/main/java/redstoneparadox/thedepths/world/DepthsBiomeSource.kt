package redstoneparadox.thedepths.world

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.gen.feature.StructureFeature
import java.util.*

class DepthsBiomeSource(val seed: Long): BiomeSource(setOf(Biomes.THE_VOID)) {

    val allowedBiomes: Array<Biome> = arrayOf(
        Biomes.THE_VOID
    );

    override fun getBiome(x: Int, y: Int, z: Int): Biome =
        Biomes.THE_VOID

    override fun locateBiome(x: Int, y: Int, z: Int, range: Int, biomes: MutableList<Biome>, random: Random): BlockPos =
        BlockPos.ORIGIN

    override fun hasStructureFeature(feature: StructureFeature<*>): Boolean = false

    override fun getTopMaterials(): MutableSet<BlockState> = mutableSetOf(Blocks.AIR.defaultState)
}