package AOCLib

import kotlin.math.sqrt

data class Point3D(val x: Int, val y: Int, val z: Int) {
    override fun toString(): String = "[x:$x,y:$y,z:$z]"
    operator fun plus(other: Point3D) = Point3D(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Point3D) = Point3D(x - other.x, y - other.y, z - other.z)
    fun compareAxis(other: Point3D) = Point3D(other.x.compareTo(x), other.y.compareTo(y), other.z.compareTo(z))
    fun distance(other: Point3D): Double {
        val delta = other - this
        return sqrt(1.0 * delta.x * delta.x + delta.y * delta.y + delta.z * delta.z)
    }
}