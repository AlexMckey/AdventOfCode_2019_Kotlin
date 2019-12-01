val l = listOf(14, 1969, 100756)
l.sumBy{ Math.floorDiv(it, 3) - 2 }
fun calcFuel(mass: Int): Int {
    return Math.floorDiv(mass, 3) - 2
}
l.map { calcFuel(it) }
fun calcRecFuel(mass: Int, acc: Int = 0): Int {
    val newMass = calcFuel(mass)
    return if (newMass > 0) {
        calcRecFuel(newMass, acc + newMass)
    } else acc
}
l.map { calcRecFuel(it) }
fun calcListFuel(mass: Int, acc: List<Int> = emptyList()): List<Int> {
    val newMass = calcFuel(mass)
    return if (newMass <= 0) acc
    else calcListFuel(newMass, acc + newMass)
}
val r1 = l.map { calcListFuel(it) }
r1
r1.map { it.sum() }