val puzzleInput = "356779-356900"
val from = puzzleInput.substringBefore('-')
val to = puzzleInput.substringAfter('-')
from
to
val range = from.toInt()..to.toInt()
val p1 = range.map { it.toString() }[0]
p1
val p1charPairs = p1.zipWithNext()
p1charPairs
p1charPairs.all { (a, b) ->
    println("$a >= $b : ${a <= b}")
    a <= b
}
p1charPairs.any { (a, b) -> a == b }
//val ps = range.map{it.toString()}
val ps = "112233,123444,111122,111111,223450,123789".split(',')
val cps = ps.filter {
    val pass = it.zipWithNext().toList()
    pass.all { (a, b) -> a <= b } &&
            pass.any { (a, b) -> a == b }
}
cps
val p2 = cps[2]
p2.asIterable().groupingBy { it }.eachCount()
cps.filter {
    val pairCnts = it.asIterable().groupingBy { it }.eachCount()
    pairCnts.any { e -> e.value == 2 }
}