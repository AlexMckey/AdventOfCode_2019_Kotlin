data class Point(val x: Int, val y: Int) {
    companion object {
        fun incXPoint(): (Point) -> Point = { p -> Point(p.x + 1, p.y) }
        fun decXPoint(): (Point) -> Point = { p -> Point(p.x - 1, p.y) }
        fun incYPoint(): (Point) -> Point = { p -> Point(p.x, p.y + 1) }
        fun decYPoint(): (Point) -> Point = { p -> Point(p.x, p.y - 1) }
    }
}

val wire1path = "R8,U5,L5,D3"
val w1l = wire1path.split(',')
val w1p1 = w1l.first()
fun parsePart(part: String): Pair<String, Int> {
    return part.let { it.substring(0, 1) to it.substring(1).toInt() }
}

val w1p1p = parsePart(w1p1)
fun partPoints(part: Pair<String, Int>): List<(Point) -> Point> {
    return List(part.second) {
        when (part.first) {
            "L" -> Point.decXPoint()
            "R" -> Point.incXPoint()
            "U" -> Point.incYPoint()
            "D" -> Point.decYPoint()
            else -> { p: Point -> p }
        }
    }
}

fun incXPoint(): (Point) -> Point = { p -> Point(p.x + 1, p.y) }
val w1p1t = List(w1p1p.second - 1) { incXPoint() }

fun <S, T> List<T>.scan(acc: S, op: (S, T) -> S): List<S> {
    fun tempFun(accumulator: S, list: List<T>, op: (S, T) -> S): List<S> {
        val result = op(accumulator, list.first())
        val cdr = list.drop(1)
        if (cdr.isNotEmpty()) {
            return listOf(result) + tempFun(result, cdr, op)
        }
        return listOf(result)
    }
    return listOf(acc) + tempFun(acc, this, op)
}
w1p1t.scan(Point(0, 0))
{ p: Point, f: (Point) -> Point -> f(p) }

val r = w1l.map { parsePart(it) }.flatMap { partPoints(it) }
r[0](Point(0, 0))
r.scan(Point(0, 0)) { p, f -> f(p) }