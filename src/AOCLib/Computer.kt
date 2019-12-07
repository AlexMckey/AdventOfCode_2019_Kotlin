package AOCLib

enum class ParameterMode {
    Position, Immediate
}

enum class CompState {
    Running, Halt, InputSuspend
}

private fun Char.toParameterMode(): ParameterMode {
    return when (this) {
        '1' -> ParameterMode.Immediate
        else -> ParameterMode.Position
    }
}

class Computer(program: IntArray = intArrayOf(99)) {
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
    var state: CompState = CompState.Halt
    var input = mutableListOf<Int>()
    var output = mutableListOf<Int>()
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
                if (input.isNotEmpty()) {
                    memory[ip, 1] = input[0]
                    input.removeAt(0)
                    state = CompState.Running
                    ip += 2
                } else state = CompState.InputSuspend
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
                state = CompState.Halt
                ip += 1
            }
        }
    }

    fun runProgram() {
        state = CompState.Running
        while (state == CompState.Running) {
            doOneStep()
            step++
        }
    }
}