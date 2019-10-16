package redstoneparadox.thedepths.world.noise

import kotlin.math.PI
import kotlin.math.cos

class PitNoiseSampler(seed: Long, private val cutoff: Double, private val strechFactor: Double) {

    private val simplexNoise: OpenSimplexNoise = OpenSimplexNoise(seed)

    fun isStone(x: Int, y: Int, z: Int): Boolean = when (y) {
        in 240..254 -> eval(x, z) < calculateOffset(y - 240, 16.0)
        in 72..239 -> eval(x, z)  < cutoff
        in 64..71 -> eval(x, z)  < cutoff - calculateOffset(y - 64, 8.0)
        else -> false
    }

    private fun calculateOffset(value: Int, divisor: Double): Double = (cos((PI * value)/divisor) + 1)/2 * (cutoff/2)

    private fun eval(x: Int, z: Int) = simplexNoise.eval(x.toDouble()/strechFactor, z.toDouble()/strechFactor)
}