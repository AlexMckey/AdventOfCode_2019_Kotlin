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
    str.mapIndexed { x, c -> Pos(x, y) to c }
}
    .flatten().filter { it.second == '#' }
    .map { it.first }
asteriods
val a = asteriods.find { it == Pos(5, 8) }!!
a
fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
asteriods.filterNot { a == it }.groupingBy { it ->
    val g = gcd(it.x - a.x, it.y - a.y)
    Pos((it.x / g), (it.y / g))
}.eachCount().size