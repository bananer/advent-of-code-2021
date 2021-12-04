fun main() {
    fun part1(input: List<String>): Int {
        val setBitCounts = IntArray(input[0].length)
        for (line in input) {
            line.toCharArray().forEachIndexed { index, c ->
                if (c == '1') {
                    setBitCounts[index]++
                }
            }
        }

        val gamma = setBitCounts.map {
            if (it > input.size / 2) {
                '1'
            }
            else {
                '0'
            }
        }.joinToString("")
        val epsilon = gamma.map {
            when(it) {
                '1' -> '0'
                '0' -> '1'
                else -> throw IllegalStateException()
            }
        }.joinToString("")

        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val testOutput = part1(testInput)
    println("test output: $testOutput")
    check(testOutput == 198)

    val input = readInput("Day03")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
