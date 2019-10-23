package redstoneparadox.thedepths.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState

class CrystalBlock(settings: Settings) : Block(settings) {

    override fun hasEmissiveLighting(state: BlockState): Boolean {
        return true
    }
}