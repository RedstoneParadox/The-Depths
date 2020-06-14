package redstoneparadox.thedepths.world.biome

import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.gen.GenerationStep
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
        .depth(0.0f).scale(0.8f)
        .temperature(0.9f).downfall(0.0f)
        .effects(BiomeEffects.Builder()
            .fogColor(12638463)
            .waterColor(4159204)
            .waterFogColor(329011)
            .build()
        )
        .parent("null")
){

    //TODO: Make this end well.
    init {
        this.addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))

        for ((i, crystal) in arrayOf(DepthsBlocks.RED_CRYSTAL.defaultState, DepthsBlocks.BLUE_CRYSTAL.defaultState, DepthsBlocks.WHITE_CRYSTAL.defaultState).withIndex()) {
            this.addFeature(
                GenerationStep.Feature.RAW_GENERATION, DepthsFeatures.CRYSTAL_COLUMN.configure(
                    CrystalColumnFeatureConfig(
                        crystal,
                        DepthsBlocks.DEEP_ROCK.defaultState,
                        2,
                        0.1,
                        1f,
                        Registry.BIOME.getId(this)!!
                    )
                ).createDecoratedFeature(
                    DepthsDecorators.LOWER_SURFACE.configure(LowerSurfaceDecoratorConfig(i, 0.1))
                ))

        }
    }
}