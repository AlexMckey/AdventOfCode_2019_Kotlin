package Sol03

import java.io.File
import kotlin.math.abs
import kotlin.streams.toList

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

fun parsePart(part: String): Pair<String, Int> {
    return part.let { it.substring(0, 1) to it.substring(1).toInt() }
}

fun <S, T> List<T>.scanM(acc: S, op: (S, T) -> S): List<S> {
    var accumulator: S = acc
    return this.mapIndexed { _, x ->
        accumulator = op(accumulator, x)
        accumulator
    }
}

fun <S, T> List<T>.scan(acc: S, op: (S, T) -> S): List<S> {
    fun tempFun(accumulator: S, list: List<T>, op: (S, T) -> S): List<S> {
        val result = op(accumulator, list.first())
        val cdr = list.drop(1)
        if (cdr.isNotEmpty()) {
            return listOf(result) + tempFun(result, cdr, op)
        }
        return listOf(result)
    }
    return listOf(acc) + tempFun(acc, this, op)
}

fun partPoints(dir: String, cnt: Int): List<(Point) -> Point> {
    return List(cnt) {
        when (dir) {
            "L" -> Point.decXPoint()
            "R" -> Point.incXPoint()
            "U" -> Point.incYPoint()
            "D" -> Point.decYPoint()
            else -> { p: Point -> p }
        }
    }
}

const val fileName = "out/production/KtAOC2019/Sol03/input03.txt"

fun main() {
    val inputList = File(fileName).bufferedReader().lines().toList()
    //val inputList = "R75,D30,R83,U83,L12,D49,R71,U7,L72\nU62,R66,U55,R34,D71,R55,D58,R83".split('\n')
    //val inputList = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\nU98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split('\n')
    val wires = inputList.map { s -> s.split(',').map { parsePart(it) } }

    val wiresPointTransformations = wires.map { w -> w.flatMap { partPoints(it.first, it.second) } }
    //val wiresPoints = wiresPointTransformations.map { it.scan(Point(0,0)){p,f -> f(p)}.drop(1) }

    val wiresPoints = wiresPointTransformations.map { it.scanM(Point(0, 0)) { p, f -> f(p) }.drop(1) }
    val w1pts = wiresPoints.first()
    val w2pts = wiresPoints.last()

    val intersectPoints = w1pts.intersect(w2pts)

    val distances = intersectPoints.map { manhattanDistance(it) }.toList()

    val res1 = distances.min()
    println(res1) //1064


}
