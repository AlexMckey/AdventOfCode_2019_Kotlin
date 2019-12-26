import Sol18.Pos

val input = "########################\n" +
        "#f.D.E.e.C.b.A.@.a.B.c.#\n" +
        "######################.#\n" +
        "#d.....................#\n" +
        "########################"
val inputStream = input.split('\n')
val grid = inputStream
    .withIndex()
    .flatMap { yv -> yv.value.mapIndexed { x, c -> Pos(x, yv.index) to c } }
    .toMap()
grid
val objects = grid.filterValues { it != '#' && it != '.' }
    .map { it.value to it.key }.toMap()
objects
