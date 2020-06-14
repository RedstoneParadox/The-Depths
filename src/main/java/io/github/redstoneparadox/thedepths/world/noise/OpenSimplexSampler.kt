package io.github.redstoneparadox.thedepths.world.noise

class OpenSimplexSampler(seed: Long = 0, private val xStrech: Double = 1.0, private val yStrech: Double = 1.0, private val zStrech: Double = 1.0, private val noiseMultiplier: Double = 1.0) {

    val noise = OpenSimplexNoise(seed)

    fun eval(x: Int, z: Int): Double = noise.eval(x.toDouble()/xStrech, z.toDouble()/zStrech) * noiseMultiplier

    fun eval(x: Int, y: Int, z: Int): Double = noise.eval(x.toDouble()/xStrech,y.toDouble()/yStrech, z.toDouble()/zStrech) * noiseMultiplier
}