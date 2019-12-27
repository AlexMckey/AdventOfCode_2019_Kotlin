package Sol19

import AOCLib.Computer
import kotlin.math.abs

data class Pos(val x: Int, val y: Int) {
    override fun toString(): String = "[x:$x,y:$y]"

    operator fun plus(other: Pos) = Pos(other.x + x, other.y + y)
    operator fun plus(other: Pair<Int, Int>) = Pos(other.first + x, other.second + y)
    operator fun minus(other: Pos) = Pos(other.x - x, other.y - y)

    fun manhattanDistance(p2: Pos = Zero): Int {
        return abs(this.x - p2.x) + abs(this.y - p2.y)
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

class Drone(program: LongArray) {
    private val grid = mutableMapOf<Pos, Int>()
    private val brain = Computer(program)

    fun exploreArea(h: Int, w: Int): MutableMap<Pos, Int> {
        val inputData = (0L until h)
            .flatMap { y ->
                (0L until w)
                    .flatMap { x -> listOf(x, y) }
            }
            .chunked(2)
            .map { Pos(it.first().toInt(), it.last().toInt()) }
            .forEach { pos ->
                brain.input = mutableListOf(pos.x.toLong(), pos.y.toLong())
                brain.reset()
                brain.runProgram()
                grid[pos] = brain.output.removeAt(0).toInt()
            }
        displayGame()
        return grid
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
                when (grid[Pos(x, y)]) {
                    1 -> print('#')
                    else -> print('.')
                }
            }
            println()
        }
    }
}