package redstoneparadox.thedepths.world.gen.decorator

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.NopeDecoratorConfig
import java.util.*
import java.util.function.Function
import java.util.stream.Stream

class LowerSurfaceDecorator(func: Function<Dynamic<*>, out NopeDecoratorConfig>): Decorator<NopeDecoratorConfig>(func) {

    override fun getPositions(world: IWorld, chunkGenerator: ChunkGenerator<out ChunkGeneratorConfig>, random: Random, config: NopeDecoratorConfig, pos: BlockPos): Stream<BlockPos> {
        val startPos = BlockPos.Mutable(pos.x, 0, pos.z)

        while (!world.getBlockState(startPos).isAir) {
            startPos.y += 1
        }

        startPos.add(0, -1, 0)
        return if (random.nextDouble() <= 0.3) {arrayListOf(startPos.toImmutable()).stream()} else {arrayListOf<BlockPos>().stream()}
    }
}