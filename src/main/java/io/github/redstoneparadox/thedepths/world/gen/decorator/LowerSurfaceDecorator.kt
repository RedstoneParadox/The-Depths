package io.github.redstoneparadox.thedepths.world.gen.decorator

import com.mojang.serialization.Codec
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.decorator.Decorator
import io.github.redstoneparadox.thedepths.util.last
import java.util.*
import java.util.stream.Stream

open class LowerSurfaceDecorator<DC: LowerSurfaceDecoratorConfig>(codec: Codec<DC>): Decorator<DC>(codec) {

    override fun getPositions(world: WorldAccess, chunkGenerator: ChunkGenerator, random: Random, config: DC, pos: BlockPos): Stream<BlockPos> {
        val startPos = BlockPos.Mutable(pos.x + random.nextInt(15), pos.y, pos.z + random.nextInt(15))
        val biome = world.getBiome(startPos)

        if (world.getBlockState(pos).isAir) {
            while (world.getBlockState(startPos).isAir && world.getBiome(startPos) == biome) {
                startPos.y -= 1
            }
            startPos.y += 1
        }
        else {
            while (!world.getBlockState(startPos).isAir && world.getBiome(startPos) == biome) {
                startPos.y += 1
            }
        }

        return if (last(config.randOffset) { random.nextDouble() } <= config.chance) {arrayListOf(startPos.toImmutable()).stream()} else {arrayListOf<BlockPos>().stream()}
    }
}