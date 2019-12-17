package Sol15

fun main() {
    val grid: MutableMap<Pos, Block> = mutableMapOf()
    grid.withDefault { Block.Unknown }
    val droidPos = Pos.Zero
    println(droidPos)
    grid[Pos.Zero] = Block.Empty
    grid[droidPos + Pos.toLeft] = Block.Wall
    grid[droidPos + Pos.toRight] = Block.Wall
    grid[droidPos + Pos.toUp] = Block.Wall
    grid[droidPos + Pos.toDown] = Block.Empty
    grid[droidPos + Pos.toDown + Pos.toDown] = Block.Empty
    grid[droidPos + Pos.toDown + Pos.toDown + Pos.toDown] = Block.Empty
    grid[droidPos + Pos.toDown + Pos.toDown + Pos.toDown + Pos.toLeft] = Block.Oxygen
    println(grid)
    val goalPos = droidPos + Pos.toDown + Pos.toDown + Pos.toDown + Pos.toLeft
    println(goalPos)
    val path = bfs(droidPos, setOf(goalPos), grid)
    println(path)
}