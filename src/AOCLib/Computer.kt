package AOCLib

enum class ParameterMode {
    Position, Immediate
}

private fun Char.toParameterMode(): ParameterMode {
    return when (this) {
        '1' -> ParameterMode.Immediate
        else -> ParameterMode.Position
    }
}

class Computer(program: IntArray = intArrayOf(99), private val input: Iterator<Int>? = null) {
    private var memory = program
    operator fun get(address: Int) = memory[address]
    operator fun set(address: Int, value: Int) {
        memory[address] = value
    }

    private fun paramMode(opCode: Int, n: Int): ParameterMode =
        (opCode / 100).toString()
            .reversed().getOrElse(n - 1) { '0' }.toParameterMode()

    private operator fun IntArray.get(ip: Int, n: Int): Int =
        if (paramMode(memory[ip], n) == ParameterMode.Position) {
            memory[memory[ip + n]]
        } else {
            memory[ip + n]
        }

    private operator fun IntArray.set(ip: Int, n: Int, value: Int) =
        if (paramMode(memory[ip], n) == ParameterMode.Position) {
            memory[memory[ip + n]] = value
        } else {
            memory[ip + n] = value
        }

    private var ip: Int = 0
    private var step: Int = 0
    private var isHalt: Boolean = false
    var output: MutableCollection<Int> = mutableListOf()
    val result: Int
        get() = memory[0]

    private fun doOneStep() {
        when (memory[ip] % 100) {
            1 -> {
                memory[ip, 3] = memory[ip, 1] + memory[ip, 2]
                ip += 4
            }
            2 -> {
                memory[ip, 3] = memory[ip, 1] * memory[ip, 2]
                ip += 4
            }
            3 -> {
                memory[ip, 1] = input?.next()!!
                ip += 2
            }
            4 -> {
                output.add(memory[ip, 1])
                ip += 2
            }
            5 -> if (memory[ip, 1] != 0) ip = memory[ip, 2] else ip += 3
            6 -> if (memory[ip, 1] == 0) ip = memory[ip, 2] else ip += 3
            7 -> {
                memory[ip, 3] = if (memory[ip, 1] < memory[ip, 2]) 1 else 0
                ip += 4
            }
            8 -> {
                memory[ip, 3] = if (memory[ip, 1] == memory[ip, 2]) 1 else 0
                ip += 4
            }
            99 -> {
                isHalt = true
                ip += 1
            }
        }
        step++
    }

    fun runProgram() {
        step = 0
        ip = 0
        while (!isHalt) doOneStep()
    }
}