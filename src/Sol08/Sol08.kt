package Sol08

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol08/input08.txt"
    val inputStream = File(fileName).bufferedReader().readLine()

    val w = 25
    val h = 6
    val res1 = inputStream.chunked(w * h)
        .map { it.groupingBy { it }.eachCount() }
        .minBy { it.getOrDefault('0', w * h) }
        .let { it?.get('1')!! * it['2']!! }

    println(res1) //1088

    inputStream.chunked(w * h)
        .flatMap { it.withIndex() }
        .groupBy({ i -> i.index }, { v -> v.value })
        .values.asSequence()
        .map { it.dropWhile { c -> c == '2' }.take(1) }
        .flatten().chunked(w).map { it.joinToString("") }
        .forEach { println(it.replace('0', ' ').replace('1', '#')) }
//    #     ##  #   ##  # ###
//    #    #  # #   ##  # #  #
//    #    #     # # #### ###
//    #    # ##   #  #  # #  #
//    #    #  #   #  #  # #  #
//    ####  ###   #  #  # ###
}