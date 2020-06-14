package redstoneparadox.thedepths.client

import net.fabricmc.api.ClientModInitializer
import redstoneparadox.thedepths.TheDepths.DEPTHS_TYPE_KEY
import redstoneparadox.thedepths.mixin.client.render.AccessorSkyProperties

@Suppress("unused")
object TheDepthsClient: ClientModInitializer {
    override fun onInitializeClient() {
        AccessorSkyProperties.getByDimensionType()[DEPTHS_TYPE_KEY] = DepthsSkyProperties()
    }
}