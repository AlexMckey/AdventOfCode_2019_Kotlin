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

var h0 = mutableSetOf(moons[0])
var h1 = mutableSetOf(moons[1])
var h2 = mutableSetOf(moons[2])
var h3 = mutableSetOf(moons[3])
var res = mutableListOf(0, 0, 0, 0)
var state = moons
var cnt = 0
var steps = 0
do {
    state = step(state)
    if (res[0] != 0 && h0.contains(state[0])) {
        res[0] = steps
        cnt++
    } else h0.add(state[0])
    if (res[1] != 0 && h1.contains(state[1])) {
        res[1] = steps
        cnt++
    } else h1.add(state[1])
    if (res[2] != 0 && h2.contains(state[2])) {
        res[2] = steps
        cnt++
    } else h2.add(state[2])
    if (res[3] != 0 && h3.contains(state[3])) {
        res[3] = steps
        cnt++
    } else h3.add(state[3])
    steps++
} while (cnt < 4)
fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
fun lcm(a: Int, b: Int): Int = (a * b).absoluteValue / gcd(a, b)
//state
h0
h1
h2
h3
res