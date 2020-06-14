package redstoneparadox.thedepths.util

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

object Codecs {
    val INT_RANGE: Codec<IntRange> = createRangeCodec(Codec.INT, ::IntRange)

    private inline fun <reified T, reified U: ClosedRange<T>> createRangeCodec(valueCodec: Codec<T>, noinline factory: (T, T) -> U): Codec<U> {
        return RecordCodecBuilder.create { instance ->
            instance.group(
                valueCodec.fieldOf("start").forGetter { it.start },
                valueCodec.fieldOf("endInclusive").forGetter { it.endInclusive }
            ).apply(instance, factory)
        }
    }
}