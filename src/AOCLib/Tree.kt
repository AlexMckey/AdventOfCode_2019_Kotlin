package AOCLib

class Tree<T>(val value: T) {
    val children: MutableList<Tree<T>> = mutableListOf()
    val size: Int
        get() = children.fold(1) { acc, tree -> acc + tree.size }
    val height: Int
        get() = 1 + (children.map { it.size }.max() ?: 0)

    fun add(child: T) = children.add(Tree(child))
}