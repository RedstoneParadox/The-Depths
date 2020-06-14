package redstoneparadox.thedepths.world.gen.decorator

import net.minecraft.util.registry.Registry
import redstoneparadox.thedepths.util.id

object DepthsDecorators {

    val LOWER_SURFACE = LowerSurfaceDecorator(LowerSurfaceDecoratorConfig.CODEC)
    val LOWER_SURFACE_SCATTER = LowerSurfaceScatterDecorator()

    fun init() {
        Registry.register(Registry.DECORATOR,
            id("lower_surface"), LOWER_SURFACE)
        Registry.register(Registry.DECORATOR,
            id("lower_surface_scatter"), LOWER_SURFACE_SCATTER)
    }
}