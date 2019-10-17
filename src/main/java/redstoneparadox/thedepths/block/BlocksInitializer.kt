package redstoneparadox.thedepths.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.Block
import net.minecraft.util.registry.Registry

abstract class BlocksInitializer {

    protected fun <T : Block> register(id: String, block: T): T {
        return Registry.register(Registry.BLOCK, "thedepths:$id", block)
    }

    protected fun <T : Block> register(id: String, block: T, vararg conditions : Boolean): T? {
        for (condition in conditions) {
            if (!condition) return null
        }
        return register(id, block)
    }

    protected fun registerFlammableBlocks(blocks : Array<Block?>, entry : FlammableBlockRegistry.Entry) {
        for (block in blocks) {
            registerFlammableBlock(block, entry)
        }
    }

    protected fun registerFlammableBlock(block: Block?, entry : FlammableBlockRegistry.Entry) {
        if (block != null) {
            FlammableBlockRegistry.getDefaultInstance().add(block, entry)
        }
    }

    protected fun copySettings(block: Block): Block.Settings {
        return FabricBlockSettings.copy(block).build()
    }
}