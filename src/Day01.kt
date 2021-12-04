fun main() {
    fun part1(input: List<String>): Int {
        return input.map { Integer.parseInt(it) }
            .fold(Pair(Integer.MAX_VALUE, 0)) { (prev, count), next ->
                Pair(
                    next,
                    if (next > prev) {
                        count + 1
                    } else {
                        count
                    }
                )
            }
            .second
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val testOutput = part1(testInput)
    check(testOutput == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
