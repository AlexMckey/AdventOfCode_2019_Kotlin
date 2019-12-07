package Sol07

import AOCLib.CompState
import AOCLib.Computer
import AOCLib.permute
import java.io.File

class ThrustAmplifier(inputProgram: IntArray, inputPhases: List<Int>, withBackLink: Boolean = false) {
    var output = mutableListOf<Int>()
    private val amps: List<Computer>
    private val phases: List<Int>

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

fun main() {
    val fileName = "out/production/KtAOC2019/Sol07/input07.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    //val inputStream = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"
    //val phaseStr = "4,3,2,1,0" //43210
    //val inputStream = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0"
    //val phaseStr = "0,1,2,3,4" //54321
    //val inputStream = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"
    //val phaseStr = "1,0,4,3,2" //65210
    //val inputStream = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"
    //val phaseStr = "9,8,7,6,5" //139629729
    //val inputStream = "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10"
    //val phaseStr = "9,7,8,5,6" //18216
    val inputProgram = inputStream.split(',').map { it.toInt() }.toIntArray()
    //val inputPhases = phaseStr.split(',').map { it.toInt() }

    val part1phases = listOf(0, 1, 2, 3, 4).permute().shuffled()
    val part1maxOutput = part1phases.map {
        val thrustAmp = ThrustAmplifier(inputProgram, it)
        thrustAmp.runAmps()
        it to thrustAmp.output.joinToString("").toInt()
    }.maxBy { it.second }

    val res1 = part1maxOutput.toString()
    println(res1) //3, 1, 4, 2, 0 => 422858

//    val thrustAmpWithBackLink = ThrustAmplifier(inputProgram,inputPhases,true)
//    thrustAmpWithBackLink.runAmps()

    val part2phases = listOf(5, 6, 7, 8, 9).permute().shuffled()
    val part2maxOutput = part2phases.map {
        val thrustAmpWithBackLink = ThrustAmplifier(inputProgram, it, true)
        thrustAmpWithBackLink.runAmps()
        it to thrustAmpWithBackLink.output.joinToString("").toInt()
    }.maxBy { it.second }

    val res2 = part2maxOutput.toString()
    println(res2) //[7, 8, 9, 6, 5] => 14897241
}