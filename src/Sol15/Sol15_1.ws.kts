import AOCLib.Point

val grid: MutableMap<Point, Long> = mutableMapOf()
val droidPos = Point(0, 0)
grid.put(droidPos, 1L)
grid.put(droidPos + (1 to 0), 2L)
grid
droidPos.near() - grid.keys