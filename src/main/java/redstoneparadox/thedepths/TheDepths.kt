package redstoneparadox.thedepths

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.World
import net.minecraft.world.dimension.DimensionType
import redstoneparadox.thedepths.block.DepthsBlocks
import redstoneparadox.thedepths.items.DepthsItems
import redstoneparadox.thedepths.util.executeTestCommand
import redstoneparadox.thedepths.util.id
import redstoneparadox.thedepths.util.placeEntity
import redstoneparadox.thedepths.world.DepthsChunkGenerator
import redstoneparadox.thedepths.world.biome.DepthsBiomeSource
import redstoneparadox.thedepths.world.biome.DepthsBiomes
import redstoneparadox.thedepths.world.gen.decorator.DepthsDecorators
import redstoneparadox.thedepths.world.gen.feature.DepthsFeatures
import redstoneparadox.thedepths.world.gen.surfacebuilder.DepthsSurfaceBuilders

object TheDepths: ModInitializer {
    val DEPTHS_KEY: RegistryKey<World> = RegistryKey.of(Registry.DIMENSION,
        id("depths")
    )
    val DEPTHS_TYPE_KEY: RegistryKey<DimensionType> = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
        id("depths_type")
    )

    override fun onInitialize() {
        DepthsDecorators.init()
        DepthsFeatures.init()
        DepthsSurfaceBuilders.init()
        DepthsBiomes.init()
        DepthsBlocks.init()
        DepthsItems.init()

        Registry.register(Registry.CHUNK_GENERATOR,
            id("depths"), DepthsChunkGenerator.CODEC)
        Registry.register(Registry.BIOME_SOURCE,
            id("depths"), DepthsBiomeSource.CODEC)
        FabricDimensions.registerDefaultPlacer(DEPTHS_KEY, ::placeEntity)

        CommandRegistrationCallback.EVENT.register(
            CommandRegistrationCallback { dispatcher: CommandDispatcher<ServerCommandSource?>, dedicated: Boolean ->
                dispatcher.register(
                    literal<ServerCommandSource>("depths").executes(::executeTestCommand)
                )
            }
        )

        println("Hello Fabric world!")
    }

}


