package redstoneparadox.thedepths.world.gen.decorator

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.world.gen.decorator.DecoratorConfig
import net.minecraft.world.gen.decorator.NopeDecoratorConfig

class LowerSurfaceDecoratorConfig(val randOffset: Int, val chance: Double): DecoratorConfig {

    override fun <T : Any> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(ops, ops.createMap(
            mapOf(
                ops.createString("randOffset") to ops.createInt(randOffset),
                ops.createString("chance") to ops.createDouble(chance)
            )
        ))
    }

    companion object {

        fun deserialize(dyn: Dynamic<*>): LowerSurfaceDecoratorConfig {
            return LowerSurfaceDecoratorConfig(dyn["randOffset"].asInt(0), dyn["chance"].asDouble(0.01))
        }
    }
}