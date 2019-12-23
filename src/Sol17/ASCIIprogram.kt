package Sol17

import AOCLib.Computer

class ASCIIprogram(program: LongArray) {
    private var grid: Map<Pos, Char> = emptyMap()
    private val brain = Computer(program)
    val dustCount
        get() = brain.output.last()
    val alignmentSum
        get() = alignmentSum()

    fun getCameraView(displayView: Boolean = false) {
        brain.memory[0] = 1L
        brain.runProgram()
        updateGrid()
        if (displayView) displayGrid()
        brain.output = mutableListOf()
    }

    private fun alignmentSum() =
        grid.filterValues { c -> c == '#' }
            .filterKeys { p -> p.near().all { n -> grid[n] == '#' } }
            .map { it.key.x * it.key.y }.sum()

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
        val scaffolds = grid.filterValues { "#v^><".contains(it) }
        val robotPos = scaffolds.filterValues { "v^><".contains(it) }.keys.first()
        val curDir = Dir(
            robotPos, when (scaffolds[robotPos]) {
                '^' -> 'U'
                '>' -> 'L'
                '<' -> 'R'
                'v' -> 'D'
                else -> ' '
            }
        )

        fun nextDir(dir: Dir): Dir? {
            val neighbors = dir.p.near() intersect scaffolds.keys
            val nextFLR = listOf(dir.forward(), dir.rotLeft(), dir.rotRight())
            return if (neighbors.contains(nextFLR[0].p)) nextFLR[0]
            else {
                nextFLR.find { neighbors.contains(it.p) }
            }
        }

        tailrec fun getPath(dir: Dir, acc: List<Char>): List<Char> {
            val nextDir = nextDir(dir)
            return if (nextDir == null) acc
            else getPath(nextDir, acc + nextDir.dir)
        }

        val fullPath = getPath(curDir, emptyList()).joinToString("")
        tailrec fun compressPath(s: String, dir: Char, acc: List<String>): List<String> =
            if (s == "") acc
            else {
                val c = s.first()
                val seq = s.takeWhile { it == c }
                val rotate = dir.toString() + c.toString()
                val nextDir = when (rotate) {
                    "LD", "UL", "DR", "RU" -> 'L'
                    "DL", "LU", "RD", "UR" -> 'R'
                    else -> c
                }
                compressPath(s.drop(seq.length), c, acc + (nextDir.toString() + seq.length))
            }

        val path = compressPath(fullPath, fullPath.first(), emptyList()).joinToString("")
        check(path == "L12L8R10R10L6L4L12L12L8R10R10L6L4L12R10L8L4R10L6L4L12L12L8R10R10R10L8L4R10L6L4L12R10L8L4R10")
        val re = "^(.{1,21})\\1*(.{1,21})(?:\\1|\\2)*(.{1,21})(?:\\1|\\2|\\3)*\$".toRegex()
        val matching = re.matchEntire(path)
        if (matching != null && matching.groups.size == 4) {
            val progMain = matching.groups[0]!!.value
                .replace(matching.groups[1]!!.value, "A")
                .replace(matching.groups[2]!!.value, "B")
                .replace(matching.groups[3]!!.value, "C")
            val sb = StringBuilder()
                .append(progMain).append('#')
                .append(matching.groups[1]!!.value).append('#')
                .append(matching.groups[2]!!.value).append('#')
                .append(matching.groups[3]!!.value).append('#')
                .append('n').append('\n')
            val procs = "(\\d+|A|B|C|n|R|L)(?!\$)".toRegex()
                .replace(sb.toString(), "$1,")
                .replace(",#", "\n")
            val inputData = procs.toCharArray().map { it.toLong() }
            brain.input.addAll(inputData)
            brain.runProgram()
        } else throw  Exception("Path not found")
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
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                print(grid[Pos(x, y)])
            }
            println()
        }
    }
}