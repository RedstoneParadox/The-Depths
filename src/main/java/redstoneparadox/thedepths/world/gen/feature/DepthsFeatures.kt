package redstoneparadox.thedepths.world.gen.feature

import net.minecraft.util.registry.Registry
import redstoneparadox.thedepths.id
import java.util.function.Function

object DepthsFeatures {

    val CRYSTAL_COLUMN = CrystalColumnFeature(Function { CrystalColumnFeatureConfig.deserialize(it) })

    fun init() {
        Registry.register(Registry.FEATURE, id("crystal_column"), CRYSTAL_COLUMN)
    }
}