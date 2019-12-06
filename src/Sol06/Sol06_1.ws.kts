val l = "COM)B,B)C,C)D,D)E,E)F,B)G,G)H,D)I,E)J,J)K,K)L".split(',')
l
val p = l.map { it.substringBefore(')') to it.substringAfter(')') }
p
val m = p.groupBy({ it.first }, { it.second })
m
val mm = p.map { (from, _) -> from to p.filter { (k, _) -> k == from }.map { it.second } }.toMap()
mm
val mm1 =
    mm.map { me -> me.key to (me.value + me.value.flatMap { v -> if (mm.containsKey(v)) mm[v]!!.asIterable() else emptyList() }) }
        .toMap()
mm1
val mm2 =
    mm1.map { me -> me.key to (me.value + me.value.flatMap { v -> if (mm1.containsKey(v)) mm1[v]!!.asIterable() else emptyList() }).distinct() }
        .toMap()
mm2
mm2.map { it.value.size }.sum()
val mm3 =
    mm2.map { me -> me.key to (me.value + me.value.flatMap { v -> if (mm1.containsKey(v)) mm1[v]!!.asIterable() else emptyList() }).distinct() }
        .toMap()
mm3
mm3.map { it.value.size }.sum()
val mm4 =
    mm3.map { me -> me.key to (me.value + me.value.flatMap { v -> if (mm1.containsKey(v)) mm1[v]!!.asIterable() else emptyList() }).distinct() }
        .toMap()
mm4
mm4.map { it.value.size }.sum()
val mm5 =
    mm4.map { me -> me.key to (me.value + me.value.flatMap { v -> if (mm1.containsKey(v)) mm1[v]!!.asIterable() else emptyList() }).distinct() }
        .toMap()
mm5
mm5.map { it.value.size }.sum()