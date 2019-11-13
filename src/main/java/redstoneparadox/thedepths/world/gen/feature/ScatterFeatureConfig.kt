package redstoneparadox.thedepths.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.feature.FeatureConfig

class ScatterFeatureConfig(val state: BlockState): FeatureConfig {

    override fun <T : Any> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(ops, ops.createMap(
            mutableMapOf(
                ops.createString("state") to BlockState.serialize(ops, state).value
            )
        ))
    }

    companion object {
        fun deserialize(dynamic: Dynamic<*>): ScatterFeatureConfig {
            return ScatterFeatureConfig(dynamic["state"].map { BlockState.deserialize(it) }.orElse(Blocks.AIR.defaultState) as BlockState)
        }
    }
}