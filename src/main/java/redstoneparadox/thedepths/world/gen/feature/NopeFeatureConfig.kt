package redstoneparadox.thedepths.world.gen.feature

import com.mojang.datafixers.util.Unit.*
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.gen.feature.FeatureConfig

class NopeFeatureConfig(unit: com.mojang.datafixers.util.Unit): FeatureConfig {
    companion object {
        val CODEC: Codec<out NopeFeatureConfig> = RecordCodecBuilder.create { instance ->
            return@create instance.group(Codec.EMPTY.codec().fieldOf("").forGetter { INSTANCE }).apply(instance, ::NopeFeatureConfig)
        }
    }
}