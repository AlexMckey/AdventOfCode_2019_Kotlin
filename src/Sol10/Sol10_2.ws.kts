fun gcd(a: Long, b: Long): Long = if (b == 0.toLong()) a else gcd(b, a % b)
gcd(2, 4)

data class Pos(val x: Int, val y: Int) {
    fun angle(other: Pos) =
        if (other.y - this.y == 0)
            0.0
        else 1.0 * (other.x - this.x) / (other.y - this.y)
}

val testData = "......#.#.\n" +
        "#..#.#....\n" +
        "..#######.\n" +
        ".#.#.###..\n" +
        ".#..#.....\n" +
        "..#....#.#\n" +
        "#..#....#.\n" +
        ".##.#..###\n" +
        "##...#..#.\n" +
        ".#....####"
val s = testData.split('\n')
val asteriods = s.mapIndexed { y, str ->
    str.mapIndexed { x, c -> Sol10.Pos(x, y) to c }
}
    .flatten().filter { it.second == '#' }
    .map { it.first }
asteriods
val a58 = asteriods.find { it == Sol10.Pos(5, 8) }!!
a58
val parts = asteriods.partition { it.x == a58.x }
parts.first.size
parts.second

val detected = asteriods.map { a ->
    val ps = asteriods.partition { it.x == a.x }

}
//val detected = asteriods
//    .map { a -> a to asteriods
//        .filterNot { it == a }
//        .groupBy { p -> a.angle(p).toString() }}