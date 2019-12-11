package AOCLib

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.sqrt

data class Point(val x: Int, val y: Int) {
    override fun toString(): String = "[x:$x,y:$y]"
    operator fun plus(other: Point) = Point(other.x + x, other.y + y)
    operator fun minus(other: Point) = Point(other.x - x, other.y - y)
    fun angle(other: Point) = atan2((other - this).y.toDouble(), (other - this).x.toDouble()) * 180 / PI
    fun distance(other: Point): Double {
        val delta = other - this
        return sqrt(1.0 * delta.x * delta.x + delta.y * delta.y)
    }

    companion object {
        fun incXPoint(): (Point) -> Point = { p -> Point(p.x + 1, p.y) }
        fun decXPoint(): (Point) -> Point = { p -> Point(p.x - 1, p.y) }
        fun incYPoint(): (Point) -> Point = { p -> Point(p.x, p.y + 1) }
        fun decYPoint(): (Point) -> Point = { p -> Point(p.x, p.y - 1) }
    }
}

fun manhattanDistance(p1: Point, p2: Point = Point(0, 0)): Int {
    return abs(p1.x - p2.y) + abs(p1.y - p2.y)
}