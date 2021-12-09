fun main() {

    fun fishGrowth(input: List<String>, days: Int): Long {
        var state = mutableMapOf<Int, Long>()
        (0..8).forEach { state[it] = 0 }

        input[0].split(",")
            .map { it.toInt() }
            .forEach {
                state.compute(it) { _, count -> count!! + 1 }
            }

        (0 until days).forEach { _ ->
            val newState = mutableMapOf<Int, Long>()
            newState[8] = state[0]!!
            (7 downTo 0).forEach {
                newState[it] = state[it + 1]!!
            }
            newState[6] = newState[6]!! + state[0]!!

            state = newState
        }

        return state.values.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")

    val testOutput1 = fishGrowth(testInput, 80)
    println("test output1: $testOutput1")
    check(testOutput1 == 5934L)

    val testOutput2 = fishGrowth(testInput, 256)
    println("test output2: $testOutput2")
    check(testOutput2 == 26984457539)

    val input = readInput("Day06")
    println("output part1: ${fishGrowth(input, 80)}")
    println("output part2: ${fishGrowth(input, 256)}")
}
