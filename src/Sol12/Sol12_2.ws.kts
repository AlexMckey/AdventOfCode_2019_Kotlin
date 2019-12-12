import AOCLib.Point3D
import Sol12.Moon

val s = "<x=-1, y=0, z=2>\n" +
        "<x=2, y=-10, z=-7>\n" +
        "<x=4, y=-8, z=8>\n" +
        "<x=3, y=5, z=-1>"
val coordsR = "<x=(?<x>[-]?\\d+), y=(?<y>[-]?\\d+), z=(?<z>[-]?\\d+)>".toRegex()
val moons = coordsR.findAll(s).map {
    val (x, y, z) = it.destructured
    Moon(Point3D(x.toInt(), y.toInt(), z.toInt()))
}
moons.toList()

fun step(ms: List<Moon>) = ms.map { m ->
    val vel = ms.minus(m)
        .map { m.pos.compareAxis(it.pos) }
        .reduce(Point3D::plus)
    val newMoon = Moon(m.pos + m.vel + vel, m.vel + vel)
    println("$m + $vel = $newMoon")
    newMoon
}.toList()

val s1 = step(moons.toList())
s1
println("------------------------------")
val s2 = step(s1)
s2