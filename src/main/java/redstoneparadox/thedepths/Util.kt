package redstoneparadox.thedepths

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions
import net.minecraft.block.pattern.BlockPattern
import net.minecraft.entity.Entity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import redstoneparadox.thedepths.TheDepths.DEPTHS_KEY
import kotlin.math.absoluteValue

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
        if (serverWorld.registryKey != DEPTHS_KEY) {
            serverPlayerEntity.changeDimension(context.source.minecraftServer.getWorld(DEPTHS_KEY))
        } else {
            FabricDimensions.teleport(serverPlayerEntity, context.source.minecraftServer.getWorld(World.OVERWORLD), ::placeEntity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return 1
}

fun placeEntity(teleported: Entity, destination: ServerWorld, portalDir: Direction, horizontalOffset: Double, verticalOffset: Double): BlockPattern.TeleportTarget? {
    return BlockPattern.TeleportTarget(teleported.pos, Vec3d.ZERO, 0)
}