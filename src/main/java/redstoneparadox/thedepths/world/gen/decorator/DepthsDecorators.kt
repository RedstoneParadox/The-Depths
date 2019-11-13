package redstoneparadox.thedepths.world.gen.decorator

import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.decorator.NopeDecoratorConfig
import redstoneparadox.thedepths.id
import java.util.function.Function

object DepthsDecorators {

    val LOWER_SURFACE = LowerSurfaceDecorator(Function {LowerSurfaceDecoratorConfig.deserialize (it)} )
    val LOWER_SURFACE_SCATTER = LowerSurfaceScatterDecorator(Function { LowerSurfaceScatterDecoratorConfig.deserialize(it) })

    fun init() {
        Registry.register(Registry.DECORATOR, id("lower_surface"), LOWER_SURFACE)
        Registry.register(Registry.DECORATOR, id("lower_surface_scatter"), LOWER_SURFACE_SCATTER)
    }
}