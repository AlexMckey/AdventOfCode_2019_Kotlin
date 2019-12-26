import Sol18.bfs
import Sol18.bfsGraph
import Sol18.makeGrid

val input = "########################\n" +
        "#f.D.E.e.C.b.A.@.a.B.c.#\n" +
        "######################.#\n" +
        "#d.....................#\n" +
        "########################"
val inputStream = input.split("\n")

val grid = makeGrid(inputStream).toMutableMap()
val attractions = grid.filterValues { it != '.' && it != '#' }
    .map { it.value to it.key }.toMap()
val keys = attractions.filterKeys { it.isLowerCase() && it.isLetter() }
keys
val entries = "@1234".associateWith { attractions[it] }.filterValues { it != null }.toMap()
entries
val keysGrid = (keys + entries).map { me -> me.key to bfs(grid, me.value!!) }.toMap()
keysGrid
keys + entries
val keyGraph = (keys + entries).map { me -> me.key to bfsGraph(grid, me.value!!) }.toMap()
keyGraph
val s = setOf('A')
s - 'a'.toUpperCase()