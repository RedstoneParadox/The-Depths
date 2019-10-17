package redstoneparadox.thedepths.world.gen.surfacebuilder

import com.mojang.datafixers.Dynamic
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.surfacebuilder.ShatteredSavannaSurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import redstoneparadox.thedepths.block.DepthsBlocks
import redstoneparadox.thedepths.id
import java.util.function.Function

object DepthsSurfaceBuilders {

    val DEPTHS = DepthsSurfaceBuilder(Function { DepthsSurfaceConfig(Blocks.STONE.defaultState) })

    fun init() {
        Registry.register(Registry.SURFACE_BUILDER, id("depths"), DEPTHS)
    }
}