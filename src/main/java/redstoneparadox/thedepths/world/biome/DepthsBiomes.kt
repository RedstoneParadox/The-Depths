package redstoneparadox.thedepths.world.biome

import com.terraformersmc.terraform.biome.builder.TerraformBiome
import net.minecraft.util.registry.Registry
import redstoneparadox.thedepths.id

object DepthsBiomes {

    val DEPTHS_BIOME = DepthsBiome()

    fun init() {
        Registry.register(Registry.BIOME, id("depths"), DEPTHS_BIOME)
    }
}