import java.io.BufferedReader
import java.io.File
import kotlin.streams.toList

fun main() {
    val fileName = "out/production/KtAOC2019/input01.txt"
    val inputStream: BufferedReader = File(fileName).bufferedReader()
    val res1 = inputStream.lines().map { Math.floorDiv(it.toInt(), 3) - 2 }.toList().sum()
    println(res1) //3342351
}