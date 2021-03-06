package Sol18

fun main() {
    //val fileName = "out/production/KtAOC2019/Sol17/input17.txt"
    //val inputStream = File(fileName).bufferedReader().readLines()
    val input = "#################\n" +
            "#i.G..c...e..H.p#\n" +
            "########.########\n" +
            "#j.A..b...f..D.o#\n" +
            "########@########\n" +
            "#k.E..a...g..B.n#\n" +
            "########.########\n" +
            "#l.F..d...h..C.m#\n" +
            "#################"
    val inputStream = input.split("\n")

    val grid = makeGrid(inputStream)
    val attractions = grid.filterValues { it != '.' && it != '#' }
        .map { it.value to it.key }.toMap()
    val keys = attractions.filterKeys { it.isLowerCase() && it.isLetter() }
    println(keys)
    val entries = "@1234".associateWith { attractions[it] }.filterValues { it != null }.toMap()
    println(entries)
    val keysGraph = (keys + entries).map { me -> me.key to bfsGraph(grid, me.value!!) }.toMap()
    println(keysGraph)

    val d = dijkstra(keysGraph, entries.keys.first())
    println(d)

    val res1 = d.values.last().first
    println(res1) //

    val res2 = 0
    println(res2) //
}

fun makeGrid(input: List<String>): Map<Pos, Char> =
    input.withIndex()
        .flatMap { yv ->
            yv.value
                .mapIndexed { x, c -> Pos(x, yv.index) to c }
        }
        .toMap()