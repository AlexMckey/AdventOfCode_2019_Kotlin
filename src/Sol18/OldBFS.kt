package Sol18

fun oldbfs(startPos: Pos, endPos: Set<Pos>, grid: Map<Pos, Char>): Map<Pos, Pair<Pos, Int>> {

    tailrec fun helper(visited: Map<Pos, Pair<Pos, Int>>, toVisit: Map<Pos, Pair<Pos, Int>>): Map<Pos, Pair<Pos, Int>> {
        val neighbors = toVisit
            .flatMap { me ->
                me.key
                    .near().intersect(grid.keys)
                    .map { p -> p to (me.key to me.value.second + 1) }
            }.toMap()
        val newVisited = visited + toVisit
        val newToVisit = neighbors.filterNot { visited.containsKey(it.key) }
        return if (newToVisit.isEmpty() || (visited.keys.containsAll(endPos)))
            newVisited
        else
            helper(newVisited, newToVisit)
    }

    return helper(emptyMap(), mapOf(startPos to (startPos to 0)))
}

fun findPath(startPos: Pos, endPos: Pos, grid: Map<Pos, Char>, reversed: Boolean = false): List<Pos> {
    val bfsTree = oldbfs(startPos, setOf(endPos), grid)
    var current = endPos
    val res = mutableListOf(current)
    while (current != startPos) {
        current = bfsTree[current]!!.first
        res.add(current)
    }
    //res.add(startPos) // optional
    return if (reversed) res.reversed() else res
}