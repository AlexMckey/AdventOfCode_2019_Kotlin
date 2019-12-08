val s = "123456789012"
val w = 3
val h = 2
val l = h * w
s.windowed(l, l).map { it.toCharArray().toList() }
    .map { it.groupingBy { it }.eachCount().toMap() }
    .maxBy { it.getOrDefault('0', 0) }
