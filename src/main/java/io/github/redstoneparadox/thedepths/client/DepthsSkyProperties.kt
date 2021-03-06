package io.github.redstoneparadox.thedepths.client

import net.minecraft.client.render.SkyProperties
import net.minecraft.util.math.Vec3d

class DepthsSkyProperties: SkyProperties(Float.NaN, false, SkyType.NONE, false, true) {
    override fun useThickFog(i: Int, j: Int): Boolean {
        return true
    }

    override fun adjustSkyColor(vec3d: Vec3d, f: Float): Vec3d {
        return vec3d
    }
}