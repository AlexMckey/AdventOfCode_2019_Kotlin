package Sol18

import kotlin.math.min

data class State(val ps: Set<Pos>, val ownedKeys: Set<Char>, val step: Int)

fun collectKeys(
    grid: Map<Pos, Char>,
    graph: Map<Pos, List<NodeKey>>,
    mazeObjects: Map<Char, Pos>,
    startPos: List<Char>
): Int {
    val sps = startPos.map { mazeObjects[it]!! }
    var curMin = Int.MAX_VALUE
    val q = mutableListOf<State>(State(sps.toSet(), emptySet(), 0))
    val visited = mutableMapOf<Pair<Set<Pos>, Set<Char>>, Int>()
    val keys = mazeObjects.filterKeys { it.isLetter() && it.isLowerCase() }
    val keysCount = keys.size
    while (q.any()) {
        val state = q.removeAt(0)
        val k: Pair<Set<Pos>, Set<Char>> = state.ps to state.ownedKeys
        val steps = visited[k]
        if (steps != null) {
            if (steps <= state.step) continue
            else visited[k] = state.step
        } else visited[k] = state.step
        if (state.ownedKeys == keys) {
            curMin = min(curMin, state.step)
            continue
        }
        for (r in startPos) {

        }
    }
    return 0
}