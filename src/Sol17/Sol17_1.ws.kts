import Sol17.Pos
import Sol17.SuffixTree

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
val prog =
    "L,12,R,8,L,10,L,10,R,6,R,4,R,12,R,12,R,12,L,8,L,10,L,10,R,6,R,4,R,12,L,10,R,8,R,4,L,10,R,6,R,4,R,12,R,12,R,8,L,10,L,10,L,10,R,8,R,4,L,10,R,6,R,4,R,12,L,10,R,8,R,4,L,10"
val sft = SuffixTree(prog)
