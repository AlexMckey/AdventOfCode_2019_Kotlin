package Sol18

fun isDoor(ch: Char) = ch.isLetter() && ch.isUpperCase()
fun isKey(ch: Char) = ch.isLetter() && ch.isLowerCase()
fun isEntry(ch: Char) = ch in "@1234"
fun isNotWall(ch: Char) = ch != '#'
inline fun Int.getBit(i: Int) = shr(i) and 1 == 1
inline fun Int.setBit(i: Int) = or(1 shl i)