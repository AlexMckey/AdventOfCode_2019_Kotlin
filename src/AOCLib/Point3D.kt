package AOCLib

import kotlin.math.sqrt

data class Point3D(val x: Int, val y: Int, val z: Int) {
    override fun toString(): String = "[x:$x,y:$y,z:$z]"
    operator fun plus(other: Point3D) = Point3D(other.x + x, other.y + y, other.z + z)
    operator fun minus(other: Point3D) = Point3D(other.x - x, other.y - y, other.z - z)
    fun distance(other: Point3D): Double {
        val delta = other - this
        return sqrt(1.0 * delta.x * delta.x + delta.y * delta.y + delta.z * delta.z)
    }
}