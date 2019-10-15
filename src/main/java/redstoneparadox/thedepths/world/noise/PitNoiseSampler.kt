package redstoneparadox.thedepths.world.noise

class PitNoiseSampler(seed: Long, val cutoff: Double, val strechFactor: Double) {

    private val simplexNoise: OpenSimplexNoise = OpenSimplexNoise(seed)

    fun isPit(x: Int, z: Int): Boolean = simplexNoise.eval(x.toDouble()/strechFactor, z.toDouble()/strechFactor) > cutoff

}