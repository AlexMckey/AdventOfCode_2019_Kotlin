package Sol07

import AOCLib.CompState
import AOCLib.Computer

class ThrustAmplifier(inputProgram: LongArray, inputPhases: List<Long>, withBackLink: Boolean = false) {
    var output = mutableListOf<Long>()
    private val amps: List<Computer>
    private val phases: List<Long>

    init {
        amps = arrayListOf(
            Computer(inputProgram.clone()),
            Computer(inputProgram.clone()),
            Computer(inputProgram.clone()),
            Computer(inputProgram.clone()),
            Computer(inputProgram.clone())
        )
        phases = inputPhases
        for (i in 0..3) {
            amps[i + 1].input = amps[i].output
            amps[i].output.add(phases[i + 1])
        }
        if (withBackLink) {
            amps[0].input = amps[4].output
            amps[4].output.add(phases[0])
            amps[4].output.add(0)
        } else {
            amps[0].input.add(phases[0])
            amps[0].input.add(0)
        }
    }

    fun runAmps() {
        do {
            amps.forEach { it.runProgram() }
        } while (!amps.all { it.state == CompState.Halt })
        output = amps[4].output
    }
}