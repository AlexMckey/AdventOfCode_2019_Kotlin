package Sol15

fun bfs(startPos: Pos, endPos: Set<Pos>, grid: Map<Pos, Block>): Map<Pos, Pair<Pos, Int>> {

    fun isFree(pos: Pos, grid: Map<Pos, Block>): Boolean =
        grid[pos] == Block.Empty || grid[pos] == Block.Oxygen

    tailrec fun helper(visited: Map<Pos, Pair<Pos, Int>>, toVisit: Map<Pos, Pair<Pos, Int>>): Map<Pos, Pair<Pos, Int>> {
        val neighbors = toVisit
            .flatMap { me ->
                me.key
                    .near()
                    .filter { isFree(it, grid) }
                    .map { p -> p to (me.key to me.value.second + 1) }
            }.toMap()
        val newVisited = visited + toVisit
        val newToVisit = neighbors.filterNot { visited.containsKey(it.key) }
        return if (newToVisit.isEmpty() || (toVisit.keys intersect endPos).isNotEmpty())
            newVisited
        else
            helper(newVisited, newToVisit)
    }

    return helper(emptyMap(), mapOf(startPos to (startPos to 0)))
}

fun findPath(startPos: Pos, endPos: Pos, grid: Map<Pos, Block>, reversed: Boolean = false): List<Pos> {
    val bfsTree = bfs(startPos, setOf(endPos), grid)
    var current = endPos
    val res = mutableListOf(current)
    while (current != startPos) {
        current = bfsTree[current]!!.first
        res.add(current)
    }
    //res.add(startPos) // optional
    return if (reversed) res.reversed() else res
}