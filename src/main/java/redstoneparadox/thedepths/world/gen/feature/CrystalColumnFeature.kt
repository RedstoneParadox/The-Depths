package redstoneparadox.thedepths.world.gen.feature

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.feature.Feature
import java.util.*
import java.util.function.Function
import kotlin.math.*

class CrystalColumnFeature(func: Function<Dynamic<*>, out CrystalColumnFeatureConfig>): Feature<CrystalColumnFeatureConfig>(func) {



    override fun generate(world: IWorld, chunkGenerator: ChunkGenerator<out ChunkGeneratorConfig>, random: Random, pos: BlockPos, config: CrystalColumnFeatureConfig): Boolean {
        val stone = config.stone
        val crystal = config.crystal
        val crystalChance = config.crystalChance
        val radius = config.radius
        val biome = config.biome

        run {
            val mutPos = BlockPos.Mutable(pos)

            while (world.getBiome(mutPos) == biome && mutPos.y <= 248) {
                for (r in 0..radius) {
                    for (i in 0 until 360 step 8) {
                        val theta = i * (PI/180)
                        val x = (r * cos(theta)).roundToInt()
                        val z = (r * sin(theta)).roundToInt()

                        mutPos.x += x
                        mutPos.z += z
                        world.setBlockState(mutPos, if (random.nextFloat() <= crystalChance) {crystal} else {stone}, 0)
                        mutPos.x -= x
                        mutPos.z -= z
                    }
                }
                mutPos.y += 1
            }
        }

        return true
    }
}