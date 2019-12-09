package Sol09

import AOCLib.Computer
import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol09/input09.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    //val inputStream = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99" //Ок
    //val inputStream = "1102,34915192,34915192,7,4,7,99,0" //1219070632396864
    //val inputStream = "104,1125899906842624,99" //1125899906842624
    val inputProgram = inputStream.split(',').map { it.toLong() }.toLongArray()

    val comp1 = Computer(inputProgram.clone())
    comp1.input.add(1)
    comp1.runProgram()

    val res1 = comp1.output.toString()
    println(res1) //2427443564

    val comp2 = Computer(inputProgram.clone())
    comp2.input.add(2)
    comp2.runProgram()

    val res2 = comp2.output.toString()
    println(res2) //87221
}