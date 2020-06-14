package io.github.redstoneparadox.thedepths.world.gen.decorator

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

class LowerSurfaceScatterDecoratorConfig(val count: Int): LowerSurfaceDecoratorConfig(1, 1.0) {

    companion object {
        val CODEC: Codec<LowerSurfaceScatterDecoratorConfig> = RecordCodecBuilder.create {
            it.group(
                Codec.INT.fieldOf("count").forGetter { config -> config.count }
            ).apply(it, ::LowerSurfaceScatterDecoratorConfig)
        }
    }
}