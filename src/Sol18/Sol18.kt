package Sol18

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol18/input18.txt"
    val inputStream = File(fileName).bufferedReader().readLines()
    val grid = inputStream
        .withIndex()
        .flatMap { yv -> yv.value.mapIndexed { x, c -> Pos(x, yv.index) to c } }
        .toMap()
    val optMaze = grid.filterValues { it != '#' }
    val keys = optMaze.filterValues { it.isLetter() && it.isLowerCase() }
    val doors = optMaze.filterValues { it.isLetter() && it.isUpperCase() }
    val entry = optMaze.filterValues { it == '@' }.keys.first()

    val bfs = bfs(entry, keys.keys.toSet(), optMaze)
    println(bfs)

    val res1 = 0
    println(res1) //
    val res2 = 0
    println(res2) //
}