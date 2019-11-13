package redstoneparadox.thedepths.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.world.gen.feature.FeatureConfig

class NopeFeatureConfig: FeatureConfig {

    override fun <T : Any> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(ops)
    }

}