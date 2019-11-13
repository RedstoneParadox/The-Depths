package redstoneparadox.thedepths.world.gen.decorator

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.DecoratorConfig
import net.minecraft.world.gen.decorator.NopeDecoratorConfig
import redstoneparadox.thedepths.last
import java.util.*
import java.util.function.Function
import java.util.stream.Stream

open class LowerSurfaceDecorator<DC: LowerSurfaceDecoratorConfig>(func: Function<Dynamic<*>, out DC>): Decorator<DC>(func) {

    override fun getPositions(world: IWorld, chunkGenerator: ChunkGenerator<out ChunkGeneratorConfig>, random: Random, config: DC, pos: BlockPos): Stream<BlockPos> {
        println("Starting with position $pos")
        val startPos = BlockPos.Mutable(pos.x + random.nextInt(15), pos.y, pos.z + random.nextInt(15))

        if (world.getBlockState(pos).isAir) {
            while (world.getBlockState(startPos).isAir) {
                startPos.y -= 1
            }
            startPos.y += 1
        }
        else {
            while (!world.getBlockState(startPos).isAir) {
                startPos.y += 1
            }
        }

        return if (last(config.randOffset) { random.nextDouble() } <= config.chance) {arrayListOf(startPos.toImmutable()).stream()} else {arrayListOf<BlockPos>().stream()}
    }
}