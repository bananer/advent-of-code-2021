fun main() {

    fun countBits(input: Iterable<String>): IntArray {
        val setBitCounts = IntArray(input.first().length)
        for (line in input) {
            line.toCharArray().forEachIndexed { index, c ->
                if (c == '1') {
                    setBitCounts[index]++
                }
            }
        }
        return setBitCounts
    }

    fun part1(input: List<String>): Int {
        val setBitCounts = countBits(input)

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

        val candidatesOxygen = input.toMutableSet()
        val candidatesScrubber = input.toMutableSet()

        for(i in input.indices) {
            if (candidatesOxygen.size > 1) {
                val setBitCounts = countBits(candidatesOxygen)
                val desiredBit = if(setBitCounts[i].toFloat() / candidatesOxygen.size.toFloat() >= 0.5f) '1' else '0'
                candidatesOxygen.retainAll { it[i] == desiredBit }
            }
            if (candidatesScrubber.size > 1) {
                val setBitCounts = countBits(candidatesScrubber)
                val desiredBit = if(setBitCounts[i].toFloat() / candidatesScrubber.size.toFloat() < 0.5f) '1' else '0'
                candidatesScrubber.retainAll { it[i] == desiredBit }
            }
        }

        val oxy = candidatesOxygen.also { check(it.size == 1)}.first().toInt(2)
        val scr = candidatesScrubber.also { check(it.size == 1)}.first().toInt(2)

        return oxy * scr
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val testOutput1 = part1(testInput)
    println("test output: $testOutput1")
    check(testOutput1 == 198)
    val testOutput2 = part2(testInput)
    println("test output: $testOutput2")
    check(testOutput2 == 230)

    val input = readInput("Day03")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
