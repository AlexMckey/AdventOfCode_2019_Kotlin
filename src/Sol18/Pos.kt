package Sol18

import kotlin.math.abs

data class Pos(val x: Int, val y: Int) {
    override fun toString(): String = "[x:$x,y:$y]"

    operator fun plus(other: Pos) = Pos(other.x + x, other.y + y)
    operator fun plus(other: Pair<Int, Int>) = Pos(other.first + x, other.second + y)
    operator fun minus(other: Pos) = Pos(other.x - x, other.y - y)

    fun manhattanDistance(p2: Pos = Zero): Int {
        return abs(this.x - p2.x) + abs(this.y - p2.y)
    }

    fun near(): List<Pos> = listOf(this + toLeft, this + toRight, this + toUp, this + toDown)

    companion object {
        val Zero = Pos(0, 0)
        val toLeft = Pos(-1, 0)
        val toRight = Pos(1, 0)
        val toUp = Pos(0, 1)
        val toDown = Pos(0, -1)
    }
}