package Sol15

import AOCLib.CompState
import AOCLib.Computer
import kotlin.math.abs

enum class Dir(val id: Long) {
    //U(0L),
    N(1L),
    S(2L),
    W(3L),
    E(4L);

    fun offset(): Pos = when (this) {
        N -> Pos(0, 1)
        S -> Pos(0, -1)
        W -> Pos(-1, 0)
        E -> Pos(1, 0)
    }

    fun reverse(): Dir = when (this) {
        N -> S
        S -> N
        W -> E
        E -> W
    }

    companion object {
        fun of(ch: Char): Dir = valueOf(ch.toString())
        fun near(): List<Dir> = listOf(N, S, W, E)
    }
}

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

class RD(program: LongArray) {
    private val grid = mutableMapOf<Pos, Block>()
    private val frontier = mutableMapOf<Pos, Pos?>()
    private val wave = mutableMapOf<Pos, Int>()
    private var droidPos = Pos.Zero
    private var oxygenPos: Pos? = null
    private val brain = Computer(program)

    init {
        grid.withDefault { Block.Unknown }
        grid[droidPos] = Block.Empty
        frontier[droidPos] = null
        wave[droidPos] = 0
    }

    fun runGame(autoExplorer: Boolean = false, debug: Boolean = false) {
        var done: Boolean
        do {
            if (brain.state == CompState.InputSuspend) {
                val path = if (autoExplorer) calcNextMove()
                else getManualMove()
                brain.input.addAll(path.map { it.id })
            }
            brain.runProgram()
            updateGrid()
            displayGame(debug)
            done = oxygenPos != null && oxygenPos == droidPos
        } while (brain.state != CompState.Halt && !done)
    }

    private fun updateGrid() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun calcNextMove(): MutableList<Dir> {
        val curDist = wave[droidPos]!!
        val (toVisit, needCheck) = (droidPos.near().partition {
            val cell = grid.getOrDefault(it, Block.Unknown)
            cell == Block.Wall || cell == Block.Unknown
        })
        wave.replaceAll { p, d -> if (needCheck.contains(p) && d > curDist + 1) curDist + 1 else d }
        needCheck.forEach { wave.merge(it, curDist + 1) { old, new -> if (old > new) new else old } }

//        val newVisited = visited + toVisit
//        val newToVisit = neighbors - visited.keys
//        return if (newToVisit.isEmpty() || (toVisit.keys intersect endPos).isNotEmpty())
//            newVisited
//        else
//            helper(newVisited, newToVisit)
        return mutableListOf(Dir.N)
    }

    private fun getManualMove(): MutableList<Dir> {
        print("Move Droid to North [W] or South [S] or West [A] or East [D]:")
        var key: Char = ' '
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
        return mutableListOf(Dir.of(key))
    }

    fun displayGame(debug: Boolean = false) {
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
                if (Pos(x, y) == droidPos) print("@@")
                else when (grid[Pos(x, y)]) {
                    Block.Empty -> print("..")
                    Block.Oxygen -> print("**")
                    Block.Wall -> print("##")
                    else -> print("  ")
                }
            }
            println()
        }
        if (debug) {
            for (y in maxY.downTo(minY)) {
                for (x in minX..maxX) {
                    when (grid[Pos(x, y)]) {
                        Block.Empty, Block.Oxygen ->
                            print(wave[Pos(x, y)].toString().padStart(2, ' '))
                        Block.Wall -> print("##")
                        else -> print("  ")
                    }
                }
                println()
            }
        }
    }
}