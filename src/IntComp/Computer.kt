package AOCLib

enum class ParameterMode {
    Position, Immediate, Relative
}

enum class CompState {
    Running, Halt, InputSuspend
}

private fun Char.toParameterMode(): ParameterMode {
    return when (this) {
        '1' -> ParameterMode.Immediate
        '2' -> ParameterMode.Relative
        else -> ParameterMode.Position
    }
}

class Computer(program: LongArray?) {
    var memory: LongArray = LongArray(100000) { 0 }
    operator fun get(address: Int) = memory[address]
    operator fun set(address: Int, value: Long) {
        memory[address] = value
    }

    init {
        program?.copyInto(memory)
    }

    private fun paramMode(opCode: Long, n: Int): ParameterMode =
        (opCode / 100).toString()
            .reversed().getOrElse(n - 1) { '0' }.toParameterMode()

    private operator fun LongArray.get(ip: Int, n: Int): Long =
        when (paramMode(memory[ip], n)) {
            ParameterMode.Position -> memory[memory[ip + n].toInt()]
            ParameterMode.Relative -> memory[relativeBase + memory[ip + n].toInt()]
            else -> memory[ip + n]
        }

    private operator fun LongArray.set(ip: Int, n: Int, value: Long) =
        when (paramMode(memory[ip], n)) {
            ParameterMode.Position -> memory[memory[ip + n].toInt()] = value
            ParameterMode.Relative -> memory[relativeBase + memory[ip + n].toInt()] = value
            else -> memory[ip + n] = value
        }

    private var ip: Int = 0
    private var step: Int = 0
    private var relativeBase: Int = 0
    var state = CompState.Halt
    var input = mutableListOf<Long>()
    var output = mutableListOf<Long>()
    val result: Long
        get() = memory[0]

    fun reset() {
        ip = 0
        step = 0
        relativeBase = 0
        state = CompState.Halt
    }

    private fun doOneStep() {
        when (memory[ip] % 100) {
            1L -> {
                memory[ip, 3] = memory[ip, 1] + memory[ip, 2]
                ip += 4
            }
            2L -> {
                memory[ip, 3] = memory[ip, 1] * memory[ip, 2]
                ip += 4
            }
            3L -> {
                if (input.isNotEmpty()) {
                    memory[ip, 1] = input[0]
                    input.removeAt(0)
                    state = CompState.Running
                    ip += 2
                } else state = CompState.InputSuspend
            }
            4L -> {
                output.add(memory[ip, 1])
                ip += 2
            }
            5L -> if (memory[ip, 1] != 0L) ip = memory[ip, 2].toInt() else ip += 3
            6L -> if (memory[ip, 1] == 0L) ip = memory[ip, 2].toInt() else ip += 3
            7L -> {
                memory[ip, 3] = if (memory[ip, 1] < memory[ip, 2]) 1 else 0
                ip += 4
            }
            8L -> {
                memory[ip, 3] = if (memory[ip, 1] == memory[ip, 2]) 1 else 0
                ip += 4
            }
            9L -> {
                relativeBase += memory[ip, 1].toInt()
                ip += 2
            }
            99L -> {
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