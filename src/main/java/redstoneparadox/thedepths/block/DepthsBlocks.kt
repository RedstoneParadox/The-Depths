package redstoneparadox.thedepths.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.*

object DepthsBlocks: BlocksInitializer() {

    val DEEP_ROCK: Block = Block(FabricBlockSettings.copy(Blocks.STONE).build())
    val DEEP_ROCK_SLAB: SlabBlock = SlabBlock(FabricBlockSettings.copy(Blocks.STONE_SLAB).build())
    val DEEP_ROCK_STAIRS: StairsBlock = object : StairsBlock(DEEP_ROCK.defaultState, FabricBlockSettings.copy(Blocks.STONE_BRICKS).build()) {}
    val DEEP_ROCK_BRICKS: Block = Block(FabricBlockSettings.copy(Blocks.STONE_BRICKS).build())
    val DEEP_ROCK_BRICK_SLAB: SlabBlock = SlabBlock(FabricBlockSettings.copy(Blocks.STONE_BRICK_SLAB).build())
    val DEEP_ROCK_BRICK_STAIRS: StairsBlock = object : StairsBlock(DEEP_ROCK_BRICKS.defaultState, FabricBlockSettings.copy(Blocks.STONE_BRICKS).build()) {}
    val DEEP_ROCK_BRICK_WALL: WallBlock = WallBlock(FabricBlockSettings.copy(Blocks.STONE_BRICK_WALL).build())

    val RED_CRYSTAL: CrystalBlock = CrystalBlock(FabricBlockSettings.copy(Blocks.STONE).build())
    val BLUE_CRYSTAL: CrystalBlock = CrystalBlock(FabricBlockSettings.copy(Blocks.STONE).build())
    val WHITE_CRYSTAL: CrystalBlock = CrystalBlock(FabricBlockSettings.copy(Blocks.STONE).build())

    val MOSS: MossBlock = MossBlock()

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

        register("moss", MOSS)
    }
}