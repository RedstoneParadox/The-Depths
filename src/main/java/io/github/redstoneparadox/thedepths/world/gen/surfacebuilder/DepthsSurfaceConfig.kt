package io.github.redstoneparadox.thedepths.world.gen.surfacebuilder

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig
import io.github.redstoneparadox.thedepths.block.DepthsBlocks
import java.util.*

open class DepthsSurfaceConfig(
    val primaryStone: BlockState = DepthsBlocks.DEEP_ROCK.defaultState,
    val secondaryStone: BlockState = Blocks.STONE.defaultState,
    val cover: Optional<BlockState> = Optional.empty(),
    val fluid: Optional<BlockState> = Optional.empty(),
    val floorMinHeight: Int = 16,
    val floorMaxHeight: Int = 18,
    val pitCutoff: Double = 0.5
): SurfaceConfig {
    override fun getTopMaterial(): BlockState = primaryStone

    override fun getUnderMaterial(): BlockState = primaryStone

    companion object {
        val CODEC: Codec<DepthsSurfaceConfig> = RecordCodecBuilder.create { instance ->
            return@create instance.group(
                BlockState.CODEC.fieldOf("primaryStone").forGetter { it.primaryStone },
                BlockState.CODEC.fieldOf("secondaryStone").forGetter { it.secondaryStone },
                BlockState.CODEC.optionalFieldOf("cover").forGetter { it.cover },
                BlockState.CODEC.optionalFieldOf("fluid").forGetter { it.fluid },
                Codec.INT.fieldOf("floorMinHeight").forGetter { it.floorMinHeight },
                Codec.INT.fieldOf("floorMaxHeight").forGetter { it.floorMaxHeight },
                Codec.DOUBLE.fieldOf("pitCutoff").forGetter { it.pitCutoff }
            ).apply(instance, ::DepthsSurfaceConfig)
        }
    }
}