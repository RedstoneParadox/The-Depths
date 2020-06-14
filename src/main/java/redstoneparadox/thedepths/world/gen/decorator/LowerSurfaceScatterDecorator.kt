package redstoneparadox.thedepths.world.gen.decorator

import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.chunk.ChunkGenerator
import java.util.*
import java.util.stream.Stream

class LowerSurfaceScatterDecorator: LowerSurfaceDecorator<LowerSurfaceScatterDecoratorConfig>(LowerSurfaceScatterDecoratorConfig.CODEC) {

    override fun getPositions(world: WorldAccess, chunkGenerator: ChunkGenerator, random: Random, config: LowerSurfaceScatterDecoratorConfig, pos: BlockPos): Stream<BlockPos> {
        var finalStream = arrayListOf<BlockPos>().stream()

        for (i in 0..config.count) {
            finalStream = Stream.of(finalStream, super.getPositions(world, chunkGenerator, random, config, pos)).flatMap { foo -> foo }
        }

        return finalStream
    }


}