import java.io.BufferedReader
import java.io.File
import kotlin.streams.toList

fun calcFuel(mass: Int): Int {
    return Math.floorDiv(mass, 3) - 2
}

fun calcRecFuel(mass: Int, acc: Int = 0): Int {
    val newMass = calcFuel(mass)
    return if (newMass > 0) {
        calcRecFuel(newMass, acc + newMass)
    } else acc
}

fun main() {
    val fileName = "out/production/KtAOC2019/input01.txt"
    val inputStream: BufferedReader = File(fileName).bufferedReader()
    val masses = inputStream.lines().map { it.toInt() }.toList()
    val res1 = masses.map { calcFuel(it) }.sum()
    println(res1) //3342351
    val res2 = masses.map { calcRecFuel(it) }.sum()
    println(res2) //5010664
}

// Not my solution, but it's clever
class Day1(private val lines: List<String>) {
    private fun fuel(weight: Int): Int = weight / 3 - 2

    private fun fuels(weight: Int): Int =
        generateSequence(fuel(weight), ::fuel)
            .takeWhile { it > 0 }
            .sum()

    fun part1(): Int = lines.sumBy { fuel(it.toInt()) }

    fun part2(): Int = lines.sumBy { fuels(it.toInt()) }
}