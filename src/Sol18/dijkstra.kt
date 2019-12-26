package Sol18

private fun checkDoorsAndKeys(doors: Set<Char>, keys: Set<Char>): Boolean =
    (doors - keys.map { it.toUpperCase() }.toSet()).isEmpty()

fun dijkstra(graph: Map<Char, Map<Char, Pair<Int, Set<Char>>>>, start: Char): MutableMap<Char, Pair<Int, Char?>> {
    val visited = mutableMapOf<Char, Pair<Int, Char?>>()
    var node = NodeKey(start, 0, emptySet())
    val toVisit = mutableListOf(node)
    while (toVisit.isNotEmpty()) {
        node = toVisit.minBy { it.dist }!!
        toVisit.remove(node)
        val neighbors = graph[node.key]!!
            .filterKeys { ch -> ch !in node.doors }
            //.filterValues { p -> (p.second - visited.keys).isEmpty() }
            .filterValues { checkDoorsAndKeys(it.second, node.doors + node.key - start) }
        if (neighbors.isEmpty()) continue
        neighbors.forEach { me ->
            toVisit.add(
                NodeKey(
                    me.key,
                    node.dist + me.value.first,
                    (node.doors + node.key)
                )
            )
            val path = node.dist + me.value.first
            if (!visited.containsKey(me.key))
                visited[me.key] = Int.MAX_VALUE to null
            if (path < visited[me.key]!!.first)
                visited[me.key] = path to node.key
        }
    }
    return visited
}