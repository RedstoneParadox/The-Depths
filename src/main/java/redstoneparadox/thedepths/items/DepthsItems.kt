package redstoneparadox.thedepths.items

import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import redstoneparadox.thedepths.block.DepthsBlocks

object DepthsItems: ItemsInitializer() {

    val DEEP_ROCK: BlockItem = registerNonNullBlockItem("deep_rock", DepthsBlocks.DEEP_ROCK, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))

    fun init() {

    }
}