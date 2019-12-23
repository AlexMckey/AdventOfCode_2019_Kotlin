import Sol17.Pos

val output = "#######...#####\n" +
        "#.....#...#...#\n" +
        "#.....#...#...#\n" +
        "......#...#...#\n" +
        "......#...###.#\n" +
        "......#.....#.#\n" +
        "^########...#.#\n" +
        "......#.#...#.#\n" +
        "......#########\n" +
        "........#...#..\n" +
        "....#########..\n" +
        "....#...#......\n" +
        "....#...#......\n" +
        "....#...#......\n" +
        "....#####......"
val rows = output.split("\n")
val grid = rows.withIndex().flatMap { yv -> yv.value.mapIndexed { x, c -> Pos(x, yv.index) to c } }.toMap()
val scaffold = grid.filterValues { it != '.' }
val rp = scaffold.filterValues { "><^v".contains(it) }.keys.first()
rp
var fp = rp.near() intersect scaffold.keys
