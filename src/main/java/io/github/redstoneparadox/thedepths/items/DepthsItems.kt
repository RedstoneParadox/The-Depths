package io.github.redstoneparadox.thedepths.items

import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import io.github.redstoneparadox.thedepths.block.DepthsBlocks

object DepthsItems: ItemsInitializer() {

    val DEEP_ROCK: BlockItem = BlockItem(DepthsBlocks.DEEP_ROCK, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val DEEP_ROCK_SLAB: BlockItem = BlockItem(DepthsBlocks.DEEP_ROCK_SLAB, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val DEEP_ROCK_STAIRS: BlockItem = BlockItem(DepthsBlocks.DEEP_ROCK_STAIRS, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val DEEP_ROCK_BRICKS: BlockItem = BlockItem(DepthsBlocks.DEEP_ROCK_BRICKS, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val DEEP_ROCK_BRICK_SLAB: BlockItem = BlockItem(DepthsBlocks.DEEP_ROCK_BRICK_SLAB, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val DEEP_ROCK_BRICK_STAIRS: BlockItem = BlockItem(DepthsBlocks.DEEP_ROCK_BRICK_STAIRS, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    val DEEP_ROCK_BRICK_WALL: BlockItem = BlockItem(DepthsBlocks.DEEP_ROCK_BRICK_WALL, Item.Settings().group(ItemGroup.DECORATIONS))

    val RED_CRYSTAL: BlockItem = BlockItem(DepthsBlocks.RED_CRYSTAL, Item.Settings().group(ItemGroup.DECORATIONS))
    val BLUE_CRYSTAL: BlockItem = BlockItem(DepthsBlocks.BLUE_CRYSTAL, Item.Settings().group(ItemGroup.DECORATIONS))
    val WHITE_CRYSTAL: BlockItem = BlockItem(DepthsBlocks.WHITE_CRYSTAL, Item.Settings().group(ItemGroup.DECORATIONS))

    fun init() {
        register("deep_rock", DEEP_ROCK)
        register("deep_rock_slab", DEEP_ROCK_SLAB)
        register("deep_rock_stairs", DEEP_ROCK_STAIRS)
        register("deep_rock_bricks", DEEP_ROCK_BRICKS)
        register("deep_rock_brick_slab", DEEP_ROCK_BRICK_SLAB)
        register("deep_rock_brick_stairs", DEEP_ROCK_BRICK_STAIRS)
        register("deep_rock_brick_wall", DEEP_ROCK_BRICK_WALL)

        register("red_crystal", RED_CRYSTAL)
        register("blue_crystal", BLUE_CRYSTAL)
        register("white_crystal", WHITE_CRYSTAL)
    }
}