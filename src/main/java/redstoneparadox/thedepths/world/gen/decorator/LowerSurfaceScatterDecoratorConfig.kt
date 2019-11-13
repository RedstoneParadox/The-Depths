package redstoneparadox.thedepths.world.gen.decorator

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps

class LowerSurfaceScatterDecoratorConfig(val count: Int): LowerSurfaceDecoratorConfig(1, 1.0) {

    override fun <T : Any> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(ops, ops.createMap(
            mapOf(
                ops.createString("count") to ops.createInt(count)
            )
        ))
    }

    companion object {
        fun deserialize(dyn: Dynamic<*>): LowerSurfaceScatterDecoratorConfig {
            return LowerSurfaceScatterDecoratorConfig(dyn["count"].asInt(1))
        }
    }
}