fun increase(levels: Array<IntArray>, flashes: MutableSet<Pair<Int, Int>>, x: Int, y: Int) {
    if(++levels[x][y] > 9 && !flashes.contains(Pair(x, y))) {
        flash(levels, flashes, x, y)
    }
}

fun flash(levels: Array<IntArray>, flashes: MutableSet<Pair<Int, Int>>, x: Int, y: Int) {
    flashes.add(Pair(x, y))
    val adjacent = listOf(
        Pair(x - 1, y - 1),
        Pair(x - 1, y),
        Pair(x - 1, y + 1),
        Pair(x, y - 1),
        Pair(x, y + 1),
        Pair(x + 1, y - 1),
        Pair(x + 1, y),
        Pair(x + 1, y + 1),
    )
    adjacent.forEach { (ax, ay) ->
        if (levels.indices.contains(ax) && levels[0].indices.contains(ay)) {
            increase(levels, flashes, ax, ay)
        }
    }
}

fun step(levels: Array<IntArray>): Int {
    val flashes = mutableSetOf<Pair<Int, Int>>()

    levels.indices.forEach { x ->
        levels[x].indices.forEach { y ->
            increase(levels, flashes, x, y)
        }
    }

    levels.indices.forEach { x ->
        levels[x].indices.forEach { y ->
            if (levels[x][y] > 9) {
                levels[x][y] = 0
            }
        }
    }

    return flashes.size
}

fun main() {

    fun parseInput(input: List<String>): Array<IntArray> {
        val w = input.first().length
        return input.map { line ->
            line.map { it.digitToInt() }
                .also { check(it.size == w) }
                .toIntArray()
        }.toTypedArray()
    }



    fun part1(input: List<String>): Int {
        val levels = parseInput(input)

        return (0 until 100).sumOf {
            step(levels)
        }
    }

    fun part2(input: List<String>): Int {
        val levels = parseInput(input)
        val count = levels.size * levels[0].size

        return (1 until Int.MAX_VALUE).first {
            step(levels) == count
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 1656)

    val testOutput2 = part2(testInput)
    println("test output2: $testOutput2")
    check(testOutput2 == 195)

    val input = readInput("Day11")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
