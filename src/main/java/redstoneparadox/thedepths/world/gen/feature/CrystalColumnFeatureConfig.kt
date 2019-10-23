package redstoneparadox.thedepths.world.gen.feature

import com.mojang.datafixers.Dynamic
import com.mojang.datafixers.types.DynamicOps
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.feature.FeatureConfig
import redstoneparadox.thedepths.block.DepthsBlocks
import java.util.function.Function

class CrystalColumnFeatureConfig(
    val crystal: BlockState,
    val stone: BlockState,
    val radius: Int,
    val genChance: Double,
    val crystalChance: Float,
    val biome: Biome
): FeatureConfig {

    // TODO: Actually implement this.
    override fun <T : Any> serialize(ops: DynamicOps<T>): Dynamic<T> {
        return Dynamic(ops, ops.createMap(
            mapOf(
                ops.createString("crystal") to BlockState.serialize(ops, crystal).value,
                ops.createString("stone") to BlockState.serialize(ops, stone).value,
                ops.createString("radius") to ops.createInt(radius),
                ops.createString("genChance") to ops.createDouble(genChance),
                ops.createString("crystalChance") to ops.createFloat(crystalChance),
                ops.createString("biome") to ops.createString(Registry.BIOME.getId(biome).toString())
            )
        ))
    }

    companion object {
        fun <T> deserialize(dynamic: Dynamic<T>): CrystalColumnFeatureConfig {
            val crystal = dynamic["crystal"].map { BlockState.deserialize(it) }.orElse(DepthsBlocks.RED_CRYSTAL.defaultState) as BlockState
            val stone = dynamic["stone"].map { BlockState.deserialize(it) }.orElse(DepthsBlocks.RED_CRYSTAL.defaultState) as BlockState
            val radius = dynamic["radius"].asInt(1)
            val genChance = dynamic["genChance"].asDouble(0.5)
            val crystalChance = dynamic["crystalChance"].asFloat(0.5f)
            val biome = dynamic["biome"].map { dyn -> dyn.asString().map { if (Identifier.isValid(it)) { Registry.BIOME[Identifier(it)] } else { Biomes.THE_VOID } }; Biomes.THE_VOID }.orElse(Biomes.THE_VOID)

            return CrystalColumnFeatureConfig(crystal, stone, radius, genChance, crystalChance, biome)
        }
    }
}