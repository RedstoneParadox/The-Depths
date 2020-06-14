package io.github.redstoneparadox.thedepths.world.biome

import net.minecraft.util.registry.Registry
import io.github.redstoneparadox.thedepths.util.id

object DepthsBiomes {

    val DEPTHS_BIOME = DepthsBiome()

    fun init() {
        Registry.register(Registry.BIOME, id("depths"), DEPTHS_BIOME)
    }
}