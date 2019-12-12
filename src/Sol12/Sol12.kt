package Sol12

import AOCLib.Point3D
import kotlin.math.absoluteValue

data class Moon(val pos: Point3D, val vel: Point3D = Point3D(0, 0, 0)) {
    override fun toString(): String = "pos=$pos>, vel=$vel"
    fun adjustVelocity(newVel: Point3D) = Moon(pos + vel + newVel, vel + newVel)
    fun energy() = (pos.x.absoluteValue + pos.y.absoluteValue + pos.z.absoluteValue) *
            (vel.x.absoluteValue + vel.y.absoluteValue + vel.z.absoluteValue)

    override fun hashCode(): Int = toString().hashCode()
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
    //val inputStream = File(fileName).bufferedReader().readText()
//    val inputStream1 = "<x=-1, y=0, z=2>\n" +
//            "<x=2, y=-10, z=-7>\n" +
//            "<x=4, y=-8, z=8>\n" +
//            "<x=3, y=5, z=-1>"
    val inputStream2 = "<x=-8, y=-10, z=0>\n" +
            "<x=5, y=5, z=10>\n" +
            "<x=2, y=-7, z=3>\n" +
            "<x=9, y=-8, z=-3>"
    val coordsR = "<x=(?<x>[-]?\\d+), y=(?<y>[-]?\\d+), z=(?<z>[-]?\\d+)>".toRegex()
    //val moons = coordsR.findAll(inputStream1).map {
    val moons = coordsR.findAll(inputStream2).map {
        //    val moons = coordsR.findAll(inputStream).map {
        val (x, y, z) = it.destructured
        Moon(Point3D(x.toInt(), y.toInt(), z.toInt()))
    }.toList()
    val seq = generateSequence(moons) { step(it) }
    //val res1 = seq.drop(10).take(1).last()
    //val res1 = seq.drop(100).take(1).last()
    val res1 = seq.drop(1000).take(1).last()
    println(res1.map { it.energy() }.sum()) //7013

    var hs = moons.map { setOf(it) }
    var state = moons
    var prevSumStates = 0
    var curSumStates = hs.sumBy { it.size }
    do {
        prevSumStates = curSumStates
        state = step(state)
        hs = hs.zip(state).map { it.first + it.second }
        curSumStates = hs.map { it.size }.sum()
    } while (prevSumStates != curSumStates)
    val per = hs.map { it.size }
    println(per)
    fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    fun lcm(a: Int, b: Int): Int = (a * b).absoluteValue / gcd(a, b)
    val res2 = per.reduce(::lcm)
    println(res2)
}