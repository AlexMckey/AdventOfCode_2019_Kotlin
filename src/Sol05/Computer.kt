package Sol05

enum class ParameterMode {
    Position, Immediate
}

data class Instruction(val length: Int, val action: (List<Int>) -> Unit)

private fun Char.toParameterMode(): ParameterMode {
    return when (this) {
        '1' -> ParameterMode.Immediate
        else -> ParameterMode.Position
    }
}

class Computer(program: IntArray = intArrayOf(99), input: Int = 0) {
    private var memory = intArrayOf(99)
    fun get(address: Int) = memory[address]
    fun set(address: Int, value: Int) {
        memory[address] = value
    }

    private var ip: Int = 0
    private var step: Int = 0
    private var isHalt: Boolean = false
    var output: List<Int> = emptyList()
    private var io: Int = input
        set(value: Int) {
            field = value
            output += field
        }

    val result: Int
        get() = memory[0]

    init {
        memory = program
        step = 0
        ip = 0
        io = input
        isHalt = false
    }

    private val instructions = hashMapOf(
        1 to Instruction(3) { l -> memory[l[2]] = l[0] + l[1] },
        2 to Instruction(3) { l -> memory[l[2]] = l[0] * l[1] },
        3 to Instruction(1) { l -> memory[l[0]] = io },
        4 to Instruction(1) { l -> io = memory[l[0]] },
        99 to Instruction(0) { isHalt = true }
    )

    private fun doOneStep(instruction: Int) {
        val opCode = instruction % 100
        val opParamsCount = instructions[opCode]!!.length
        val modes = (instruction / 100).toString()
            .padStart(opParamsCount, '0')
            .reversed()
            .map { it.toParameterMode() }
        val params = memory
            .slice((ip + 1)..(ip + opParamsCount))
            .zip(modes).map { (param, mode) ->
                if (mode == ParameterMode.Immediate) param
                else memory[param]
            }
        instructions[opCode]!!.action(params)
        ip += opParamsCount + 1
    }

    fun runProgram() {
        step = 0
        ip = 0
        while (!isHalt) {
            doOneStep(memory[ip])
            step++
        }
    }
}