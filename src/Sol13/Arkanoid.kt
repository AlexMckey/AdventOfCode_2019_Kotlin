package Sol13

import AOCLib.CompState
import AOCLib.Computer
import AOCLib.Point


class ArkanoidGame(
    program: LongArray,
    quarters: Long? = null,
    autoPlay: Boolean = false,
    gameInput: List<Long> = emptyList()
) {
    private val grid: MutableMap<Point, Long> = mutableMapOf()
    private val comp = Computer(program)
    private var auto = false

    init {
        comp.input = gameInput.toMutableList()
        if (quarters != null) comp.memory[0] = quarters
        auto = autoPlay
    }

    var score: Long = 0
    fun ballPos(): Point? =
        grid.filterValues { it == 4L }.keys.firstOrNull()

    fun paddlePos(): Point? =
        grid.filterValues { it == 3L }.keys.firstOrNull()

    fun runGame(testOutput: List<Long> = emptyList()) {
        if (testOutput.isNotEmpty())
            comp.output = testOutput.toMutableList()
        do {
            comp.runProgram()
            updateGrid()
            displayGame()
            if (comp.state == CompState.InputSuspend) {
                comp.input.add(
                    if (auto) calcNextMove()
                    else getManualMove()
                )
            }
        } while (comp.state != CompState.Halt)
    }

    private fun calcNextMove(): Long {
        val ballPos = ballPos()
        val paddlePos = paddlePos()
        if (ballPos == null || paddlePos == null) return 0
        return ballPos.x.compareTo(paddlePos.x).toLong()
    }

    private fun getManualMove(): Long {
        print("Move Joystick to Left [L] or Right [R] or stay neutral [Space or Enter]:")
        val key = readLine()?.toUpperCase()
        return when (key) {
            "L" -> -1L
            "R" -> 1L
            else -> 0L
        }
    }

    private fun updateGrid() {
        comp.output.chunked(3).forEach { cmd ->
            if (cmd[0] == -1L && cmd[1] == 0L) score = cmd[2]
            else grid[Point(cmd[0].toInt(), cmd[1].toInt())] = cmd[2]
        }
    }

    fun blockTiles() = grid.filterValues { it == 2L }

    fun displayGame() {
        //val ANSI_CLS = "\u001b[2J"
        //val ANSI_HOME = "\u001b[H"
        //print(ANSI_CLS + ANSI_HOME)
        //System.out.flush()
        ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor() // Win8
        val minX = grid.keys.minBy { it.x }!!.x
        val maxX = grid.keys.maxBy { it.x }!!.x
        val minY = grid.keys.minBy { it.y }!!.y
        val maxY = grid.keys.maxBy { it.y }!!.y
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                when (grid.getOrDefault(Point(x, y), 0L)) {
                    0L -> print(" ")
                    1L -> print("█")
                    2L -> print("■")
                    3L -> print("=")
                    4L -> print("*")
                    else -> print(" ")
                }
            }
            println()
        }
        println(score)
    }
}