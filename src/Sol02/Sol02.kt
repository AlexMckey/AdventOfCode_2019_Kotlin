package Sol02

import AOCLib.Computer
import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol02/input02.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    //val inputStream = "1,1,1,4,99,5,6,0,99"
    val inputProgram = inputStream.split(',').map { it.toInt() }.toIntArray()

    val computer = Computer(inputProgram.clone())
    computer[1] = 12
    computer[2] = 2
    computer.runProgram()
    val res1 = computer.result
    println(res1) //5434663

    val res2 = (1..99).flatMap { n -> (1..99).map { n to it } }
        .parallelStream().filter { (n, v) ->
            val prog = inputProgram.clone()
            val cmp = Computer(prog)
            cmp[1] = n
            cmp[2] = v
            cmp.runProgram()
            //println("(noun:$n, verb:$v) = ${cmp.res}")
            cmp.result == 19690720
        }.findAny().orElse(0 to 0)
        .let { (n, v) -> n * 100 + v }
    println(res2) //4559
}