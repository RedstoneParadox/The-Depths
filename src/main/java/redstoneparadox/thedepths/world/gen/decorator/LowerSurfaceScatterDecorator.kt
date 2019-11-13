package redstoneparadox.thedepths.world.gen.decorator

import com.mojang.datafixers.Dynamic
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import java.util.*
import java.util.function.Function
import java.util.stream.Stream

class LowerSurfaceScatterDecorator(func: Function<Dynamic<*>, out LowerSurfaceScatterDecoratorConfig>): LowerSurfaceDecorator<LowerSurfaceScatterDecoratorConfig>(func) {

    override fun getPositions(world: IWorld, chunkGenerator: ChunkGenerator<out ChunkGeneratorConfig>, random: Random, config: LowerSurfaceScatterDecoratorConfig, pos: BlockPos): Stream<BlockPos> {
        println("Decorating at $pos")
        var finalStream = arrayListOf<BlockPos>().stream()

        for (i in 0..config.count) {
            finalStream = Stream.of(finalStream, super.getPositions(world, chunkGenerator, random, config, pos)).flatMap { foo -> foo }
        }

        println(finalStream)
        return finalStream
    }


}