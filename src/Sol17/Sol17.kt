package Sol17

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol17/input17.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    val program = inputStream.split(',').map { it.toLong() }
    val camera = ASCIIprogram(program.toLongArray())
    camera.getCameraView(true)
    val res1 = camera.aligmentSum
    println(res1) //6520
}