package Sol10

import AOCLib.Rational

private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

data class Pos(val x: Int, val y: Int) {
    fun angle(other: Pos): Rational {
        return Rational((other.y - this.y).toBigInteger(), (other.x - this.x).toBigInteger())
    }
}

fun main() {
    val fileName = "out/production/KtAOC2019/Sol10/input10.txt"
//   val testData = (".#..#\n" +
//                    ".....\n" +
//                    "#####\n" +
//                    "....#\n" +
//                    "...##")
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
    val inputStream = testData.split('\n')
    //val inputStream = File(fileName).bufferedReader().readLines()

    val asteriods = inputStream.mapIndexed { y, str ->
        str.mapIndexed { x, c -> Pos(x, y) to c }
    }
        .flatten().filter { it.second == '#' }
        .map { it.first }
    val detected =
        asteriods.map { a -> a to asteriods.filterNot { it == a }.groupingBy { p -> a.angle(p) }.eachCount().size }
    //val res1 = detected.maxBy { it.second }
    //println(res1)
    println(asteriods.find { it == Pos(5, 8) })
    println(asteriods.toList())
    println(detected.toList())
}