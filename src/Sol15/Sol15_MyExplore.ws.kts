import kotlin.math.abs

enum class Block(val id: Long) {
    Wall(0L),
    Empty(1L),
    Oxygen(2L),
    Unknown(-1);

    companion object {
        fun of(id: Long): Block = Block.values().first { it.id == id }
    }
}

data class Pos(val x: Int, val y: Int) {
    override fun toString(): String = "[x:$x,y:$y]"
    operator fun plus(other: Pos) = Pos(other.x + x, other.y + y)
    operator fun plus(other: Pair<Int, Int>) = Pos(other.first + x, other.second + y)
    operator fun minus(other: Pos) = Pos(other.x - x, other.y - y)
    fun manhattanDistance(p2: Pos = Zero): Int {
        return abs(this.x - p2.y) + abs(this.y - p2.y)
    }

    fun near(): List<Pos> = listOf(this + toLeft, this + toRight, this + toUp, this + toDown)

    companion object {
        val Zero = Pos(0, 0)
        val toLeft = Pos(-1, 0)
        val toRight = Pos(1, 0)
        val toUp = Pos(0, 1)
        val toDown = Pos(0, -1)
    }
}

fun posToDir(pos: Pos): Char =
    when (pos) {
        Pos.toLeft -> 'W'
        Pos.toRight -> 'E'
        Pos.toUp -> 'N'
        Pos.toDown -> 'S'
        else -> ' '
    }

val grid = mutableMapOf<Pos, Block>()
val backPath = mutableMapOf<Pos, Pos?>()
val wave = mutableMapOf<Pos, Int>()
val toVisit = mutableMapOf<Pos, Pair<Pos, Int>>()
val startPos = Pos.Zero
var droidPos = Pos.Zero
var oxygenPos: Pos? = null
var goalPos: Pos? = null

grid.withDefault { Block.Unknown }
grid[droidPos] = Block.Empty
backPath[droidPos] = null
wave[droidPos] = 0

var parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
var needVisit = parts.first
var needCheck = parts.second
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit

var goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))

//--------------------------------------------
grid[goal.key] = Block.Wall
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))
//-----------------------------------------------
grid[goal.key] = Block.Wall
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(goal.value.first.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))
//-------------------------------------------------------
grid[goal.key] = Block.Wall
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))
//-----------------------------------------------------------
grid[goal.key] = Block.Empty
droidPos = goal.key
backPath[goal.key] = goal.value.first
wave[droidPos] = goal.value.second
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))
//-------------------------------------------------------
grid[goal.key] = Block.Wall
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))
//-------------------------------------------------------
grid[goal.key] = Block.Empty
droidPos = goal.key
backPath[goal.key] = goal.value.first
wave[droidPos] = goal.value.second
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))
//-------------------------------------------------------
grid[goal.key] = Block.Empty
droidPos = goal.key
backPath[goal.key] = goal.value.first
wave[droidPos] = goal.value.second
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))
//-------------------------------------------------------
grid[goal.key] = Block.Empty
droidPos = goal.key
backPath[goal.key] = goal.value.first
wave[droidPos] = goal.value.second
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))
//-------------------------------------------------------
grid[goal.key] = Block.Wall
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))
//-------------------------------------------------------
grid[goal.key] = Block.Wall
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))
//-------------------------------------------------------
grid[goal.key] = Block.Wall
grid

parts = (droidPos.near().partition {
    val cell = grid.getOrDefault(it, Block.Unknown)
    cell == Block.Wall || cell == Block.Unknown
})
needVisit = parts.first
needCheck = parts.second
needVisit
toVisit.putAll((needVisit - grid.keys).map { it to (droidPos to wave[droidPos]!! + 1) }.toMap())
toVisit
goal = toVisit.entries.first()
goal
toVisit.remove(goal.key)
toVisit
println(droidPos.manhattanDistance(goal.key))
println(posToDir(goal.value.first - goal.key))


var curDist = wave[droidPos]!!
curDist
//wave.replaceAll { p, d -> if (needCheck.contains(p) && d > curDist + 1) curDist + 1 else d }
//needCheck.forEach { wave.merge(it,curDist+1){ old, new -> if (old > new) new else old } }


grid[droidPos + Pos.toLeft] = Block.Wall
grid[droidPos + Pos.toRight] = Block.Wall
grid[droidPos + Pos.toUp] = Block.Wall
grid[droidPos + Pos.toDown] = Block.Empty
grid[droidPos + Pos.toDown + Pos.toDown] = Block.Empty
grid[droidPos + Pos.toDown + Pos.toDown + Pos.toDown] = Block.Empty
grid[droidPos + Pos.toDown + Pos.toDown + Pos.toDown + Pos.toLeft] = Block.Oxygen