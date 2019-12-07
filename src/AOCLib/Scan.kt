package AOCLib

fun <S, T> List<T>.scanM(acc: S, op: (S, T) -> S): List<S> {
    var accumulator: S = acc
    return this.mapIndexed { _, x ->
        accumulator = op(accumulator, x)
        accumulator
    }
}

fun <S, T> List<T>.scan(acc: S, op: (S, T) -> S): List<S> {
    fun tempFun(accumulator: S, list: List<T>, op: (S, T) -> S): List<S> {
        val result = op(accumulator, list.first())
        val cdr = list.drop(1)
        if (cdr.isNotEmpty()) {
            return listOf(result) + tempFun(result, cdr, op)
        }
        return listOf(result)
    }
    return listOf(acc) + tempFun(acc, this, op)
}