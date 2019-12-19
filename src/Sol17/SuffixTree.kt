package Sol17

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set


class SuffixTree(source: String) {
    inner class Node {
        val value: Char
        var children: MutableMap<Char, Node?>

        constructor() {
            value = '*'
            children = HashMap()
        }

        constructor(currentValue: Char) {
            value = currentValue
            children = HashMap()
        }

        fun addChild(c: Node?) {
            children[c!!.value] = c
        }

        fun hasChild(c: Node?): Boolean {
            return children.containsKey(c!!.value)
        }

        fun getChild(c: Node?): Node? {
            return children[c!!.value]
        }

        override fun toString(): String {
            val currentValue = value
            val keys = StringBuffer()
            for (key in children.keys) {
                keys.append("Current key:$key\n")
            }
            return "Current value:" + currentValue + "\n" +
                    "Keys:" + keys.toString()
        }
    }

    private val root: Node
    private fun log(l: Any) {
        println(l)
    }

    /*
      * Helper method that initializes the suffix tree
      */
    private fun createSuffixTree(source: String, root: Node): Node {
        for (i in source.indices) {
            var parent: Node? = Node(source[i])
            if (root.hasChild(parent)) {
                parent = root.getChild(parent)
            }
            var current = parent
            for (j in i + 1 until source.length) {
                var temp: Node? = Node(source[j])
                if (current!!.hasChild(temp)) {
                    temp = current.getChild(temp)
                } else {
                    current.addChild(temp)
                }
                current = temp
            }
            root.addChild(parent)
        }
        return root
    }

    fun printMap(map: Map<Char, Node?>) {
        for (c in map.keys) {
            println("Current node has child$c")
        }
    }

    fun search(target: String): Boolean {
        var rootChildren: Map<Char, Node?> = this.root.children
        for (c in target.toCharArray()) {
            rootChildren = if (rootChildren.containsKey(c)) {
                rootChildren[c]!!.children
            } else {
                return false
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val sTree = SuffixTree("bananas")
            val input: ArrayList<String?> = object : ArrayList<String?>() {
                init {
                    add("ba")
                    add("ban")
                    add("ana")
                    add("anas")
                    add("nan")
                    add("anans")
                    add("ananas")
                    add("n")
                    add("s")
                    add("as")
                    add("naab")
                    add("baan")
                    add("aan")
                }
            }
            for (i in input) {
                var exists = "exists"
                if (!i?.let { sTree.search(it) }!!) {
                    exists = "doesn't exist"
                }
                println("Input:$i $exists")
            }
        }
    }

    /*
      Creates the suffix tree from the given string
      */
    init {
        this.root = createSuffixTree(source, Node())
    }
}