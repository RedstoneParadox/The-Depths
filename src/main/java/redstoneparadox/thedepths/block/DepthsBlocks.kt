package redstoneparadox.thedepths.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks

object DepthsBlocks: BlocksInitializer() {

    val DEEP_ROCK: Block = Block(FabricBlockSettings.copy(Blocks.STONE).build())

    fun init() {
        register("deep_rock", DEEP_ROCK)
    }
}