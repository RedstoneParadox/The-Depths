package redstoneparadox.thedepths.world.biome

import net.minecraft.block.Blocks
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.GenerationStep
import redstoneparadox.thedepths.block.DepthsBlocks
import redstoneparadox.thedepths.world.gen.decorator.DepthsDecorators
import redstoneparadox.thedepths.world.gen.decorator.LowerSurfaceScatterDecoratorConfig
import redstoneparadox.thedepths.world.gen.feature.DepthsFeatures
import redstoneparadox.thedepths.world.gen.feature.ScatterFeatureConfig
import redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceBuilders
import redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceConfig

class FungalBlanketBiome: Biome(
    Biome.Settings()
        .configureSurfaceBuilder(DepthsSurfaceBuilders.DEPTHS, DepthsSurfaceConfig())
        .precipitation(Precipitation.NONE)
        .category(Category.NONE)
        .depth(0.0f)
        .scale(0.8f)
        .temperature(0.9f)
        .downfall(0.0f)
        .effects(
            BiomeEffects.Builder()
                .fogColor(12638463)
                .waterColor(4159204)
                .waterFogColor(329011)
                .build()
        )
        .parent("null")
) {

    init {
        this.addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            DepthsFeatures.MOSS_PATCH.configure(
                ScatterFeatureConfig(DepthsBlocks.MOSS.defaultState)
            ).createDecoratedFeature(
                DepthsDecorators.LOWER_SURFACE_SCATTER.configure(
                    LowerSurfaceScatterDecoratorConfig(256)
                )
            )
        )

        this.addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            DepthsFeatures.MOSS_PATCH.configure(
                ScatterFeatureConfig(Blocks.BROWN_MUSHROOM.defaultState)
            ).createDecoratedFeature(
                DepthsDecorators.LOWER_SURFACE_SCATTER.configure(
                    LowerSurfaceScatterDecoratorConfig(16)
                )
            )
        )

        this.addFeature(
            GenerationStep.Feature.RAW_GENERATION,
            DepthsFeatures.MOSS_PATCH.configure(
                ScatterFeatureConfig(Blocks.RED_MUSHROOM.defaultState)
            ).createDecoratedFeature(
                DepthsDecorators.LOWER_SURFACE_SCATTER.configure(
                    LowerSurfaceScatterDecoratorConfig(16)
                )
            )
        )
    }
}