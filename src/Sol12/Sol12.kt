package Sol12

import AOCLib.Point3D
import java.io.File
import kotlin.math.absoluteValue

data class Moon(val pos: Point3D, val vel: Point3D = Point3D(0, 0, 0)) {
    override fun toString(): String = "pos=$pos>, vel=$vel"
    fun energy() = (pos.x.absoluteValue + pos.y.absoluteValue + pos.z.absoluteValue) *
            (vel.x.absoluteValue + vel.y.absoluteValue + vel.z.absoluteValue)
}

fun step(ms: List<Moon>) = ms.map { m ->
    val vel = ms.minus(m)
        .map { m.pos.compareAxis(it.pos) }
        .reduce(Point3D::plus)
    val newMoon = Moon(m.pos + m.vel + vel, m.vel + vel)
    //println("$m + $vel = $newMoon")
    newMoon
}

fun main() {
    val fileName = "out/production/KtAOC2019/Sol12/input12.txt"
    val inputStream = File(fileName).bufferedReader().readText()

    val coordsR = "<x=(?<x>[-]?\\d+), y=(?<y>[-]?\\d+), z=(?<z>[-]?\\d+)>".toRegex()
    val moons = coordsR.findAll(inputStream).map {
        val (x, y, z) = it.destructured
        Moon(Point3D(x.toInt(), y.toInt(), z.toInt()))
    }.toList()

    var st = moons
    repeat(1000) { st = step(st) }
    val res1 = st.map { it.energy() }.sum()
    println(res1) //7013

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
    var done: List<Boolean>
    do {
        state = step(state)
        done = hs.zip(byCoord(state)).map { it.first.add(it.second) }
    } while (!done.all { !it })

    val per = hs.map { it.size.toLong() }
    fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
    fun lcm(a: Long, b: Long): Long = (a * b).absoluteValue / gcd(a, b)
    val res2 = per.reduce(::lcm)
    println(res2) //324618307124784
}