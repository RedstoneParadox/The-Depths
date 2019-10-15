package redstoneparadox.thedepths.world

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.gen.feature.StructureFeature
import redstoneparadox.thedepths.world.biome.DepthsBiomes
import java.util.*

class DepthsBiomeSource(val seed: Long): BiomeSource(setOf(DepthsBiomes.DEPTHS_BIOME)) {

    val allowedBiomes: Array<Biome> = arrayOf(
        DepthsBiomes.DEPTHS_BIOME
    );

    override fun getBiome(x: Int, y: Int, z: Int): Biome =
        DepthsBiomes.DEPTHS_BIOME

    override fun locateBiome(x: Int, y: Int, z: Int, range: Int, biomes: MutableList<Biome>, random: Random): BlockPos =
        BlockPos(x, y, z)

    override fun hasStructureFeature(feature: StructureFeature<*>): Boolean = false

    override fun getTopMaterials(): MutableSet<BlockState> = mutableSetOf(Blocks.AIR.defaultState)
}