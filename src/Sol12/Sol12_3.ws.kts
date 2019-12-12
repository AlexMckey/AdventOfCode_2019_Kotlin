import AOCLib.Point3D
import Sol12.Moon
import kotlin.math.absoluteValue

val s = "<x=-1, y=0, z=2>\n" +
        "<x=2, y=-10, z=-7>\n" +
        "<x=4, y=-8, z=8>\n" +
        "<x=3, y=5, z=-1>"
val coordsR = "<x=(?<x>[-]?\\d+), y=(?<y>[-]?\\d+), z=(?<z>[-]?\\d+)>".toRegex()
val moons = coordsR.findAll(s).map {
    val (x, y, z) = it.destructured
    Moon(Point3D(x.toInt(), y.toInt(), z.toInt()))
}.toList()

fun step(ms: List<Moon>) = ms.map { m ->
    val vel = ms.minus(m)
        .map { m.pos.compareAxis(it.pos) }
        .reduce(Point3D::plus)
    val newMoon = Moon(m.pos + m.vel + vel, m.vel + vel)
    //println("$m + $vel = $newMoon")
    newMoon
}

var hs = moons.map { hashSetOf(it) }
var state = moons
var prevSumStates: Int
var curSumStates = hs.sumBy { it.size }
do {
    prevSumStates = curSumStates
    state = step(state)
    hs = hs.zip(state).map { (it.first + it.second).toHashSet() }
    curSumStates = hs.map { it.size }.sum()
} while (prevSumStates != curSumStates)
val per = hs.map { it.size }
fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
fun lcm(a: Int, b: Int): Int = (a * b).absoluteValue / gcd(a, b)
per.reduce(::lcm)