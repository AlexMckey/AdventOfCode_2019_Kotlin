package Sol15

import AOCLib.CompState
import AOCLib.Computer
import kotlin.math.abs

enum class Dir(val id: Long) {
    U(0L),
    N(1L),
    S(2L),
    W(3L),
    E(4L);

    fun toOffset(): Pos =
        when (this) {
            U -> Pos.Zero
            N -> Pos.toUp
            S -> Pos.toDown
            W -> Pos.toLeft
            E -> Pos.toRight
        }

    companion object {
        fun of(ch: Char): Dir = valueOf(ch.toString())
    }
}

enum class Block(val id: Long) {
    Wall(0L),
    Empty(1L),
    Oxygen(2L),
    Unknown(-1);

    companion object {
        fun of(id: Long): Block = values().first { it.id == id }
    }
}

data class Pos(val x: Int, val y: Int) {
    override fun toString(): String = "[x:$x,y:$y]"

    operator fun plus(other: Pos) = Pos(other.x + x, other.y + y)
    operator fun plus(other: Pair<Int, Int>) = Pos(other.first + x, other.second + y)
    operator fun minus(other: Pos) = Pos(other.x - x, other.y - y)

    fun manhattanDistance(p2: Pos = Zero): Int {
        return abs(this.x - p2.x) + abs(this.y - p2.y)
    }

    fun near(): List<Pos> = listOf(this + toLeft, this + toRight, this + toUp, this + toDown)

    fun toDir(): Dir =
        when (this) {
            toLeft -> Dir.W
            toRight -> Dir.E
            toUp -> Dir.N
            toDown -> Dir.S
            else -> Dir.U
        }

    companion object {
        val Zero = Pos(0, 0)
        val toLeft = Pos(-1, 0)
        val toRight = Pos(1, 0)
        val toUp = Pos(0, 1)
        val toDown = Pos(0, -1)
    }
}

class RD(program: LongArray) {
    private val grid = mutableMapOf<Pos, Block>()
    private val toVisit = mutableMapOf<Pos, Pos>()
    private val startPos = Pos.Zero
    private var droidPos = Pos.Zero
    private var oxygenPos: Pos? = null
    private var goalPos: Pair<Pos, Pos> = Pos.Zero to Pos.Zero
    var oxygenPath: List<Pos>? = null
    var allExplored: Boolean = false
    private val brain = Computer(program)

    init {
        grid.withDefault { Block.Unknown }
        grid[droidPos] = Block.Empty
    }

    fun runGame(autoExplorer: Boolean = false, findOxygen: Boolean = true, displayGrid: Boolean = false) {
        var done: Boolean
        val path: MutableList<Dir> = mutableListOf()
        var goal: Dir = Dir.U
        do {
            if (!findOxygen && allExplored) break
            if (brain.state == CompState.InputSuspend) {
                if (path.isEmpty())
                    path.addAll(
                        if (autoExplorer)
                            calcNextMove()
                        else getManualMove()
                    )
                if (!findOxygen && allExplored) break
                goal = path.removeAt(0)
                brain.input.add(goal.id)
            }
            brain.runProgram()
            updateGrid(droidPos + goal.toOffset())
            if (displayGrid) displayGame()
            done = oxygenPos != null && oxygenPos == droidPos
            if (!findOxygen) done = done && allExplored
        } while (brain.state != CompState.Halt && !done)
        oxygenPath = pathToOxygen().drop(1)
        displayGame()
    }

    private fun updateGrid(goal: Pos) {
        if (brain.output.size == 0) return
        val out = brain.output.removeAt(0)
        grid[goal] = Block.of(out)
        if (grid[goal] == Block.Empty || grid[goal] == Block.Oxygen) droidPos = goal
        if (out == Block.Oxygen.id) oxygenPos = droidPos
    }

    private fun calcNextMove(): MutableList<Dir> {
        toVisit.putAll((droidPos.near() - grid.keys).map { it to droidPos }.toMap())
        allExplored = toVisit.isEmpty()
        if (allExplored) return mutableListOf()
        goalPos = toVisit.minBy { droidPos.manhattanDistance(it.key) }!!.toPair()
        //goalPos = toVisit.entries.first().toPair()
        toVisit.remove(goalPos.first)
        val path = if (droidPos.manhattanDistance(goalPos.first) == 1) listOf(goalPos.second, goalPos.first)
        else findPath(goalPos.second, droidPos, grid) + goalPos.first
        return path.zipWithNext().map { (it.first - it.second).toDir() }.toMutableList()
    }

    private fun getManualMove(): MutableList<Dir> {
        print("Move Droid to North [W] or South [S] or West [A] or East [D]:")
        var key = ' '
        var code: Int
        while (-1 != System.`in`.read().also { code = it }) {
            key = code.toChar().toUpperCase()
            if ('Q' == key) {
                System.exit(0)
            }
            if ("WASD".contains(key)) break
        }
        key = when (key) {
            'W' -> 'N'
            'S' -> 'S'
            'A' -> 'W'
            'D' -> 'E'
            else -> ' '
        }
        val dir = Dir.of(key)
        //goalPos = droidPos + dir.toOffset() to droidPos
        return mutableListOf(dir)
    }

    private fun pathToOxygen(): List<Pos> =
        if (oxygenPos != null)
            findPath(oxygenPos!!, startPos, grid)
        else emptyList()

    fun minutesToFillOxygen(): Int {
        val bfs = bfs(oxygenPos!!, emptySet(), grid).maxBy { it.value.second }
        return bfs!!.value.second
    }

    fun displayGame() {
        ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor() // Win8
        val ANSI_CLS = "\u001b[2J"
        val ANSI_HOME = "\u001b[H"
        print(ANSI_CLS + ANSI_HOME)
        System.out.flush()
        val minX = grid.keys.minBy { it.x }!!.x
        val maxX = grid.keys.maxBy { it.x }!!.x
        val minY = grid.keys.minBy { it.y }!!.y
        val maxY = grid.keys.maxBy { it.y }!!.y
        for (y in maxY.downTo(minY)) {
            for (x in minX..maxX) {
                if (Pos(x, y) == droidPos) print("@")
                else if (Pos(x, y) == startPos) print("$")
                else if (oxygenPath != null && oxygenPath!!.contains(Pos(x, y)))
                    print("#")
                else when (grid[Pos(x, y)]) {
                    Block.Empty -> print(".")
                    Block.Oxygen -> print("*")
                    Block.Wall -> print("█")
                    else -> print(" ")
                }
            }
            println()
        }
    }
}