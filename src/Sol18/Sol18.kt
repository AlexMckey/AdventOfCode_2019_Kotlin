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
    println(keys)
    val doors = optMaze.filterValues { it.isLetter() && it.isUpperCase() }
    val entry = optMaze.filterValues { it == '@' }.keys.first()
    val keysWithEntry = keys + (entry to optMaze[entry])

    var bfsKeys = keysWithEntry.keys.map { kp ->
        oldbfs(kp, keysWithEntry.keys.toSet() - kp, optMaze)
            .filterKeys { keysWithEntry.keys.contains(it) && it != kp }
            .map { pv -> keysWithEntry[kp] to (keysWithEntry[pv.key] to pv.value.second) }
            .groupBy({ it.first }, { it.second })
    }
    println(bfsKeys)
    val fk1 = bfsKeys.find { it.keys.contains('@') }!!.map { it.value.minBy { it.second } }.first()
    val visited = mutableSetOf('@')
    println(fk1)
    bfsKeys =
        bfsKeys.filterNot { visited.containsAll(it.keys) }//.map { me -> me.map { list -> list.key to (list.value.filterNot { pair -> visited.contains(pair.first) }) } }
    println(bfsKeys)
    val fk2 = bfsKeys.find { it.keys.contains(fk1!!.first) }!!.map { it.value.minBy { it.second } }.first()
    println(fk2)

    val res1 = 0
    println(res1) //
    val res2 = 0
    println(res2) //
}