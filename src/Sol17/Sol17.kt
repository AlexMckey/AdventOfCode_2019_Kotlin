package Sol17

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol17/input17.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    val program = inputStream.split(',').map { it.toLong() }
    val robot = ASCIIprogram(program.toLongArray())
    robot.getCameraView(true)
    val res1 = robot.alignmentSum
    println(res1) //6520
    robot.runRobotProgram()
    val res2 = robot.dustCount
    println(res2) //1071369
}