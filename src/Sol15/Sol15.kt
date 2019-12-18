package Sol15

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol15/input15.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    val program = inputStream.split(',').map { it.toLong() }
    val droid1 = RD(program.toLongArray())
    droid1.runGame(autoExplorer = true)
    val res1 = droid1.oxygenPath
    println(res1!!.size) //300
    val droid2 = RD(program.toLongArray())
    droid2.runGame(autoExplorer = true, findOxygen = false)
    val res2 = droid2.minutesToFillOxygen()
    println(res2) //312
}