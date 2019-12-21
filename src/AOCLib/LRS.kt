package AOCLib

import java.util.*
import kotlin.math.min

object LRS {
    // return the longest common prefix of s and t
    fun lcp(s: String?, t: String?): String {
        val n = min(s!!.length, t!!.length)
        for (i in 0 until n) {
            if (s[i] != t[i]) return s.substring(0, i)
        }
        return s.substring(0, n)
    }

    // return the longest repeated string in s
    fun lrs(s: String): String { // form the N suffixes
        val N = s.length
        val suffixes = arrayOfNulls<String>(N)
        for (i in 0 until N) {
            suffixes[i] = s.substring(i, N)
        }
        // sort them
        Arrays.sort(suffixes)
        // find longest repeated substring by comparing adjacent sorted suffixes
        var lrs = ""
        for (i in 0 until N - 1) {
            val x = lcp(suffixes[i], suffixes[i + 1])
            if (x.length > lrs.length) lrs = x
        }
        return lrs
    }

    // return the longest common prefix of s and t
    fun <T> lcp(s: List<T>?, t: List<T>?): List<T> {
        val n = min(s!!.size, t!!.size)
        for (i in 0 until n) {
            if (s[i] != t[i]) return s.subList(0, i)
        }
        return s.subList(0, n)
    }

    // return the longest repeated string in s
    fun <T : Comparable<*>> lrs(s: List<T>): List<T> { // form the N suffixes
        val N = s.size
        val suffixes = arrayOfNulls<List<T>>(N)
        for (i in 0 until N) {
            suffixes[i] = s.subList(i, N)
        }
        // sort them
        Arrays.sort(suffixes) { o1: List<T>?, o2: List<T>? ->
            if (o1 == null && o2 == null) 0
            else if (o1 == null) 1
            else if (o2 == null) -1
            else if (o1.size > o2.size) -1
            else if (o1.size < o2.size) 1
            else {
                var res = 0
                for (i in o1.indices) {
                    res = compareValues(o1[i], o2[i])
                    if (res < 0) break
                    else if (res > 0) break
                }
                res
            }
        }
        // find longest repeated substring by comparing adjacent sorted suffixes
        var lrs = emptyList<T>()
        for (i in 0 until N - 1) {
            val x = lcp(suffixes[i], suffixes[i + 1])
            if (x.size > lrs.size) lrs = x
        }
        return lrs
    }

    // read in text, replacing all consecutive whitespace with a single space
// then compute longest repeated substring
    @JvmStatic
    fun main(args: Array<String>) {
        var s: String? = readLine()
        s = s!!.replace("\\s+".toRegex(), " ")
        println("'" + lrs(s) + "'")
    }
}