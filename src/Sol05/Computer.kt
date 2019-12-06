package Sol05

class Computer(program: IntArray, inputIterator: Iterator<Int>) {
    private var ip = 0
    private var step = 0
    private val memory = program
    operator fun get(address: Int) = memory[address]
    operator fun set(address: Int, value: Int) {
        memory[address] = value
    }

    private var isHalt = false
    private val input = inputIterator
    val output: MutableList<Int> = emptyList<Int>().toMutableList()
    val result: Int
        get() = memory[0]

    private fun doOneStep(instruction: Int) {
        val opCode = instruction % 100
        val opLength = 4
        val modes = (instruction / 100).toString()
            .padStart(opLength - 1, '0')
            .reversed()
        val params = memory.sliceArray(ip + 1 until ip + opLength)
        when (opCode) {
            1 -> memory[params[2]] =
                if (modes[0] == '1') params[0] else memory[params[0]] +
                        if (modes[1] == '1') params[1] else memory[params[1]]
            2 -> memory[params[2]] =
                if (modes[0] == '1') params[0] else memory[params[0]] *
                        if (modes[1] == '1') params[1] else memory[params[1]]
            3 -> memory[params[2]] = input.next()
            4 -> output.add(if (modes[0] == '1') params[0] else memory[params[0]])
            99 -> isHalt = true
        }
        ip += opLength
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