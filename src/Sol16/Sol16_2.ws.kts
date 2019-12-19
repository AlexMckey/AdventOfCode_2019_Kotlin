import kotlin.math.absoluteValue

val inputStream = "03036732577212944063491565474664"
val input: List<Int> = inputStream.map { Character.digit(it, 10) }
inputStream.slice(0 until 7).toInt()
val offset = input.take(7).fold(0) { acc, digit -> 10 * acc + digit }
offset
inputStream.take(7).toInt()
val length = input.size
length
val newLength = 10000 * length
newLength - offset
check(offset < newLength && newLength <= 2 * offset)
val value = (offset until newLength).map { input[it % length] }.toIntArray()
offset % length
val f = input.take(offset % length)
val s = input.drop(offset % length)
value.toList()
value.size
(s + f).toList()
value.indices.reversed()
value.indices.reversed().fold(0) { acc, i ->
    ((acc + value[i]) % 10).absoluteValue.also { value[i] = it }
}
val l1 = value.toList()
value.toList()