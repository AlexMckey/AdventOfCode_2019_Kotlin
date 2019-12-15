package Sol03

import AOCLib.Point
import AOCLib.scanM
import java.io.File
import kotlin.streams.toList

fun parsePart(part: String): Pair<String, Int> {
    return part.let { it.substring(0, 1) to it.substring(1).toInt() }
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

    val distances = intersectPoints.map { it.manhattanDistance() }.toList()

    val res1 = distances.min()!!
    println(res1) //1064

    val minStepToIntersect = intersectPoints.map {
        w1pts.indexOf(it) + w2pts.indexOf(it) + 4
    }.min()!!
    println(minStepToIntersect) //25676
}
