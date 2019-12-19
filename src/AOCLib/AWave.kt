package AOCLib

import java.util.*
import kotlin.collections.ArrayList

fun aStar(from: Node, to: Node) {

    val exploreList: PriorityQueue<Node> = PriorityQueue()
    val visited: ArrayList<Node> = ArrayList()
    var successors: ArrayList<Node>

    var current: Node = from
    println(current.name)
    while (current != to) {
        successors = current.connected
        successors.sort()
        for (n in successors) {
            if (!visited.contains(n)) {
                exploreList.add(n)
            }
            for (n1 in successors) {
                if (n.fSum() > n1.fSum()) {
                    exploreList.remove(n)
                    exploreList.add(n1)
                }
            }
        }
        visited.add(current)
        current = exploreList.remove()
        println(current.name)
    }
}