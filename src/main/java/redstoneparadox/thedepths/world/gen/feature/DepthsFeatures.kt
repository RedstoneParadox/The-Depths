package redstoneparadox.thedepths.world.gen.feature

import net.minecraft.util.registry.Registry
import redstoneparadox.thedepths.util.id

object DepthsFeatures {

    val CRYSTAL_COLUMN = CrystalColumnFeature()
    val MOSS_PATCH = ScatterFeature()

    fun init() {
        Registry.register(Registry.FEATURE,
            id("crystal_column"), CRYSTAL_COLUMN)
        Registry.register(Registry.FEATURE,
            id("moss_patch"), MOSS_PATCH)
    }
}