package Sol19

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol19/input19.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    val program = inputStream.split(',').map { it.toLong() }
    val drone = Drone(program.toLongArray())
    val area = drone.exploreArea(50, 50)
    val res1 = area.values.sum()
    println(res1) //
}