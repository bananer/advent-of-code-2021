typealias Heightmap = Array<Array<Int>>

fun Heightmap.get(x: Int, y: Int) = this[y][x]

val Heightmap.width: Int
    get() = this[0].size

val Heightmap.height: Int
    get() = this.size

fun Heightmap.getAdjacentCoordinates(x: Int, y: Int): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()
    if (x > 0) {
        result.add(Pair(x - 1, y))
    }
    if (x < this.width - 1) {
        result.add(Pair(x + 1, y))
    }
    if (y > 0) {
        result.add(Pair(x, y - 1))
    }
    if (y < this.height - 1) {
        result.add(Pair(x, y + 1))
    }
    return result
}

fun Heightmap.getAdjacentHeights(x: Int, y: Int) = this.getAdjacentCoordinates(x, y)
    .map { (x, y) -> this.get(x, y) }

fun Heightmap.isLowPoint(x: Int, y: Int) = this.getAdjacentHeights(x, y).all {
    it > this.get(x, y)
}

fun Heightmap.coordinates(): Iterable<Pair<Int, Int>> {
    return Iterable {
        object : Iterator<Pair<Int, Int>> {

            private var x = -1
            private var y = 0

            override fun hasNext(): Boolean {
                return y < height -1 || x < width -1
            }

            override fun next(): Pair<Int, Int> {
                if (x < width - 1) {
                    x++
                } else {
                    x = 0
                    y++
                }
                return Pair(x, y)
            }

        }
    }
}

fun Heightmap.lowPoints(): Iterable<Pair<Int, Int>> = coordinates().filter { (x, y) -> this.isLowPoint(x, y) }

fun Heightmap.riskLevel(x: Int, y: Int) = this.get(x, y) + 1

fun Heightmap.lowPointsRiskLevelSum() = lowPoints().sumOf { (x, y) -> this.riskLevel(x, y) }


fun main() {

    fun parseInput(input: List<String>): Heightmap = input
        .map { line ->
            line.chunked(1).map { it.toInt() }.toTypedArray()
        }
        .also { lines ->
            // verify same size of all lines
            lines.forEach { check(it.size == lines[0].size) }
        }
        .toTypedArray()

    fun part1(input: List<String>) = parseInput(input).lowPointsRiskLevelSum()


    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 15)

    val testOutput2 = part2(testInput)
    println("test output2: $testOutput2")
    //check(testOutput2 == -1)

    val input = readInput("Day09")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
