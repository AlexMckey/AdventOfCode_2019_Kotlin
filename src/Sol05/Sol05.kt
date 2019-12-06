package Sol05

import AOCLib.Computer
import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol05/input05.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    //val inputStream = "1002,4,3,4,33"
    //val inputStream = "3,0,4,0,99"
    val inputProgram = inputStream.split(',').map { it.toInt() }.toIntArray()

    val computer = Computer(inputProgram.clone(), listOf(1).iterator())
    computer.runProgram()

    val res1 = computer.output.toString()

    println(res1) //
}