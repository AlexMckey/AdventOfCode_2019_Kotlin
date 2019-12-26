package Sol17

import java.util.*
import kotlin.math.min

object LRS {
    // return the longest common prefix of s and t
    fun lcp(s: String?, t: String?): String {
        val n = Math.min(s!!.length, t!!.length)
        for (i in 0 until n) {
            if (s[i] != t[i]) return s.substring(0, i)
        }
        return s.substring(0, n)
    }

    // return the longest repeated string in s
    fun lrs(s: String): String { // form the N suffixes
        val n = s.length
        val suffixes = arrayOfNulls<String>(n)
        for (i in 0 until n) {
            suffixes[i] = s.substring(i, n)
        }
        // sort them
        Arrays.sort(suffixes)
        // find longest repeated substring by comparing adjacent sorted suffixes
        var lrs = ""
        for (i in 0 until n - 1) {
            val x = lcp(suffixes[i], suffixes[i + 1])
            if (x.length > lrs.length) lrs = x
        }
        return lrs
    }

    fun <T : Comparable<T>> compareLists(list1: List<T>?, list2: List<T>?): Int {
        for (i in 0 until min(list1!!.size, list2!!.size)) {
            val elem1 = list1[i]
            val elem2 = list2[i]

            if (elem1.javaClass != elem2.javaClass) {
                TODO("Decide what to do when you encounter values of different classes")
            }

            compareValues(elem1, elem2).let {
                if (it != 0) return it
            }
        }
        return compareValues(list1.size, list2.size)
    }

    // return the longest common prefix of s and t
    fun <T : Comparable<T>> lcp(s: List<T>?, t: List<T>?): List<T> {
        val n = min(s!!.size, t!!.size)
        for (i in 0 until n) {
            if (s[i] != t[i]) return s.subList(0, i)
        }
        return s.subList(0, n)
    }

    // return the longest repeated string in s
    fun <T : Comparable<T>> lrs(s: List<T>): List<T> { // form the N suffixes
        val n = s.size
        val suffixes = arrayOfNulls<List<T>>(n)
        for (i in 0 until n) {
            suffixes[i] = s.subList(i, n)
        }
        // sort them
        Arrays.sort(suffixes) { l1, l2 -> compareLists(l1, l2) }
        // find longest repeated substring by comparing adjacent sorted suffixes
        var lrs = emptyList<T>()
        for (i in 0 until n - 1) {
            val x = lcp(suffixes[i], suffixes[i + 1])
            if (x.size > lrs.size) lrs = x
        }
        return lrs
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var s = "MOMOGTGT"
        s = s.replace("\\s+".toRegex(), " ")
        println("'" + lrs(s) + "'")
    }
}