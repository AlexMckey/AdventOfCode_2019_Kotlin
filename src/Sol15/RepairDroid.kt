package Sol15

import AOCLib.CompState
import AOCLib.Computer
import AOCLib.Point


class RepairDroid(program: LongArray) {
    private val grid = mutableMapOf<Point, Int>()
    private val wave = mutableMapOf<Point, Int>()
    private val toVisit = mutableSetOf<Point>()
    private val path = mutableMapOf<Point, String>()
    private val brain = Computer(program)
    private var droidPos = Point(0, 0)
    private var goalPos: Point = droidPos
    private var osPos: Point? = null

    init {
        grid[droidPos] = 1
        wave[droidPos] = 0
    }

    fun runGame(autoExplorer: Boolean = false, debug: Boolean = false) {
        var done: Boolean
        do {
            if (brain.state == CompState.InputSuspend) {
                brain.input.add(
                    (if (autoExplorer) calcNextMove()
                    else getManualMove()).toLong()
                )
            }
            brain.runProgram()
            updateGrid()
            displayGame(debug)
            done = osPos != null && osPos == droidPos
        } while (brain.state != CompState.Halt && !done)
    }

    private fun updateGrid() {
        if (brain.output.size == 0) return
        when (brain.output.removeAt(0)) {
            0L -> {
                grid[goalPos] = 2
                goalPos = droidPos
            }
            1L -> {
                grid[goalPos] = 1
                val cnt = wave.getOrPut(goalPos, { wave.getOrDefault(droidPos, 0) + 1 })
                if (cnt >= wave.getOrDefault(droidPos, 0) + 1)
                    wave[goalPos] = wave.getOrDefault(droidPos, 0) + 1
                droidPos = goalPos
            }
            2L -> {
                grid[goalPos] = 3
                val cnt = wave.getOrPut(goalPos, { wave.getOrDefault(droidPos, 0) + 1 })
                if (cnt >= wave.getOrDefault(droidPos, 0) + 1)
                    wave[goalPos] = wave.getOrDefault(droidPos, 0) + 1
                droidPos = goalPos
                osPos = goalPos
            }
            else -> {
            }
        }
    }

    private fun getManualMove(): Int {
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
        goalPos = droidPos + dirToPoint(key)
        return dirToOut(key)
    }

    private fun dirToOut(key: Char): Int =
        when (key) {
            'W' -> 1
            'S' -> 2
            'A' -> 3
            'D' -> 4
            else -> 0
        }

    private fun dirToPoint(key: Char): Point =
        when (key) {
            'W' -> Point(0, 1)
            'S' -> Point(0, -1)
            'A' -> Point(-1, 0)
            'D' -> Point(1, 0)
            else -> Point.Zero
        }

    private fun pointToDir(point: Point): Char =
        when (point) {
            Point.toLeft -> 'A'//'W'
            Point.toRight -> 'D'//'E'
            Point.toUp -> 'W'//'N'
            Point.toDown -> 'S'//'S'
            else -> ' '
        }

    private fun calcNextMove(): Int {
        toVisit.addAll(droidPos.near() - grid.keys)
        val dir = toVisit.toList().minBy { it.distance(droidPos) }!!
        toVisit.remove(dir)
        goalPos = dir
        return dirToOut(pointToDir(dir))
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
                    2 -> print("#")
                    3 -> print("*")
                    else -> print(" ")
                }
            }
            println()
        }
        if (debug) {
            for (y in maxY.downTo(minY)) {
                for (x in minX..maxX) {
                    if (Point(x, y) == droidPos) print("D")
                    else when (grid.getOrDefault(Point(x, y), 0)) {
                        1 -> print(wave.getOrDefault(Point(x, y), 0))
                        2 -> print("#")
                        3 -> print(wave.getOrDefault(Point(x, y), 0))
                        else -> print(" ")
                    }
                }
                println()
            }
        }
    }

}