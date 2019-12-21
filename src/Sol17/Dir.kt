package Sol17

data class Dir(val p: Pos, val dir: Char) {
    fun forward(): Dir = when (dir) {
        'U' -> Dir(p + Pos.toDown, 'U')
        'D' -> Dir(p + Pos.toUp, 'D')
        'L' -> Dir(p + Pos.toLeft, 'L')
        'R' -> Dir(p + Pos.toRight, 'R')
        else -> this
    }

    fun rotLeft(): Dir = when (dir) {
        'U' -> Dir(p + Pos.toLeft, 'L')
        'D' -> Dir(p + Pos.toRight, 'R')
        'L' -> Dir(p + Pos.toUp, 'D')
        'R' -> Dir(p + Pos.toDown, 'U')
        else -> this
    }

    fun rotRight(): Dir = when (dir) {
        'U' -> Dir(p + Pos.toRight, 'R')
        'D' -> Dir(p + Pos.toLeft, 'L')
        'L' -> Dir(p + Pos.toDown, 'U')
        'R' -> Dir(p + Pos.toUp, 'D')
        else -> this
    }
}