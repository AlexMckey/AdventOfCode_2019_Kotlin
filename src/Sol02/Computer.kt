package Sol02

import java.io.File

class Computer(program: IntArray?) {
    var memory = intArrayOf(99)
    private var ip: Int = 0
    private var step: Int = 0
    private val opers = hashMapOf<Int, (Int, Int) -> Int>(
        1 to { a, b -> a + b },
        2 to { a, b -> a * b },
        99 to { _, _ -> 0 }
    )
    val res: Int
        get() = memory[0]

    init {
        memory = program ?: intArrayOf(99)
        step = 0
        ip = 0
    }

    private fun doOneStep(opCode: Int): Int {
        val f = memory[memory[ip + 1]]
        val s = memory[memory[ip + 2]]
        return opers[opCode]?.invoke(f, s) ?: throw Exception("Такой команды не существует: $opCode")
    }

    fun runProgram() {
        step = 0
        ip = 0
        while (ip < memory.size - 4
            && opers.containsKey(memory[ip])
            && (memory[ip]) != 99
        ) {
            if (memory[ip + 1] < memory.size - 1
                && memory[ip + 2] < memory.size - 2
                && memory[ip + 3] < memory.size - 3
                && ip < memory.size
            ) {
                val res = doOneStep(memory[ip])
                memory[memory[ip + 3]] = res
            }
            ip += 4
            step++
        }
    }
}

fun main() {
    val fileName = "out/production/KtAOC2019/Sol02/input02.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    //val inputStream = "1,1,1,4,99,5,6,0,99"
    val inputProgram = inputStream.split(',').map { it.toInt() }.toIntArray()

    val computer = Computer(inputProgram.clone())
    computer.memory[1] = 12
    computer.memory[2] = 2
    computer.runProgram()
    val res1 = computer.res
    println(res1) //5434663

    val res2 = (1..99).flatMap { n -> (1..99).map { n to it } }
        .parallelStream().filter { (n, v) ->
            val prog = inputProgram.clone()
            val cmp = Computer(prog)
            cmp.memory[1] = n
            cmp.memory[2] = v
            cmp.runProgram()
            //println("(noun:$n, verb:$v) = ${cmp.res}")
            cmp.res == 19690720
        }.findFirst().orElse(0 to 0)
        .let { (n, v) -> n * 100 + v }
    println(res2) //4559
}
