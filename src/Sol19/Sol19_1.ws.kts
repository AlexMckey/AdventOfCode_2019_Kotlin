val w = 10
val h = 10
(0 until h).flatMap { y -> (0 until w).flatMap { x -> listOf(x, y) } }