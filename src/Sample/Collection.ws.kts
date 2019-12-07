import AOCLib.permute

val s = "вырадыф34258двыало0234йдфывоа032оафд923"
val s1 = "4352345"
s.all { it.isDigit() }
s1.all { it.isDigit() }
s.toCharArray().toList().shuffled().joinToString("")
s.filter { it.isDigit() }
s1.toCharArray().toList().permute().map { it.joinToString("") }