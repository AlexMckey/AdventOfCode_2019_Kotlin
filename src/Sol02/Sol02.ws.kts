val ns = 1..99
val vs = 1..99
ns.flatMap { n -> vs.map { Pair(n, it) } }.shuffled().parallelStream()
    .filter { (a, b) -> a == b }.findAny()