import Sol15.Block
import Sol15.Pos
import Sol15.bfs
import Sol15.findPath

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

val bfsTree = bfs(droidPos, setOf(goalPos), grid)
bfsTree
bfsTree.size
bfs(droidPos, setOf(goalPos), grid)
val ps = findPath(droidPos, goalPos, grid).zipWithNext().map { (it.first - it.second).toDir() }
ps