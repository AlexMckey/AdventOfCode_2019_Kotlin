package Sol15

import AOCLib.CompState
import AOCLib.Computer
import AOCLib.Point

data class Path(val from: Point, val to: Point) {
    val dir: Char = pointToDir(to - from)
    val backDir: Char = pointToDir(to - from)

    constructor(from: Point, byDir: Char) : this(from, from + dirToPoint(byDir))
}

private fun dirToOut(key: Char): Int =
    when (key) {
        'N' -> 1
        'S' -> 2
        'W' -> 3
        'E' -> 4
        else -> 0
    }

private fun dirToPoint(key: Char): Point =
    when (key) {
        'N' -> Point.toUp
        'S' -> Point.toDown
        'W' -> Point.toLeft
        'E' -> Point.toRight
        else -> Point.Zero
    }

private fun pointToDir(point: Point): Char =
    when (point) {
        Point.toLeft -> 'W'
        Point.toRight -> 'E'
        Point.toUp -> 'N'
        Point.toDown -> 'S'
        else -> ' '
    }

class RepairDroid(program: LongArray) {
    private val grid = mutableMapOf<Point, Int>()
    private val wave = mutableMapOf<Point, Pair<Int, Point>>()
    private val toVisit = mutableListOf<Path>()
    private val brain = Computer(program)
    private var droidPos = Point(0, 0)
    //private var goalPos: Point = droidPos
    private var osPos: Point? = null

    init {
        grid[droidPos] = 1
        wave[droidPos] = 0 to droidPos
    }

    fun runGame(autoExplorer: Boolean = false, debug: Boolean = false) {
        var done: Boolean
        var path: Collection<Path> = emptyList()
        do {
            if (brain.state == CompState.InputSuspend) {
                path = if (autoExplorer) calcNextMove()
                else getManualMove()
                brain.input.addAll(path.map { dirToOut(it.dir).toLong() })
            }
            brain.runProgram()
            updateGrid(path.lastOrNull())
            displayGame(debug)
            done = osPos != null && osPos == droidPos
        } while (brain.state != CompState.Halt && !done)
    }

    private fun updateGrid(path: Path?) {
        if (brain.output.size == 0) return
        if (path == null) return
        when (val out = brain.output.removeAt(0)) {
            0L -> {
                grid[path.to] = 3
            }
            1L, 2L -> {
                grid[path.to] = out.toInt()
                val cnt = wave[path.from]?.first ?: 0 + 1
                if (cnt < wave[droidPos]?.first ?: 0 + 1)
                    wave[path.from] = cnt to path.to
                droidPos = path.to
                if (out == 2L) osPos = droidPos
            }
            else -> {
            }
        }
    }

    private fun calcNextMove(): Collection<Path> {
        toVisit.addAll((droidPos.near() - grid.keys).map { Path(droidPos, it) })
        toVisit.sortBy { it.from.manhattanDistance(droidPos) }
        val res = toVisit.removeAt(0)
        return if (res.to.manhattanDistance(droidPos) > 1)
            backPath(res, droidPos)
        else listOf(res)
    }

    private fun backPath(to: Path, prom: Point): Collection<Path> {
        val res = mutableListOf(to)
        var curPoint = to.from
        while (curPoint != prom) {
            val backPoint = wave[curPoint]?.second ?: curPoint
            res.add(Path(backPoint, curPoint))
            curPoint = backPoint
        }
        return res
    }

    private fun getManualMove(): Collection<Path> {
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
        toVisit.add(Path(droidPos, key))
        return listOf(toVisit.removeAt(0))
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
                if (Point(x, y) == droidPos) print("D")
                else when (grid.getOrDefault(Point(x, y), 0)) {
                    1 -> print(".")
                    2 -> print("*")
                    3 -> print("#")
                    else -> print(" ")
                }
            }
            println()
        }
        if (debug) {
            for (y in maxY.downTo(minY)) {
                for (x in minX..maxX) {
                    when (grid.getOrDefault(Point(x, y), 0)) {
                        1, 2 -> print(wave[Point(x, y)]?.first ?: "x")
                        3 -> print("#")
                        else -> print(" ")
                    }
                }
                println()
            }
        }
    }

}