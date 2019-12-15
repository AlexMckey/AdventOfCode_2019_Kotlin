package Sol15

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol15/input15.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    val program = inputStream.split(',').map { it.toLong() }
    val droid = RepairDroid(program.toLongArray())
    droid.runGame(autoExplorer = true, debug = true)
    val res1 = "DONE!!!"
    println(res1) //
}