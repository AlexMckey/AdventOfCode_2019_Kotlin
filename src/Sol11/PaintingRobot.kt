package Sol11

import AOCLib.CompState
import AOCLib.Computer
import AOCLib.Point

class PaintingRobot(program: LongArray, start: Long = 0L) {
    val colorCnt: MutableMap<Long, Int> = mutableMapOf(0L to 0, 1L to 0)
    val panelPaintedWhite: MutableSet<Point> = mutableSetOf()
    val panelPaintedBlack: MutableSet<Point> = mutableSetOf()
    private var curPos = Point(0, 0)
    private val grid: MutableMap<Point, Long> = mutableMapOf(curPos to start)
    private var curDir = 0
    private val brain = Computer(program)

    private fun step() {
        if (brain.output.size < 2) return
        val color = brain.output.removeAt(0)
        val turn = brain.output.removeAt(0)
        if (color == 1L) panelPaintedWhite.add(curPos)
        else panelPaintedBlack.add(curPos)
        colorCnt[color] = colorCnt[color]!! + 1
        curDir += if (turn == 0L) 1 else -1
        curDir = (curDir + 4) % 4
        val forwardStep = when (curDir) {
            2 -> Point.incYPoint()
            0 -> Point.decYPoint()
            3 -> Point.incXPoint()
            1 -> Point.decXPoint()
            else -> { p -> p }
        }
        grid[curPos] = color
        curPos = forwardStep(curPos)
    }

    fun runRobot() {
        brain.input.add(grid[curPos]!!)
        do {
            brain.runProgram()
            if (brain.state == CompState.InputSuspend)
                while (brain.output.size > 0) step()
            if (!grid.containsKey(curPos))
                grid[curPos] = 0L
            brain.input.add(grid[curPos]!!)
        } while (brain.state != CompState.Halt)
    }

    fun draw() {
        val minX = panelPaintedWhite.minBy { it.x }!!.x
        val maxX = panelPaintedWhite.maxBy { it.x }!!.x
        val minY = panelPaintedWhite.minBy { it.y }!!.y
        val maxY = panelPaintedWhite.maxBy { it.y }!!.y
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                when (grid.getOrDefault(Point(x, y), 0L)) {
                    0L -> print(" ")
                    1L -> print("#")
                }
            }
            println()
        }
    }
}