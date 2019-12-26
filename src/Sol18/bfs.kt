package Sol18

data class NodeKey(val key: Char, val dist: Int, val doors: Set<Char>)

fun bfs(grid: Map<Pos, Char>, from: Pos): List<NodeKey> {
    tailrec fun recHelper(
        toVisit: List<Triple<Pos, Int, Set<Char>>>,
        visited: List<Pos>,
        acc: List<NodeKey>
    ): List<NodeKey> =
        if (toVisit.isEmpty()) acc
        else {
            val (pos, dist, doors) = toVisit.first()
            if (pos in visited)
                recHelper(toVisit.drop(1), visited, acc)
            else {
                var ch = grid[pos] ?: error("Position $pos not in grid")
                ch = if ("@1234".contains(ch)) '.' else ch
                when {
                    ch.isLowerCase() -> recHelper(toVisit.drop(1) + pos.near().filter { grid[it] != '#' }
                        .map { p -> Triple(p, dist + 1, doors) },
                        visited + pos,
                        if (dist == 0) acc else acc + NodeKey(ch, dist, doors)
                    )
                    ch.isUpperCase() -> recHelper(toVisit.drop(1) + pos.near().filter { grid[it] != '#' }
                        .map { p -> Triple(p, dist + 1, doors + ch) },
                        visited + pos,
                        acc
                    )
                    else -> recHelper(toVisit.drop(1) + pos.near().filter { grid[it] != '#' }
                        .map { p -> Triple(p, dist + 1, doors) },
                        visited + pos,
                        acc
                    )
                }
            }
        }
    return recHelper(listOf(Triple(from, 0, emptySet())), emptyList(), emptyList())
}

fun bfsGraph(grid: Map<Pos, Char>, from: Pos) =
    bfs(grid, from).map { it.key to (it.dist to it.doors) }.toMap()