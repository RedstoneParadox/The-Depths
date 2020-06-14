package redstoneparadox.thedepths

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions
import net.minecraft.block.pattern.BlockPattern.TeleportTarget
import net.minecraft.entity.Entity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.World
import redstoneparadox.thedepths.block.DepthsBlocks
import redstoneparadox.thedepths.items.DepthsItems
import redstoneparadox.thedepths.world.DepthsChunkGenerator
import redstoneparadox.thedepths.world.biome.DepthsBiomeSource
import redstoneparadox.thedepths.world.biome.DepthsBiomes
import redstoneparadox.thedepths.world.gen.decorator.DepthsDecorators
import redstoneparadox.thedepths.world.gen.feature.DepthsFeatures
import redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceBuilders
import kotlin.math.absoluteValue


var dimensionKey: RegistryKey<World>? = null

@Suppress("unused")
fun init() {
    DepthsDecorators.init()
    DepthsFeatures.init()
    DepthsSurfaceBuilders.init()
    DepthsBiomes.init()
    DepthsBlocks.init()
    DepthsItems.init()

    Registry.register(Registry.CHUNK_GENERATOR, id("depths"), DepthsChunkGenerator.CODEC)
    Registry.register(Registry.BIOME_SOURCE, id("depths"), DepthsBiomeSource.CODEC)
    dimensionKey = RegistryKey.of(Registry.DIMENSION, id("depths"))
    FabricDimensions.registerDefaultPlacer(dimensionKey, ::placeEntity)

    CommandRegistrationCallback.EVENT.register(
        CommandRegistrationCallback { dispatcher: CommandDispatcher<ServerCommandSource?>, dedicated: Boolean ->
            dispatcher.register(
                literal<ServerCommandSource>("depths").executes(::executeTestCommand)
            )
        }
    )

    println("Hello Fabric world!")
}

fun id(string: String): Identifier = Identifier("thedepths", string)

fun getLightLevels(): FloatArray {
    val lightLevels = FloatArray(16)

    for (i in 0..15) {
        val lightLevel = 1.0f - i.toFloat() / 15.0f
        lightLevels[i] = (1.0f - lightLevel) / (lightLevel * 3.0f + 1.0f) * 1.0f + 0.0f
        //lightLevels[i] = 1.0f
    }

    return lightLevels
}

fun offsetSeed(seed: Long, offset: Int): Long {
    val absOffset = offset.absoluteValue.toLong()
    if (seed == Long.MAX_VALUE) return Long.MIN_VALUE
    return seed + absOffset
}

fun <T> last(count: Int, func: () -> T): T {
    if (count == 0) return func()

    var value: T? = null
    for (i in 0..count) {
        value = func()
    }
    return value!!
}

@Throws(CommandSyntaxException::class)
fun executeTestCommand(context: CommandContext<ServerCommandSource>): Int {
    try {
        val serverPlayerEntity = context.source.player
        val serverWorld = serverPlayerEntity.serverWorld
        if (serverWorld.registryKey != dimensionKey) {
            serverPlayerEntity.changeDimension(context.source.minecraftServer.getWorld(dimensionKey))
        } else {
            FabricDimensions.teleport(serverPlayerEntity, context.source.minecraftServer.getWorld(World.OVERWORLD), ::placeEntity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return 1
}

fun placeEntity(teleported: Entity, destination: ServerWorld, portalDir: Direction, horizontalOffset: Double, verticalOffset: Double): TeleportTarget? {
    return TeleportTarget(teleported.pos, Vec3d.ZERO, 0)
}


