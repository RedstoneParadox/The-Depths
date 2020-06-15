package io.github.redstoneparadox.thedepths.world.biome

import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import io.github.redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceBuilders
import io.github.redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceConfig

class DepthsBiome : Biome(
    Settings()
        .configureSurfaceBuilder(DepthsSurfaceBuilders.DEPTHS, DepthsSurfaceConfig())
        .precipitation(Precipitation.NONE)
        .category(Category.NONE)
        .depth(0.0f)
        .scale(0.8f)
        .temperature(0.9f)
        .downfall(0.0f)
        .effects(
            BiomeEffects.Builder()
                .fogColor(0x2b2b3b)
                .waterColor(4159204)
                .waterFogColor(329011)
                .build()
        )
        .parent("null")
) {

    init {
        this.addSpawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
    }
}