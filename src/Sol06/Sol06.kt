package Sol06

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol06/input06.txt"
    val inputStream = File(fileName).bufferedReader().readLines()

    val m = inputStream.map { it.substringAfter(')') to it.substringBefore(')') }
        .groupBy({ it.first }, { it.second })

    fun backTree(m: Map<String, List<String>>, cur: String, acc: List<String> = emptyList()): List<String> {
        return if (!m.containsKey(cur)) acc// + "COM"
        else m[cur]!!.map { backTree(m, it, acc + cur) }.flatten()
    }

    val allPath = m.keys.map { backTree(m, it).reversed() }

    val res1 = allPath.map { it.size }.sum()
    println(res1) //227612

    val fromYou = backTree(m, "YOU").drop(1)
    val fromSan = backTree(m, "SAN").drop(1)

    val res2 = (fromYou + fromSan - (fromSan intersect fromYou)).size
    println(res2) //454
}