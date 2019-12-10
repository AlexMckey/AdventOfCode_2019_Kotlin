import AOCLib.Point

val belt = ".#..##.###...#######\n" +
        "##.############..##.\n" +
        ".#.######.########.#\n" +
        ".###.#######.####.#.\n" +
        "#####.##.#.##.###.##\n" +
        "..#####..#.#########\n" +
        "####################\n" +
        "#.####....###.#.#.##\n" +
        "##.#################\n" +
        "#####.##.###..####..\n" +
        "..######..##.#######\n" +
        "####.##.####...##..#\n" +
        ".#####..#.######.###\n" +
        "##...#.##########...\n" +
        "#.##########.#######\n" +
        ".####.#.###.###.#.##\n" +
        "....##.##.###..#####\n" +
        ".#.#.###########.###\n" +
        "#.#.#.#####.####.###\n" +
        "###.##.####.##.#..##"
val s = belt.split('\n')
val asteroids = s
    .withIndex()
    .flatMap { (y, r) ->
        r
            .withIndex()
            .filter { (_, c) -> c != '.' }
            .map { (x, _) -> Point(x, y) }
    }
asteroids
val laserPoint = Point(11, 13)
asteroids.contains(laserPoint)
val sortedAsteroids = asteroids.asSequence().filterNot { it == laserPoint }
    .map { it to (laserPoint.angle(it) - 90 to laserPoint.distance(it)) }
    .sortedBy { (_, a) -> a.first }
    .groupBy({ it.second.first }, { it.first })
    .map { (a, ps) -> a to ps }.toList()
sortedAsteroids
val parts = sortedAsteroids.partition { it.first < 0 }
val sa = parts.second + parts.first.map { -1 * it.first + 90 to it.second }
val cnt = sa.map { it.second }.count()
cnt
val ordered = sa.map { it.second }
    .withIndex()
    .flatMap { (outer, list) ->
        list
            .mapIndexed { inner, p -> inner * cnt + outer to p }
    }
ordered.sortedBy { it.first }.map { it.second }[199]