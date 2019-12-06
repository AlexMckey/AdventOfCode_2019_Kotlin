package Sol05

fun main() {
    val fileName = "out/production/KtAOC2019/Sol05/input05.txt"
    ///val inputStream = File(fileName).bufferedReader().readLine()
    val inputStream = "1002,4,3,4,33"
    val inputProgram = inputStream.split(',').map { it.toInt() }.toIntArray()

    val computer = Computer(inputProgram.clone(), intArrayOf(1).iterator()).also(Computer::runProgram).also {
        it.output.toString().also(::println)
    } //
}