package io.github.redstoneparadox.thedepths.world.gen.surfacebuilder

import net.minecraft.util.registry.Registry
import io.github.redstoneparadox.thedepths.util.id

object DepthsSurfaceBuilders {

    val DEPTHS = DepthsSurfaceBuilder(DepthsSurfaceConfig.CODEC)

    fun init() {
        Registry.register(Registry.SURFACE_BUILDER,
            id("depths"), DEPTHS)
    }
}