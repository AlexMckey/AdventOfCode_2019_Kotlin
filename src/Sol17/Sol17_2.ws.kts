import Sol17.Pos

val out = "#######...#####\n" +
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
        "....#####......".split("\n")
val grid = out
    .split("\n")
    .withIndex()
    .flatMap { yv -> yv.value.mapIndexed { x, c -> Pos(x, yv.index) to c } }
    .toMap()
grid
val robotPos = grid.filterValues { "v^><".contains(it) }.keys.first()
robotPos
val scaffolds = grid.filterValues { "#v^><".contains(it) }
val ns = robotPos.near() intersect scaffolds.keys
val curDir = when (scaffolds[robotPos]) {
    '^' -> "Up"
    '>' -> "Left"
    '<' -> "Right"
    'v' -> "Down"
    else -> ""
}
curDir