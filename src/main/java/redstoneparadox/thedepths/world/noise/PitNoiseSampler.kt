package redstoneparadox.thedepths.world.noise

import kotlin.math.PI
import kotlin.math.cos

@Deprecated("This logic should be moved to the surface builder")
class PitNoiseSampler(seed: Long, private val cutoff: Double, private val strechFactor: Double) {

    private val simplexNoise: OpenSimplexNoise = OpenSimplexNoise(seed)

    fun isStone(x: Int, y: Int, z: Int): Boolean = when (y) {
        in 252..254 -> eval(x, z) < calculateTopSlope(calculateOffset(247, 16.0), y - 248)
        251 -> eval(x, z) < cutoff * 1.25
        250 -> eval(x, z) < cutoff
        249 -> eval(x, z) < cutoff * 0.5
        248 -> eval(x, z) < cutoff * 0.25
        in 232..247 -> eval(x, z) < calculateOffset(y - 232, 16.0)
        in 72..231 -> eval(x, z)  < cutoff
        in 64..71 -> eval(x, z)  < cutoff - calculateOffset(y - 64, 8.0)
        else -> false
    }

    private fun calculateOffset(value: Int, divisor: Double): Double = (cos((PI * value)/divisor) + 1)/2 * (cutoff/2)

    private fun calculateTopSlope(initial: Double, value: Int): Double = -(value * initial)/4 + value

    private fun eval(x: Int, z: Int) = simplexNoise.eval(x.toDouble()/strechFactor, z.toDouble()/strechFactor)
}