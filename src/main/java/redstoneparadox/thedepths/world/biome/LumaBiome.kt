package redstoneparadox.thedepths.world.biome

import net.minecraft.block.Blocks
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.CountDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.feature.Feature

import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

class LumaBiome: Biome(
    Settings()
        .configureSurfaceBuilder(SurfaceBuilder.NOPE, SurfaceBuilder.AIR_CONFIG)
        .precipitation(Precipitation.NONE)
        .category(Category.NONE)
        .depth(0.0f)
        .scale(0.8f)
        .temperature(0.9f)
        .downfall(0.0f)
        .waterColor(4159204)
        .waterFogColor(329011)
) {

    init {

    }
}