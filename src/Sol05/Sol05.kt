package Sol05

import AOCLib.Computer
import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol05/input05.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    //val inputStream = "1002,4,3,4,33"
    //val inputStream = "3,0,4,0,99"
    //val inputStream = "3,9,8,9,10,9,4,9,99,-1,8"
    //val inputStream = "3,9,7,9,10,9,4,9,99,-1,8"
    //val inputStream = "3,3,1108,-1,8,3,4,3,99"
    //val inputStream = "3,3,1107,-1,8,3,4,3,99"
    val inputProgram = inputStream.split(',').map { it.toLong() }.toLongArray()

    val comp1 = Computer(inputProgram.clone())
    comp1.input.add(1)
    comp1.runProgram()

    val res1 = comp1.output.toString()

    println(res1) //7259358

    val comp2 = Computer(inputProgram.clone())
    comp2.input.add(5)
    comp2.runProgram()

    val res2 = comp2.output.toString()

    println(res2) //11826654
}