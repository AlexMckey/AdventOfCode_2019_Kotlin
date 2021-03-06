package Sol11

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol11/input11.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    val program = inputStream.split(',').map { it.toLong() }

    val robot1 = PaintingRobot(program.toLongArray())
    robot1.runRobot()
    val res1 = robot1.paintedWhite.size
    println(res1)
    robot1.draw()

    val robot2 = PaintingRobot(program.toLongArray(), 1)
    robot2.runRobot()
    robot2.draw() //ABCLFUHJ
}