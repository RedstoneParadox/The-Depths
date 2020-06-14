package redstoneparadox.thedepths

import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import redstoneparadox.thedepths.block.DepthsBlocks
import redstoneparadox.thedepths.items.DepthsItems
import redstoneparadox.thedepths.world.DepthsChunkGenerator
import redstoneparadox.thedepths.world.biome.DepthsBiomes
import redstoneparadox.thedepths.world.gen.decorator.DepthsDecorators
import redstoneparadox.thedepths.world.gen.feature.DepthsFeatures
import redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceBuilders
import kotlin.math.absoluteValue

@Suppress("unused")
fun init() {
    DepthsDecorators.init()
    DepthsFeatures.init()
    DepthsSurfaceBuilders.init()
    DepthsBiomes.init()
    DepthsBlocks.init()
    DepthsItems.init()

    Registry.register(Registry.CHUNK_GENERATOR, id("depths"), DepthsChunkGenerator.CODEC)

    println("Hello Fabric world!")
}

fun id(string: String): Identifier = Identifier("thedepths", string)

fun getLightLevels(): FloatArray {
    val lightLevels = FloatArray(16)

    for (i in 0..15) {
        val lightLevel = 1.0f - i.toFloat() / 15.0f
        lightLevels[i] = (1.0f - lightLevel) / (lightLevel * 3.0f + 1.0f) * 1.0f + 0.0f
        //lightLevels[i] = 1.0f
    }

    return lightLevels
}

fun offsetSeed(seed: Long, offset: Int): Long {
    val absOffset = offset.absoluteValue.toLong()
    if (seed == Long.MAX_VALUE) return Long.MIN_VALUE
    return seed + absOffset
}

fun <T> last(count: Int, func: () -> T): T {
    if (count == 0) return func()

    var value: T? = null
    for (i in 0..count) {
        value = func()
    }
    return value!!
}



