val l = listOf(1,2,3,4,5)
l.contains(2)
l.slice(2..4)
l.windowed(2)
l.zipWithNext()
l.zipWithNext { a, b -> a + b }
l.withIndex().toList()
l.sum()
l.sumBy { it + 3 }
l.sortedDescending()
l.reversed()
l.asReversed()
l.onEach { print(it) }
l.shuffled()
l.random()
l.reduceIndexed { index, acc, i ->  index + acc + i}
l.partition { it < 3 }
l.map { (it + 32).toChar() }
l.joinToString(",")
l.groupBy { it % 2 }
l.count()
l.take(2)
l.indexOf(6)
l.getOrElse(6) { 0 }
l.lastIndex
l.last()
l.size
l.containsAll(listOf(1,2,3))
l.containsAll(listOf(1,2,6))
l.takeLast(2)
l.zip(l.reversed())