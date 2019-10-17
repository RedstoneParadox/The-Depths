package redstoneparadox.thedepths

import com.github.draylar.worldtraveler.api.dimension.DimensionBuilder
import com.github.draylar.worldtraveler.api.dimension.utils.FogColorCalculator
import com.github.draylar.worldtraveler.api.dimension.utils.SkyAngleCalculator
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.pattern.BlockPattern
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import redstoneparadox.thedepths.block.DepthsBlocks
import redstoneparadox.thedepths.world.DepthsBiomeSource
import redstoneparadox.thedepths.world.DepthsChunkGeneratorConfig
import redstoneparadox.thedepths.world.DepthsChunkGeneratorType
import redstoneparadox.thedepths.world.biome.DepthsBiomes


val DEPTHS: FabricDimensionType = FabricDimensionType.builder()
    .skyLight(false)
    .factory { world, type ->
        DimensionBuilder()
            .renderFog(true)
            .fogColor(FogColorCalculator.DEFAULT)
            .visibleSky(false)
            .skyAngle(SkyAngleCalculator.DEFAULT)
            .setChunkGenerator(
                DepthsChunkGeneratorType.INSTANCE.create(world,
                DepthsBiomeSource(world.seed), DepthsChunkGeneratorConfig()
            ))
            .setLightLevelsToBrightness(getLightLevels())
            .build(world, type)
    }
    .defaultPlacer { entity, _, _, _, _ ->
        BlockPattern.TeleportTarget(
            Vec3d(
                entity.x,
                entity.y,
                entity.z
            ), entity.velocity, 0
        )
    }
    .buildAndRegister(id("the_depths"))

@Suppress("unused")
fun init() {
    DepthsBiomes.init()
    DepthsBlocks.init()

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



