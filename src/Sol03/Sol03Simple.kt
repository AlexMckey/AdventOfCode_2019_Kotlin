package Sol03

import java.io.File
import kotlin.streams.toList

fun wirePathToPoints(path: List<Pair<String, Int>>): Map<Point, Int> {
    var x = 0
    var y = 0
    var len = 0
    val res = mutableMapOf<Point, Int>()
    path.forEach { (dir, cnt) ->
        for (i in 0 until cnt) {
            when (dir) {
                "L" -> x--
                "R" -> x++
                "U" -> y++
                "D" -> y--
                else -> {
                }
            }
            len++
            res[Point(x, y)] = len
        }
    }
    return res
}

fun main() {
    val inputList = File(fileName).bufferedReader().lines().toList()
    //val inputList = "R75,D30,R83,U83,L12,D49,R71,U7,L72\nU62,R66,U55,R34,D71,R55,D58,R83".split('\n')
    //val inputList = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\nU98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split('\n')

    val wires = inputList.map { s -> s.split(',').map { parsePart(it) } }
    val wiresPoints = wires.map { wirePathToPoints(it) }
    val wire1 = wiresPoints.first()
    val wire2 = wiresPoints.last()
    val intersectPoints = wire1.keys.intersect(wire2.keys)

    val res1 = intersectPoints.map { manhattanDistance(it) }.min()!!
    println(res1) //1064

    val pathToIntersectPoints = intersectPoints.map { p ->
        //println("${wire1[p]} : ${wire2[p]}")
        //println("${wire1.keys.indexOf(p)} : ${wire2.keys.indexOf(p)}")
        wire1[p]!! + wire2[p]!!
    }
    val res2 = pathToIntersectPoints.min()!!
    println(res2) //25676

    val stepsToIntersectPoints = intersectPoints.map { p ->
        wire1.filterKeys { it == p }.values.min()!! +
                wire2.filterKeys { it == p }.values.min()!!
    }
    val res3 = stepsToIntersectPoints.min()!!
    println(res3)
}

