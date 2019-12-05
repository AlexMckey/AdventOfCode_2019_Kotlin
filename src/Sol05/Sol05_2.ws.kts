data class Instruction(val length: Int, val action: (List<Int>) -> Unit)
enum class ParameterMode { Position, Immediate }

var io = 0
private val ins = hashMapOf(
    1 to Instruction(3) { l -> memory[l[2]] = l[0] + l[1] },
    2 to Instruction(3) { l -> memory[l[2]] = l[0] * l[1] },
    3 to Instruction(1) { l -> memory[l[0]] = io },
    4 to Instruction(1) { l -> io = memory[l[0]] },
    99 to Instruction(0) { }
)
val memory = arrayOf(1, 2, 3, 4, 5, 0)
val ip = 0
val opCode = 1
val modes = listOf(
    ParameterMode.Position,
    ParameterMode.Immediate, ParameterMode.Position
)
val params = memory
    .slice((ip + 1)..(ip + ins[opCode]!!.length))
    .zip(modes).map { (param, mode) ->
        if (mode == ParameterMode.Immediate) param
        else memory[param]
    }
ins[opCode]!!.action(params)
memory.toList()