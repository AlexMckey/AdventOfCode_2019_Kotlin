package Sol17

import AOCLib.CompState
import AOCLib.Computer

class ASCIIprogram(program: LongArray) {
    private var grid: Map<Pos, Char> = emptyMap()
    private var crosses: Map<Pos, Char> = emptyMap()
    private val robotPos: Pos? = null
    private val brain = Computer(program)
    val aligmentSum: Int
        get() = crosses.map { it.key.x * it.key.y }.sum()

    fun getCameraView(displayView: Boolean = false) {
        brain.memory[0] = 1L
        brain.runProgram()
        updateGrid()
        findCrosses()
        if (displayView) displayGrid()
    }

    private fun findCrosses() {
        crosses = grid
            .filterValues { c -> c == '#' }
            .filterKeys { p -> p.near().all { n -> grid[n] == '#' } }
    }

    private fun updateGrid() {
        grid = brain.output
            .map { it.toChar() }
            .joinToString("")
            .split("\n")
            .withIndex()
            .flatMap { yv -> yv.value.mapIndexed { x, c -> Pos(x, yv.index) to c } }
            .toMap()
    }

    fun runRobotProgram() {
        brain.memory[0] = 2L
        do {
            if (brain.state == CompState.InputSuspend) {
                brain.input.add(0L)
            }
            brain.runProgram()
            updateGrid()
        } while (brain.state != CompState.Halt)
    }

    fun displayGrid() {
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
                print(grid[Pos(x, y)])
            }
            println()
        }
    }
}