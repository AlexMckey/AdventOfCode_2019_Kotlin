import Sol17.Pos

val output = "..#..........\n" +
        "..#..........\n" +
        "#######...###\n" +
        "#.#...#...#.#\n" +
        "#############\n" +
        "..#...#...#..\n" +
        "..#####...^.."
val rows = output.split("\n")
val grid = rows.withIndex().flatMap { yv -> yv.value.mapIndexed { x, c -> Pos(x, yv.index) to c } }.toMap()
val crosses = grid.filterValues { c -> c == '#' }.filterKeys { p -> p.near().all { n -> grid[n] == '#' } }
crosses.map { it.key.x * it.key.y }.sum()
//val prog = "L12R8L10L10R6R4R12R12R8L10L10R6R4R12L10R8R4L10R6R4R12R12R8L10L10L10R8R4L10R6R4R12L10R8R4L10"
//val prog = "R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2"
//val prog = "L,6,R,12,L,6,R,12,L,10,L,4,L,6,L,6,R,12,L,6,R,12,L,10,L,4,L,6,L,6,R,12,L,6,L,10,L,10,L,4,L,6,R,12,L,10,L,4,L,6,L,10,L,10,L,4,L,6,L,6,R,12,L,6,L,10,L,10,L,4,L,6"
//var proga = LRS.lrs(prog).trim(',')
//proga
//val pa = "L,6,R,12,L,6"
//val prog1 = prog.replace(pa,"A")
//prog1
//var progb = LRS.lrs(prog1).trim(',')
//progb
//val pb = "R,12,L,10,L,4,L,6"
//val prog2 = prog1.replace(pb,"B")
//prog2
//var progc = LRS.lrs(prog2).trim(',')
//progc
//val pc = "L,10,L,10,L,4,L,6"
//val prog3 = prog2.replace(pc,"C")
//prog3

val prog =
    "L,12,L,8,R,10,R,10,L,6,L,4,L,12,L,12,L,8,R,10,R,10,L,6,L,4,L,12,R,10,L,8,L,4,R,10,L,6,L,4,L,12,L,12,L,8,R,10,R,10,R,10,L,8,L,4,R,10,L,6,L,4,L,12,R,10,L,8,L,4,R,10".replace(
        ",",
        ""
    )
val progDummy =
    "L12R8L10L10R6R4R12R12R8L10L10R6R4R12L10R8R4L10R6R4R12R12R8L10L10L10R8R4L10R6R4R12L10R8R4L10".replace(",", "")
val procs = "L12L8R10R10,L6L4L12,R10L8L4R10"
val re = "^(.{1,11})\\1*(.{1,11})(?:\\1|\\2)*(.{1,11})(?:\\1|\\2|\\3)*\$".toRegex()
val matching = re.matchEntire(prog)
val progMain = matching!!.groups[0]!!.value
    .replace(matching.groups[1]!!.value, "A")
    .replace(matching.groups[2]!!.value, "B")
    .replace(matching.groups[3]!!.value, "C")
progMain
val sb = StringBuilder()
    .append(progMain).append('\n')
    .append(matching!!.groups[1]!!.value).append('\n')
    .append(matching.groups[2]!!.value).append('\n')
    .append(matching.groups[3]!!.value).append('\n')
    .append('n').append('\n')
sb.toString()
val r = "(\\d{1,2}|A|B|C|n|R|L)(?!\$|\\n|\\d)".toRegex()
r.replace(sb.toString(), "$1,")
sb.toString().replace("A", ",A,")
    .replace("B", ",B,").replace("C", ",C,")
    .replace("R", ",R,").replace("L", ",L,")
    .replace(",,", ",").trim(',')