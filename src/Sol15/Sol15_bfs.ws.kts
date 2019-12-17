import Sol15.Block
import Sol15.Pos
import Sol15.bfs
import Sol15.path

val grid: MutableMap<Pos, Block> = mutableMapOf()
grid.withDefault { Block.Unknown }
val droidPos = Pos.Zero
grid[Pos.Zero] = Block.Empty
grid[droidPos + Pos.toLeft] = Block.Wall
grid[droidPos + Pos.toRight] = Block.Wall
grid[droidPos + Pos.toUp] = Block.Wall
grid[droidPos + Pos.toDown] = Block.Empty
grid[droidPos + Pos.toDown + Pos.toDown] = Block.Empty
grid[droidPos + Pos.toDown + Pos.toDown + Pos.toDown] = Block.Empty
grid[droidPos + Pos.toDown + Pos.toDown + Pos.toDown + Pos.toLeft] = Block.Oxygen
grid
val goalPos = droidPos + Pos.toDown + Pos.toDown + Pos.toDown + Pos.toLeft
goalPos
fun posToDir(pos: Pos): Char =
    when (pos) {
        Pos.toLeft -> 'W'
        Pos.toRight -> 'E'
        Pos.toUp -> 'N'
        Pos.toDown -> 'S'
        else -> ' '
    }

val bfsTree: Map<Pos, Pair<Pos, Int>> = bfs(droidPos, setOf(goalPos), grid)
bfsTree
bfsTree.size
bfs(droidPos, setOf(goalPos), grid)
val ps = path(droidPos, goalPos, grid, false).zipWithNext().map { posToDir(it.first - it.second) }
ps