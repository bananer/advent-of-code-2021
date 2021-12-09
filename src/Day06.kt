fun main() {

    fun part1(input: List<String>): Int {
        val state: MutableList<Int> = input[0].split(",")
            .map { it.toInt() }
            .toMutableList()

        (0 until 80).forEach { _ ->
            var births = 0
            (0 until state.size).forEach { index ->
                state[index] = if(state[index] == 0) {
                    births++
                    6
                } else {
                    state[index] - 1
                }
            }
            (0 until  births).forEach {
                state.add(8)
            }
        }

        return state.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 5934)

    val testOutput2 = part2(testInput)
    println("test output2: $testOutput2")
    //check(testOutput2 == -1)

    val input = readInput("Day06")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
