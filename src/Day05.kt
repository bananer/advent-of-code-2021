fun main() {

    data class Point(val x: Int, val y: Int)
    data class Line(val start: Point, val end: Point)

    val lineRegex = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")

    infix fun Int.towards(b: Int): IntProgression {
        return if (this < b) {
            this .. b
        } else {
            b .. this
        }
    }

    fun parseInput(input: List<String>): List<Line> {
        return input.map {
            val matches = lineRegex.matchEntire(it)
                ?: throw IllegalArgumentException("Could not parse: '$it'")
            val (x1, y1, x2, y2) = matches.groupValues.drop(1).map { g -> g.toInt() }

            Line(Point(x1, y1), Point(x2, y2))
        }
    }

    fun part1(input: List<String>): Int {
        val lines = parseInput(input)
            // For now, only consider horizontal and vertical lines
            .filter { it.start.x == it.end.x || it.start.y == it.end.y }

        val map = mutableMapOf<Point, Int>()

        for (line in lines) {
            for (x in (line.start.x towards line.end.x)) {
                for (y in (line.start.y towards line.end.y)) {
                    // map[x,y]++
                    map.compute(Point(x, y)) { _, v -> (v ?: 0) + 1 }
                }
            }
        }

        return map.values.count { it >= 2 }
    }

    fun part2(input: List<String>): Int {
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 5)

    //val testOutput2 = part2(testInput)
    //println("test output2: $testOutput2")
    //check(testOutput2 == -1)

    val input = readInput("Day05")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
