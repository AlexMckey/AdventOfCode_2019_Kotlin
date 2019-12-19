package Sol16

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val fileName = "out/production/KtAOC2019/Sol16/input16.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    val phase = listOf(0, 1, 0, -1)
    fun <T> Sequence<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }
    fun <T> Sequence<T>.repeat(n: Int) = sequence { repeat(n) { yieldAll(this@repeat) } }
    fun phases(phase: List<Int>, n: Int) = phase.asSequence().flatMap { sequenceOf(it).repeat(n) }.repeat().drop(1)
    fun step(seq: Sequence<Int>, phase: List<Int>) = sequence {
        val cnt = seq.count()
        for (i in 1..cnt) yield(seq.zip(phases(phase, i))
            .map { (it.first * it.second % 10) }
            .sumBy { v -> v % 10 }.absoluteValue % 10
        )
    }

    fun rounds(nums: String, phase: List<Int>, repeats: Int): List<Int> {
        val cnt = nums.count()
        var res: List<Int> = nums.map { it - '0' }
        repeat(repeats) {
            res = step(res.asSequence(), phase).take(cnt).toList()
        }
        return res
    }

    val res1 = rounds(inputStream, phase, 100).take(8)
    println(res1.joinToString("")) //85726502

    val input = inputStream.map { it - '0' }
    val offset = inputStream.slice(0 until 7).toInt()
    val seq = (input.drop(offset % input.size) + input.take(offset % input.size))
        .asSequence().repeat()
        .take(input.size * 10_000 - offset).toList().toIntArray()
    repeat(100) {
        seq.indices.reversed().fold(0) { acc, i ->
            ((acc + seq[i]) % 10).absoluteValue.also { seq[i] = it }
        }
    }
    println(seq.take(8).joinToString("")) //92768399
}