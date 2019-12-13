package Sol13

import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol13/input13.txt"
    val inputStream = File(fileName).bufferedReader().readLine()
    //val inputStream = "1,2,3,6,5,4"
    val program = inputStream.split(',').map { it.toLong() }

    //val game = ArkanoidGame(longArrayOf(99L))
    //game.runGame(program)
    val gameBoard = ArkanoidGame(program.toLongArray())
    gameBoard.runGame()
    gameBoard.displayGame()
    val res1 = gameBoard.blockTiles().size
    println(res1) //398

    val game = ArkanoidGame(program.toLongArray(), 2L, true)

    game.runGame()
    val res2 = game.score
    println(res2) //19447
}