val l = "COM)B,B)C,C)D,D)E,E)F,B)G,G)H,D)I,E)J,J)K,K)L,K)YOU,I)SAN".split(',')
val m = l.map { it.substringBefore(')') to it.substringAfter(')') }
    .map { p -> p.second to p.first }.groupBy({ it.first }, { it.second })
m
fun backTree(m: Map<String, List<String>>, cur: String, acc: List<String> = emptyList()): List<String> {
    return if (!m.containsKey(cur)) acc// + "COM"
    else m[cur]!!.map { backTree(m, it, acc + cur) }.flatten()
}

val fromYou = backTree(m, "YOU").drop(1)
fromYou
val fromSan = backTree(m, "SAN").drop(1)
fromSan
val intsc = fromSan intersect fromYou
fromYou + fromSan - intsc
