package redstoneparadox.thedepths.world.biome

import net.minecraft.util.registry.Registry
import redstoneparadox.thedepths.id

object DepthsBiomes {

    val DEPTHS_BIOME = DepthsBiome()
    val CRYSTAL_BIOME = CrystalBiome()
    val LUMA_BIOME = LumaBiome()
    val FUNGAL_BLANKET_BIOME = FungalBlanketBiome()

    fun init() {
        Registry.register(Registry.BIOME, id("depths"), DEPTHS_BIOME)
        Registry.register(Registry.BIOME, id("crystal"), CRYSTAL_BIOME)
        Registry.register(Registry.BIOME, id("luma"), LUMA_BIOME)
        Registry.register(Registry.BIOME, id("fungal_blanket"), FUNGAL_BLANKET_BIOME)
    }
}