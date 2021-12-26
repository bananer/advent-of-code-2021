data class Point3d(val x: Int, val y: Int, val z: Int)

data class Cuboid(val p1: Point3d, val p2: Point3d) {
    val points: Sequence<Point3d> get() = generateSequence (p1) {
        if (it.x < p2.x) {
            Point3d(it.x + 1, it.y, it.z)
        }
        else if(it.y < p2.y) {
            Point3d(p1.x, it.y + 1, it.z)
        }
        else if(it.z < p2.z) {
            Point3d(p1.x, p1.y, it.z + 1)
        }
        else {
            null
        }
    }
}

data class RebootStep(val on: Boolean, val cuboid: Cuboid)

val inputRegex = Regex("(on|off) x=(-?\\d+)\\.\\.(-?\\d+),y=(-?\\d+)\\.\\.(-?\\d+),z=(-?\\d+)\\.\\.(-?\\d+)")

fun main() {

    fun parseInput(input: List<String>): List<RebootStep> = input
        .map { line ->
            val v = inputRegex.matchEntire(line)!!.groupValues.drop(1)
            RebootStep(
                v[0] == "on",
                Cuboid(
                    Point3d(v[1].toInt(), v[3].toInt(), v[5].toInt()),
                    Point3d(v[2].toInt(), v[4].toInt(), v[6].toInt()),
                )
            )
        }

    fun relevantForPart1(c: Cuboid): Boolean =
        c.p1.x.coerceAtMost(c.p2.x) >= -50 &&
        c.p1.y.coerceAtMost(c.p2.y) >= -50 &&
        c.p1.z.coerceAtMost(c.p2.z) >= -50 &&
        c.p1.x.coerceAtLeast(c.p2.x) <= 50 &&
        c.p1.y.coerceAtLeast(c.p2.y) <= 50 &&
        c.p1.z.coerceAtLeast(c.p2.z) <= 50

    fun part1(input: List<String>): Int {
        val region = mutableSetOf<Point3d>()

        parseInput(input).filter { relevantForPart1(it.cuboid) }
            .forEach {
                if (it.on) {
                    region.addAll(it.cuboid.points)
                }
                else {
                    region.removeAll(it.cuboid.points)
                }
            }

        return region.size
    }

    fun part2(input: List<String>): Long {
        return input.size.toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day22_test1")
    val testInput2 = readInput("Day22_test2")

    val testOutput1 = part1(testInput1)
    println("test output1: $testOutput1")
    check(testOutput1 == 590784)

    val testOutput2 = part2(testInput2)
    println("test output2: $testOutput2")
    //check(testOutput2 == 2758514936282235L)

    val input = readInput("Day22")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
