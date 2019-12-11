package Sol11

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol11/input11.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    val program = inputStream.split(',').map { it.toLong() }

    val robot = PaintingRobot(program.toLongArray())
    robot.runRobot()
    val res1 = robot.panelPaintedWhite.size
    println(res1)
    println(robot.panelPaintedWhite)
    println(robot.panelPaintedBlack)
    println(robot.colorCnt)
}