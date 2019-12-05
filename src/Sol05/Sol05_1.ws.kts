enum class ParameterMode {
    Position, Immediate
}

fun Int.toParameterMode(): ParameterMode {
    return when (this) {
        1 -> ParameterMode.Immediate
        else -> ParameterMode.Position
    }
}

fun Char.toParameterMode(): ParameterMode {
    return when (this) {
        '1' -> ParameterMode.Immediate
        else -> ParameterMode.Position
    }
}

val instruction = 1002
val opCode = instruction % 100
opCode
val parameterModes = (instruction / 100)
    .toString().reversed().toCharArray().map { it.toInt().toParameterMode() }
parameterModes
private val instructionsLength = hashMapOf<Int, Int>(
    1 to 3, 2 to 3, 3 to 1, 4 to 1, 99 to 0
).withDefault { 0 }
val ilen = instructionsLength[opCode]!!
val isp = (instruction / 100)
isp
val modes = isp.toString().padStart(ilen, '0').reversed()
modes
val pmc = modes.map { it.toParameterMode() }.toList()
pmc
val pmi = modes.map { it.toString().toInt().toParameterMode() }.toList()
pmi