package Sol04

const val puzzleInput = "356261-846303"

fun main() {
    val from = puzzleInput.substringBefore('-')
    val to = puzzleInput.substringAfter('-')
    val range = from.toInt()..to.toInt()
    val passwords = range.asIterable().map { it.toString() }
    val part1Passwords = passwords.filter {
        val passPairs = it.zipWithNext()
        passPairs.all { (a, b) -> a <= b } &&
                passPairs.any { (a, b) -> a == b }
    }

    val res1 = part1Passwords.count()
    println(res1)

    val part2Passwords = part1Passwords.filter { pass ->
        pass.asIterable().groupingBy { it }.eachCount().any { it.value == 2 }
    }
    val res2 = part2Passwords.count()
    println(res2)
}