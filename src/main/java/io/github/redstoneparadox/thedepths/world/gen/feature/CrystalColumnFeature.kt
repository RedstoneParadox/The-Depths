package io.github.redstoneparadox.thedepths.world.gen.feature

import net.minecraft.util.math.BlockPos
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.Feature
import java.util.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

class CrystalColumnFeature: Feature<CrystalColumnFeatureConfig>(CrystalColumnFeatureConfig.CODEC) {

    override fun generate(world: ServerWorldAccess, structureAccessor: StructureAccessor, chunkGenerator: ChunkGenerator, random: Random, blockPos: BlockPos, config: CrystalColumnFeatureConfig): Boolean {
        val stone = config.stone
        val crystal = config.crystal
        val crystalChance = config.crystalChance
        val radius = config.radius
        val biome = config.biome

        run {
            var pos = blockPos

            while (world.getBiome(pos) == biome && pos.y <= 248) {
                for (r in 0..radius) {
                    for (i in 0 until 360 step 8) {
                        val theta = i * (PI/180)
                        val x = (r * cos(theta)).roundToInt()
                        val z = (r * sin(theta)).roundToInt()

                        world.setBlockState(BlockPos(x, pos.y, z), if (random.nextFloat() <= crystalChance) { crystal } else { stone }, 0)
                    }
                }
                pos = pos.up()
            }
        }

        return true
    }
}