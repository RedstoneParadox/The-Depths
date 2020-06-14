package redstoneparadox.thedepths.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.feature.FeatureConfig

class CrystalColumnFeatureConfig(val crystal: BlockState, val stone: BlockState, val radius: Int, val genChance: Double, val crystalChance: Float, biomeID: Identifier): FeatureConfig {
    val biome: Biome by lazy { Registry.BIOME[biomeID]!! }

    companion object {
        val CODEC: Codec<CrystalColumnFeatureConfig> = RecordCodecBuilder.create { instance ->
            return@create instance.group(
                BlockState.CODEC.fieldOf("crystal").forGetter { config -> config.crystal },
                BlockState.CODEC.fieldOf("stone").forGetter { config -> config.stone },
                Codec.INT.fieldOf("radius").forGetter { config -> config.radius },
                Codec.DOUBLE.fieldOf("genChance").forGetter { config -> config.genChance },
                Codec.FLOAT.fieldOf("crystalChance").forGetter { config -> config.crystalChance },
                Identifier.CODEC.fieldOf("biomeID").forGetter { config -> Registry.BIOME.getId(config.biome) }
            ).apply(instance, ::CrystalColumnFeatureConfig)
        }
    }
}