package redstoneparadox.thedepths.world.biome

import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceBuilders
import redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceConfig

class DepthsBiome : Biome(
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
                .fogColor(0x33334d)
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