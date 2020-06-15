package io.github.redstoneparadox.thedepths.world.gen.surfacebuilder

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.github.redstoneparadox.thedepths.util.Codecs

data class DepthsLayer(val range: IntRange, val holes: Boolean, val connected: Boolean, val filled: Boolean) {
    companion object {
        val CODEC: Codec<DepthsLayer> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codecs.INT_RANGE.fieldOf("range").forGetter { it.range },
                Codec.BOOL.fieldOf("holes").forGetter { it.holes },
                Codec.BOOL.fieldOf("connected").forGetter { it.connected },
                Codec.BOOL.fieldOf("filled").forGetter { it.filled }
            ).apply(instance, ::DepthsLayer)
        }
    }
}