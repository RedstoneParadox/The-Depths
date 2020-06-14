package io.github.redstoneparadox.thedepths.client

import net.fabricmc.api.ClientModInitializer
import io.github.redstoneparadox.thedepths.TheDepths.DEPTHS_TYPE_KEY
import io.github.redstoneparadox.thedepths.mixin.client.render.AccessorSkyProperties

@Suppress("unused")
object TheDepthsClient: ClientModInitializer {
    override fun onInitializeClient() {
        AccessorSkyProperties.getByDimensionType()[DEPTHS_TYPE_KEY] = DepthsSkyProperties()
    }
}