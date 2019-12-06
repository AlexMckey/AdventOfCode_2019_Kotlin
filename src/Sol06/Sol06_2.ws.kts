fun <S, T> List<T>.scanM(acc: S, op: (S, T) -> S): List<S> {
    var accumulator: S = acc
    return this.mapIndexed { _, x ->
        accumulator = op(accumulator, x)
        accumulator
    }
}

val l = "COM)B,B)C,C)D,D)E,E)F,B)G,G)H,D)I,E)J,J)K,K)L".split(',')
val m = l.map { it.substringBefore(')') to it.substringAfter(')') }
    .map { p -> p.second to p.first }.groupBy({ it.first }, { it.second })
m
m.keys - m.values.flatten()
fun backTree(m: Map<String, List<String>>, cur: String, acc: List<String> = emptyList()): List<String> {
    return if (!m.containsKey(cur)) acc// + "COM"
    else m[cur]!!.map { backTree(m, it, acc + cur) }.flatten()
}
backTree(m, "F")
backTree(m, "H")
backTree(m, "I")
backTree(m, "L")
val allPath = m.keys.map { backTree(m, it).reversed() }
allPath
allPath.map { it.size }.sum()