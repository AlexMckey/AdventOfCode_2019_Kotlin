package Sol12

import AOCLib.Point3D
import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val fileName = "out/production/KtAOC2019/Sol12/input12.txt"
    val inputStream = File(fileName).bufferedReader().readText()
    val coordsR = "<x=(?<x>[-]?\\d+), y=(?<y>[-]?\\d+), z=(?<z>[-]?\\d+)>".toRegex()
    val moons = coordsR.findAll(inputStream).map {
        val (x, y, z) = it.destructured
        Moon(Point3D(x.toInt(), y.toInt(), z.toInt()))
    }.toList()

    fun pvs(ms: List<Moon>) = ms.flatMap {
        listOf(
            'x' to (it.pos.x to it.vel.x),
            'y' to (it.pos.y to it.vel.y),
            'z' to (it.pos.z to it.vel.z)
        )
    }

    fun byCoord(ms: List<Moon>) = pvs(ms).groupBy({ it.first }, { it.second }).values

    val hs = byCoord(moons).map { mutableSetOf(it) }
    var state = moons
    var done: List<Boolean>// = listOf(false, false, false)
    do {
        state = step(state)
        done = hs.zip(byCoord(state)).map { it.first.add(it.second) }
    } while (!done.all { !it })

    val per = hs.map { it.size.toLong() }
    fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
    fun lcm(a: Long, b: Long): Long = (a * b).absoluteValue / gcd(a, b)
    val res2 = per.reduce(::lcm)
    println(res2)
}