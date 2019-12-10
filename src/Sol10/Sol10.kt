package Sol10

import AOCLib.Point
import java.io.File

fun main() {
    val fileName = "out/production/KtAOC2019/Sol10/input10.txt"
    val inputStream = File(fileName).bufferedReader().readLines()
//    val belt =  ".#..#\n" +
//                ".....\n" +
//                "#####\n" +
//                "....#\n" +
//                "...##" //8
//    val belt =  "......#.#.\n" +
//            "#..#.#....\n" +
//            "..#######.\n" +
//            ".#.#.###..\n" +
//            ".#..#.....\n" +
//            "..#....#.#\n" +
//            "#..#....#.\n" +
//            ".##.#..###\n" +
//            "##...#..#.\n" +
//            ".#....####" //33
//    val belt =  "#.#...#.#.\n" +
//            ".###....#.\n" +
//            ".#....#...\n" +
//            "##.#.#.#.#\n" +
//            "....#.#.#.\n" +
//            ".##..###.#\n" +
//            "..#...##..\n" +
//            "..##....##\n" +
//            "......#...\n" +
//            ".####.###." //([x:1,y:2], 35)
//    val belt =  "#.#...#.#.\n" +
//            ".###....#.\n" +
//            ".#....#...\n" +
//            "##.#.#.#.#\n" +
//            "....#.#.#.\n" +
//            ".##..###.#\n" +
//            "..#...##..\n" +
//            "..##....##\n" +
//            "......#...\n" +
//            ".####.###." //35
//    val belt = ".#..##.###...#######\n" +
//            "##.############..##.\n" +
//            ".#.######.########.#\n" +
//            ".###.#######.####.#.\n" +
//            "#####.##.#.##.###.##\n" +
//            "..#####..#.#########\n" +
//            "####################\n" +
//            "#.####....###.#.#.##\n" +
//            "##.#################\n" +
//            "#####.##.###..####..\n" +
//            "..######..##.#######\n" +
//            "####.##.####...##..#\n" +
//            ".#####..#.######.###\n" +
//            "##...#.##########...\n" +
//            "#.##########.#######\n" +
//            ".####.#.###.###.#.##\n" +
//            "....##.##.###..#####\n" +
//            ".#.#.###########.###\n" +
//            "#.#.#.#####.####.###\n" +
//            "###.##.####.##.#..##"
//    val inputStream = belt.split('\n')

    val asteroids = inputStream
        .withIndex()
        .flatMap { (y, r) ->
            r
                .withIndex()
                .filter { (_, c) -> c == '#' }
                .map { (x, _) -> Point(x, y) }
        }
    val res1 = asteroids.map { a ->
        a to asteroids
            .filterNot { it == a }
            .map { a.angle(it) }
            .distinct().size
    }
        .maxBy { it.second }!!
    println(res1)

    val laserPoint = res1.first
    val sortedAsteroids = asteroids.asSequence().filterNot { it == laserPoint }
        .map { it to (laserPoint.angle(it) - 90) }
        .sortedBy { (_, a) -> a }
        .groupBy({ it.second }, { it.first })
        .map { (a, ps) -> a to ps }.toList()
    val parts = sortedAsteroids.partition { it.first < 0 }
    val sa = parts.second + parts.first.map { -1 * it.first + 90 to it.second }
    val cnt = sa.map { it.second }.count()
    val ordered = sa.map { it.second }
        .withIndex()
        .flatMap { (outer, list) ->
            list
                .mapIndexed { inner, p -> inner * cnt + outer to p }
        }
    val res2 = ordered.sortedBy { it.first }.map { it.second }[199]

    println(res2.x * 100 + res2.y) //1309
}