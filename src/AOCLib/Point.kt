package AOCLib

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {
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