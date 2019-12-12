import AOCLib.Point3D

val s = "<x=-1, y=0, z=2>\n" +
        "<x=2, y=-10, z=-7>\n" +
        "<x=4, y=-8, z=8>\n" +
        "<x=3, y=5, z=-1>"
val coordsR = "<x=(?<x>[-]?\\d+), y=(?<y>[-]?\\d+), z=(?<z>[-]?\\d+)>".toRegex()
val r = coordsR.findAll(s)
val coords =
    r.map { m -> Point3D(m.groups["x"]!!.value.toInt(), m.groups["y"]!!.value.toInt(), m.groups["z"]!!.value.toInt()) }
        .toList()
coords
val cs = r.map { m ->
    val (x, y, z) = m.destructured
    Point3D(x.toInt(), y.toInt(), z.toInt())
}
cs.toList()
val Io = cs.first()
val Europa = cs.drop(1).first()
val Ganymede = cs.drop(2).first()
val Callisto = cs.last()
Io - Europa
Io.compareAxis(Europa)
Europa.compareAxis(Io)
(-1).compareTo(2)
val vels = cs.map { m -> cs.minus(m).map { m.compareAxis(it) }.reduce(Point3D::plus) }
vels.toList()