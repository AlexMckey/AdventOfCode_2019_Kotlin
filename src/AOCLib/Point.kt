package AOCLib

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.sqrt

data class Point(val x: Int, val y: Int) {
    override fun toString(): String = "[x:$x,y:$y]"
    operator fun plus(other: Point) = Point(other.x + x, other.y + y)
    operator fun plus(other: Pair<Int, Int>) = Point(other.first + x, other.second + y)
    operator fun minus(other: Point) = Point(other.x - x, other.y - y)
    fun angle(other: Point) = atan2((other - this).y.toDouble(), (other - this).x.toDouble()) * 180 / PI
    fun distance(other: Point): Double {
        val delta = other - this
        return sqrt(1.0 * delta.x * delta.x + delta.y * delta.y)
    }

    fun manhattanDistance(p2: Point = Zero): Int {
        return abs(this.x - p2.y) + abs(this.y - p2.y)
    }

    fun near(): List<Point> = listOf(this + toLeft, this + toRight, this + toUp, this + toDown)

    companion object {
        val Zero = Point(0, 0)
        val toLeft = Point(-1, 0)
        val toRight = Point(1, 0)
        val toUp = Point(0, 1)
        val toDown = Point(0, -1)
        fun incXPoint(): (Point) -> Point = { p -> Point(p.x + 1, p.y) }
        fun decXPoint(): (Point) -> Point = { p -> Point(p.x - 1, p.y) }
        fun incYPoint(): (Point) -> Point = { p -> Point(p.x, p.y + 1) }
        fun decYPoint(): (Point) -> Point = { p -> Point(p.x, p.y - 1) }
    }
}

