package AOCLib

fun <Node> reconstructPath(cameFrom: Map<Node, Node>, from: Node): List<Node> {
    val totalPath = mutableListOf(from)
    var current = from
    while (cameFrom.containsKey(current)) {
        current = cameFrom[current]!!
        totalPath.add(current)
    }
    return totalPath.toList() // возможно необходим реверс
}

// A* finds a path from start to goal.
// h is the heuristic function. h(n) estimates the cost to reach goal from node n.
fun <Node> aStar(
    start: Node,
    goal: Node,
    near: (Node) -> List<Node>,
    d: (Node, Node) -> Int,
    h: (Node, Node) -> Int
): List<Node> {
    // The set of discovered nodes that may need to be (re-)expanded.
    // Initially, only the start node is known.
    val openSet = mutableSetOf(start)
    // For node n, cameFrom[n] is the node immediately preceding it on the cheapest path from start to n currently known.
    val cameFrom = mutableMapOf<Node, Node>()
    // For node n, gScore[n] is the cost of the cheapest path from start to n currently known.
    val gScore = mutableMapOf<Node, Int>().withDefault { Int.MAX_VALUE }
    gScore[start] = 0
    // For node n, fScore[n] := gScore[n] + h(n).
    val fScore = mutableMapOf<Node, Int>().withDefault { Int.MAX_VALUE }
    fScore[start] = h(start, goal)
    while (openSet.isNotEmpty()) {
        val current = openSet.minBy { fScore[it]!! }!!
        if (current == goal)
            return reconstructPath(cameFrom.toMap(), current)
        openSet.remove(current)
        for (neighbor in near(current)) {
            // d(current,neighbor) is the weight of the edge from current to neighbor
            // tentative_gScore is the distance from start to the neighbor through current
            val tentativeGScore = gScore[current]!! + d(current, neighbor)
            if (tentativeGScore < gScore[neighbor]!!) {
                // This path to neighbor is better than any previous one. Record it!
                cameFrom[neighbor] = current
                gScore[neighbor] = tentativeGScore
                fScore[neighbor] = gScore[neighbor]!! + h(neighbor, goal)
                if (!openSet.contains(neighbor))
                    openSet.add(neighbor)
            }
        }
    }
    // Open set is empty but goal was never reached
    return emptyList()
}