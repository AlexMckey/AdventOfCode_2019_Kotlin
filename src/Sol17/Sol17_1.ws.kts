import Sol17.Pos

val output = "..#..........\n" +
        "..#..........\n" +
        "#######...###\n" +
        "#.#...#...#.#\n" +
        "#############\n" +
        "..#...#...#..\n" +
        "..#####...^.."
val rows = output.split("\n")
val grid = rows.withIndex().flatMap { yv -> yv.value.mapIndexed { x, c -> Pos(x, yv.index) to c } }.toMap()
val crosses = grid.filterValues { c -> c == '#' }.filterKeys { p -> p.near().all { n -> grid[n] == '#' } }
crosses.map { it.key.x * it.key.y }.sum()