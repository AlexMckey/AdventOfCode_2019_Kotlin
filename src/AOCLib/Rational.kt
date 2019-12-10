package AOCLib

import java.math.BigInteger

class Rational(val n: BigInteger, val d: BigInteger) : Comparable<Rational> {

    operator fun plus(r: Rational): Rational = (n.times(r.d).plus(r.n.times(d))).divBy(r.d.times(d))

    operator fun minus(r: Rational): Rational = (n.times(r.d).minus(r.n.times(d))).divBy(r.d.times(d))

    operator fun times(r: Rational): Rational = n.times(r.n).divBy(r.d.times(d))

    operator fun div(r: Rational): Rational = n.times(r.d).divBy(d.times(r.n))

    operator fun unaryMinus(): Rational = Rational(n.negate(), d)

    override fun compareTo(other: Rational): Int = n.times(other.d).compareTo(other.n.times(d))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Rational

        val thisN = simplify(this)
        val otherN = simplify(other)

        return thisN.n.toDouble().div(thisN.d.toDouble()) == (otherN.n.toDouble().div(otherN.d.toDouble()))
    }

    override fun toString(): String {
        return when {
            d == 1.toBigInteger() || n.rem(d) == 0.toBigInteger() -> n.div(d).toString()
            else -> {
                val r = simplify(this)

                if (r.d < 0.toBigInteger() || (r.n < 0.toBigInteger() && r.d < 0.toBigInteger())) {
                    formatRational(Rational(r.n.negate(), r.d.negate()))
                } else {
                    formatRational(Rational(r.n, r.d))
                }
            }
        }
    }

}

fun formatRational(r: Rational): String = r.n.toString() + "/" + r.d.toString()


infix fun Int.divBy(r2: Int): Rational = Rational(toBigInteger(), r2.toBigInteger())

infix fun Long.divBy(r2: Long): Rational = Rational(toBigInteger(), r2.toBigInteger())

infix fun BigInteger.divBy(r2: BigInteger): Rational = Rational(this, r2)

fun String.toRational(): Rational {
    val number = split("/")

    when {
        number.size == 1 -> return Rational(number[0].toBigInteger(), 1.toBigInteger())
        else -> return Rational(number[0].toBigInteger(), number[1].toBigInteger())
    }
}

fun hcf(n1: BigInteger, n2: BigInteger): BigInteger =
    if (n2 != 0.toBigInteger()) hcf(n2, n1 % n2) else n1

fun simplify(r1: Rational): Rational {
    val hcf = hcf(r1.n, r1.d).abs()
    return Rational(r1.n.div(hcf), r1.d.div(hcf))
}