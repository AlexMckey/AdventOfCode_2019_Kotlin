val s = "0222112222120000"
val w = 2
val h = 2
val ls11 = s.windowed(1, w * h)
ls11
val ls12 = s.drop(1).windowed(1, w * h)
ls12
fun AddColor(f: Char, s: Char): Char {
    return if (f == '2') s
    else f
}

val l = s.windowed(w * h, w * h)
    .map { it.withIndex().toList() }
l
val lc = l.flatten().groupBy({ i -> i.index }, { v -> v.value })
lc.values
val lsc = lc.values.map { it.dropWhile { c -> c == '2' }.take(1) }.flatten()
lsc
lsc.windowed(w, h).map { it.joinToString("") }.reversed().joinToString("\n")