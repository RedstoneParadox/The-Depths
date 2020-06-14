package redstoneparadox.thedepths.world.biome

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.data.server.LootTablesProvider
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.gen.feature.StructureFeature
import redstoneparadox.thedepths.world.noise.OpenSimplexSampler
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.floor
import kotlin.math.roundToInt

class DepthsBiomeSource(val seed: Long): BiomeSource(listOf(DepthsBiomes.DEPTHS_BIOME)) {

    val biomeRangeMap: HashMap<Int, ArrayList<Biome>> = hashMapOf()
    val simplexSampler: OpenSimplexSampler = OpenSimplexSampler(
        seed = seed,
        xStrech = 512.0,
        zStrech =  512.0
    )

    init {
        addBiomeRange(0..255, DepthsBiomes.DEPTHS_BIOME)
    }

    private fun getBiome(x: Int, y: Int, z: Int): Biome {
        return if (biomeRangeMap.containsKey(y)) {
            val biomes = biomeRangeMap[y]!!
            biomes[getIndex(x, z, biomes.size)]
        } else { DepthsBiomes.DEPTHS_BIOME }
    }

    override fun hasStructureFeature(feature: StructureFeature<*>): Boolean = false

    override fun getBiomeForNoiseGen(x: Int, y: Int, z: Int): Biome {
        return if (biomeRangeMap.containsKey(y)) {
            val biomes = biomeRangeMap[y]!!
            biomes[getIndex(x, z, biomes.size)]
        } else { DepthsBiomes.DEPTHS_BIOME }
    }

    override fun method_28442(): Codec<out BiomeSource> {
        return CODEC
    }

    override fun withSeed(l: Long): BiomeSource {
        return DepthsBiomeSource(l)
    }

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

    private fun getIndex(x: Int, z: Int, maxIndex: Int): Int = floor((simplexSampler.eval(x, z).absoluteValue * maxIndex)).roundToInt()

    companion object {
        val CODEC: Codec<DepthsBiomeSource> = RecordCodecBuilder.create {
            it.group(
                Codec.LONG.fieldOf("seed").forGetter { source -> source.seed }
            ).apply(it, ::DepthsBiomeSource)
        }
    }
}