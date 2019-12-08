import java.io.File

fun AddColor(f: Char, s: Char): Char {
    return if (f == '2') s
    else f
}

val fileName =
    "d:\\YandexDisk\\DevsExercises\\AdventOfCode\\2019_Kotlin\\out\\production\\KtAOC2019\\Sol08\\input08.txt"
val inputStream = File(fileName).bufferedReader().readLine()
val w = 25
val h = 6
val ls = inputStream.windowed(w * h, w * h)
    .map { it.withIndex().toList() }.flatten()
val lgs = ls.groupBy({ i -> i.index }, { v -> v.value })
val lgsv = lgs.values
val rows = lgsv.map { it.reduce(::AddColor) }.windowed(w, w)
rows
