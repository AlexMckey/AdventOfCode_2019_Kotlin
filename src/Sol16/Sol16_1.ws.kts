import kotlin.math.absoluteValue

val input = "12345678"
val phase = listOf(0, 1, 0, -1)
val num = input.map { it - '0' }
val nums = input.map { it - '0' }.asSequence()
nums
val seq = generateSequence { phase }.flatten()
listOf(0, 1, 0, -1).flatMap { v -> List(2) { v } }.drop(1)
phase.asSequence().take(10).toList()
fun <T> Sequence<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }
fun <T> Sequence<T>.repeat(n: Int) = sequence { repeat(n) { yieldAll(this@repeat) } }
phase.asSequence().repeat().take(10).toList()
phase.flatMap { v -> List(2) { v } }.asSequence().repeat().drop(1).take(10).toList()
phase.flatMap { v -> List(3) { v } }.asSequence().repeat().drop(1).take(10).toList()
phase.asSequence().flatMap { sequenceOf(it).repeat().take(3) }.repeat().drop(1).take(10).toList()
fun phases(phase: List<Int>, n: Int) = phase.asSequence().flatMap { sequenceOf(it).repeat(n) }.repeat().drop(1)
phases(phase, 4).take(20).toList()
nums.zip(phases(phase, 1))
    .map { (it.first * it.second % 10) }
    .sumBy { it % 10 }.absoluteValue
nums.zip(phases(phase, 2))
    .map { (it.first * it.second % 10) }
    .sumBy { it % 10 }.absoluteValue
nums.withIndex().repeat(nums.count()).take(10).toList()
fun step(seq: Sequence<Int>, phase: List<Int>) = sequence {
    val cnt = seq.count()
    for (i in 1..cnt) yield(seq.zip(phases(phase, i))
        .map { (it.first * it.second % 10) }
        .sumBy { v -> v % 10 }.absoluteValue % 10
    )
}

val step1 = step(nums, phase).take(input.count()).toList()
step1
val step2 = step(step1.asSequence(), phase).take(input.count()).toList()
step2
fun rounds(nums: String, phase: List<Int>, repeats: Int): List<Int> {
    val cnt = nums.count()
    var res: List<Int> = nums.map { it - '0' }
    repeat(repeats) {
        res = step(res.asSequence(), phase).take(cnt).toList()
    }
    return res
}
rounds(input, phase, 1).toList()
rounds(input, phase, 2).toList()
rounds(input, phase, 3).toList()
rounds(input, phase, 4).toList()
rounds("80871224585914546619083218645595", phase, 100).take(8)
rounds("19617804207202209144916044189917", phase, 100).take(8)
rounds("69317163492948606335995924319873", phase, 100).take(8)