package redstoneparadox.thedepths.world.biome

import net.minecraft.entity.EntityCategory
import net.minecraft.entity.EntityType
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

class DepthsBiome : Biome(
    Biome.Settings()
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
        this.addSpawn(EntityCategory.MONSTER, Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
    }
}