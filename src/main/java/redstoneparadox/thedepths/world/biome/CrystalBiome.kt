package redstoneparadox.thedepths.world.biome

import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.NopeDecoratorConfig
import redstoneparadox.thedepths.block.DepthsBlocks
import redstoneparadox.thedepths.world.gen.decorator.DepthsDecorators
import redstoneparadox.thedepths.world.gen.decorator.LowerSurfaceDecoratorConfig
import redstoneparadox.thedepths.world.gen.feature.CrystalColumnFeatureConfig
import redstoneparadox.thedepths.world.gen.feature.DepthsFeatures
import redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceBuilders
import redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceConfig

class CrystalBiome: Biome(
    Biome.Settings()
        .configureSurfaceBuilder(DepthsSurfaceBuilders.DEPTHS, DepthsSurfaceConfig())
        .precipitation(Precipitation.NONE)
        .category(Category.NONE)
        .depth(0.0f)
        .scale(0.8f)
        .temperature(0.9f)
        .downfall(0.0f)
        .waterColor(4159204)
        .waterFogColor(329011)
){

    init {
        this.addSpawn(EntityCategory.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))

        for ((i, crystal) in arrayOf(DepthsBlocks.RED_CRYSTAL.defaultState, DepthsBlocks.BLUE_CRYSTAL.defaultState, DepthsBlocks.WHITE_CRYSTAL.defaultState).withIndex()) {
            this.addFeature(
                GenerationStep.Feature.RAW_GENERATION, DepthsFeatures.CRYSTAL_COLUMN.method_23397(
                    CrystalColumnFeatureConfig(
                        crystal,
                        DepthsBlocks.DEEP_ROCK.defaultState,
                        2,
                        0.1,
                        1f,
                        this
                    )
                ).method_23388(
                    DepthsDecorators.LOWER_SURFACE.method_23475(LowerSurfaceDecoratorConfig(i, 0.1))
                ))

        }
    }
}