package Sol08

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol08/input08.txt"
    val inputStream = File(fileName).bufferedReader().readLine()

    val w = 25
    val h = 6
    val layer = inputStream.windowed(w * h, w * h).map { it.toCharArray().toList() }
        .map { it.groupingBy { it }.eachCount().toMap() }
        .minBy { it.getOrDefault('0', w * h) }

    val res1 = layer?.get('1')!! * layer.get('2')!!
    println(res1) //1088

    val li = inputStream.windowed(w * h, w * h)
        .map { it.withIndex().toList() }
        .flatten().groupBy({ i -> i.index }, { v -> v.value })
        .values.map { it.dropWhile { c -> c == '2' }.take(1) }
        .flatten().windowed(w, w).map { it.joinToString("") }
        .joinToString("\n").replace('0', ' ').replace('1', '#')
    println(li)
}