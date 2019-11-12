package redstoneparadox.thedepths.world.biome

import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.gen.feature.StructureFeature
import redstoneparadox.thedepths.world.noise.OpenSimplexSampler
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class DepthsBiomeSource(val seed: Long): BiomeSource(setOf(DepthsBiomes.DEPTHS_BIOME, DepthsBiomes.LUMA_BIOME)) {

    val biomeRangeMap: HashMap<Int, ArrayList<Biome>> = hashMapOf()
    val simplexSampler: OpenSimplexSampler = OpenSimplexSampler(
        seed = seed,
        xStrech = 512.0,
        zStrech =  512.0
    )

    init {
        addBiomeRange(0..227, DepthsBiomes.DEPTHS_BIOME)
        // addBiomeRange(0..63, DepthsBiomes.CRYSTAL_BIOME)
        addBiomeRange(228..255, DepthsBiomes.LUMA_BIOME)
    }

    override fun getStoredBiome(x: Int, y: Int, z: Int): Biome {
        return getBiome(x * 4, y * 4, z * 4)
    }

    private fun getBiome(x: Int, y: Int, z: Int): Biome {
        return if (biomeRangeMap.containsKey(y)) {
            val biomes = biomeRangeMap[y]!!
            biomes[getIndex(x, z, biomes.size - 1)]
        } else { DepthsBiomes.DEPTHS_BIOME }
    }

    override fun hasStructureFeature(feature: StructureFeature<*>): Boolean = false

    private fun addBiomeRange(range: IntRange, vararg biomes: Biome) {
        for (i in range) {
            if (i !in 0..255) throw IllegalArgumentException("Biomes must be between the top and bottom of the world!")

            if (!biomeRangeMap.containsKey(i)) biomeRangeMap[i] = arrayListOf()
            val list = biomeRangeMap[i]!!
            for (biome in biomes) {
                list.add(biome)
            }
        }
    }

    private fun getIndex(x: Int, z: Int, maxIndex: Int): Int = (simplexSampler.eval(x, z).absoluteValue * maxIndex).roundToInt()
}