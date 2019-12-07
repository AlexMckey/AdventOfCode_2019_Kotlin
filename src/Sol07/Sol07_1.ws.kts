import AOCLib.permute
import java.util.*

val l = listOf(0, 1, 2, 3, 4)
val pl = l.permute().shuffled()
pl
pl[0].joinToString("").toInt()

var input: Stack<Int> = Stack()
var output: Stack<Int> = Stack()
input = output
output.push(7)
input.pop()
input.empty()

var i = mutableListOf<Int>()
val o = mutableListOf<Int>()
i.isEmpty()
o.isEmpty()
i = o
i.add(1)
o[0]
o.removeAt(0)
i.add(5)
o[0]