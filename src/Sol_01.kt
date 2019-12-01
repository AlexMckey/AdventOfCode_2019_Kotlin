import java.io.BufferedReader
import java.io.File
import kotlin.streams.toList

fun main() {
    val fileName = "out/production/KtAOC2019/input01.txt"
    val inputStream: BufferedReader = File(fileName).bufferedReader()
    val masses = inputStream.lines().map{it.toInt()}.toList()
    val res1 = masses.map { Math.floorDiv(it, 3) - 2 }.sum()
    println(res1) //3342351
}