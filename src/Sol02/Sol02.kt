package Sol02

import AOCLib.Computer
import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol02/input02.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    //val inputStream = "1,1,1,4,99,5,6,0,99"
    val inputProgram = inputStream.split(',').map { it.toLong() }.toLongArray()

    val computer = Computer(inputProgram.clone())
    computer[1] = 12L
    computer[2] = 2L
    computer.runProgram()
    val res1 = computer.result
    println(res1) //5434663

    val res2 = (1L..99L).flatMap { n -> (1L..99L).map { n to it } }
        .parallelStream().filter { (n, v) ->
            val prog = inputProgram.clone()
            val cmp = Computer(prog)
            cmp[1] = n
            cmp[2] = v
            cmp.runProgram()
            //println("(noun:$n, verb:$v) = ${cmp.res}")
            cmp.result == 19690720L
        }.findAny().orElse(0L to 0)
        .let { (n, v) -> n * 100 + v }
    println(res2) //4559
}