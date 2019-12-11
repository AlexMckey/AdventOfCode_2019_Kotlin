import AOCLib.Point

val belt = ".#..#\n" +
        ".....\n" +
        "#####\n" +
        "....#\n" +
        "...##"
val s = belt.split('\n')
s
val asteroids =
    s.withIndex().flatMap { (y, row) -> row.withIndex().filter { it.value == '#' }.map { Point(it.index, y) } }
asteroids
asteroids.map { a ->
    a to asteroids
        .filterNot { it == a }
        .map { a.angle(it) }
        .distinct()
}
