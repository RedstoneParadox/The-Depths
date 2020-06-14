package io.github.redstoneparadox.thedepths.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.world.gen.feature.FeatureConfig

class ScatterFeatureConfig(val state: BlockState): FeatureConfig {
    companion object {
        val CODEC: Codec<ScatterFeatureConfig> = RecordCodecBuilder.create { instance ->
            return@create instance.group(BlockState.CODEC.fieldOf("state").forGetter { config -> return@forGetter config.state }).apply(instance, ::ScatterFeatureConfig)
        }
    }
}