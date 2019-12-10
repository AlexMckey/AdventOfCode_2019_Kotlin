val s = ".#..#\n.....\n#####\n....#\n...##"
val ss = s.split('\n')
ss
val sm = ss.mapIndexed { x, str ->
    str.mapIndexed { y, c ->
        (x to y) to c
    }
}
    .flatten().filter { it.second == '#' }
    .map { it.first }
sm
val f = sm[0]
val smf = sm.filterNot { it == f }
smf
smf.groupBy { if (f.second - it.second == 0) 0.0 else 1.0 * (it.first - f.first) / (f.second - it.second) }.size
val d =
    sm.map { xy -> xy to sm.filterNot { it == xy }.groupBy { p -> if (xy.second - p.second == 0) 0.0 else 1.0 * (xy.first - p.first) / (xy.second - p.second) }.size }
        .toMap()
d
