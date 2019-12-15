data class Chem(val name: String, val cnt: Int) {
    constructor(str: String) :
            this(str.split(' ').last(), str.split(' ').first().toInt())

    override fun toString(): String = "$cnt $name"
}

class Reaction(str: String) {
    val chem: Chem = Chem(str.substringAfter(" => "))
    val need: List<Chem> = str.substringBefore(" => ")
        .split(", ").map { Chem(it) }

    override fun toString(): String = "${need.joinToString(", ")} => $chem"
}

val sample1 = "10 ORE => 10 A\n" +
        "1 ORE => 1 B\n" +
        "7 A, 1 B => 1 C\n" +
        "7 A, 1 C => 1 D\n" +
        "7 A, 1 D => 1 E\n" +
        "7 A, 1 E => 1 FUEL"
val inputStream = sample1.split('\n')

val rs = inputStream.map { Reaction(it) }
rs
val f = rs.find { it.chem.name == "FUEL" }
f

var needs = mutableMapOf("FUEL" to 1)
while (needs.any { it.key != "ORE" && it.value > 0 }) {
    needs.filter { it.key != "ORE" && it.value > 0 }
        .keys.forEach { n ->
        val r = rs.find { it.chem.name == n }!!
        needs.merge(r.chem.name, r.chem.cnt) { t, u -> t - u }
        r.need.forEach { m -> needs.merge(m.name, m.cnt) { t, u -> t + u } }
    }
}
needs