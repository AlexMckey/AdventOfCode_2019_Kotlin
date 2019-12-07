package Sol07

import AOCLib.Computer
import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol07/input07.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    //val inputStream = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0" //43210
    //val phase = "4,3,2,1,0"
    //val inputStream = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0" //54321
    //val phase = "0,1,2,3,4"
    //val inputStream = "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0" //65210
    //val phase = "1,0,4,3,2"
    val inputProgram = inputStream.split(',').map { it.toInt() }.toIntArray()

    val comp1 = Computer(inputProgram.clone(), listOf(1).iterator())
    comp1.runProgram()

    val res1 = comp1.output.toString()

    println(res1) //7259358

    val comp2 = Computer(inputProgram.clone(), listOf(5).iterator())
    comp2.runProgram()

    val res2 = comp2.output.toString()

    println(res2) //11826654
}