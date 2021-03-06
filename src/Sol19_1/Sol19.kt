package Sol19_1

import java.io.File

class Day19(lines: List<String>) {
    private val ints: List<Long> =
        lines.first().splitToSequence(",").map { it.toLong() }.toList()

    private fun f(x: Long, y: Long): Boolean =
        Intcode(ints.toMutableList()).runBlocking(listOf(x, y)).single() != 0L

    fun part1(): Int = (0L..49L).sumBy { x -> (0L..49L).count { y -> f(x, y) } }

    fun part2(): Long {
        var x = 0L
        var y = 99L
        while (true) {
            while (!f(x, y)) {
                x++
            }
            if (f(x + 99, y - 99)) {
                return 10000 * x + y - 99
            }
            y++
        }
    }
}

fun main() {
    val fileName = "out/production/KtAOC2019/Sol19/input19.txt"
    val inputStream = File(fileName).bufferedReader().readLines()
    val day19 = Day19(inputStream)
    println(day19.part1())
    println(day19.part2())
}