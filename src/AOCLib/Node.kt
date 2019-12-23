package AOCLib

import java.util.*

data class Arc(val node: Node?, val cost: Int)

class Node(
    val name: String, private val travDist: Int, private val straightDist: Int
) : Comparable<Any?> {
    private val arcs: ArrayList<Arc> = ArrayList()

    fun addArc(to: Node?, c: Int) {
        arcs.add(Arc(to, c))
    }

    val connected: ArrayList<Node>
        get() {
            val returnData = ArrayList<Node>()
            for (a in arcs) {
                returnData.add(a.node!!)
            }
            return returnData
        }

    override operator fun compareTo(other: Any?): Int { //return name.compareTo(((Node) o).getName());
        val sum = (other as Node).fSum()
        return sum.compareTo(fSum())
    }

    fun fSum(): Int {
        return travDist + straightDist
    }

}
