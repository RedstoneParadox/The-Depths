package io.github.redstoneparadox.thedepths.world.gen.decorator

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.gen.decorator.DecoratorConfig

open class LowerSurfaceDecoratorConfig(val randOffset: Int, val chance: Double): DecoratorConfig {

    companion object {
        val CODEC: Codec<LowerSurfaceDecoratorConfig> = RecordCodecBuilder.create {
            it.group(
                Codec.INT.fieldOf("randOffset").forGetter { config -> config.randOffset },
                Codec.DOUBLE.fieldOf("chance").forGetter { config -> config.chance }
            ).apply(it, ::LowerSurfaceDecoratorConfig)
        }
    }
}