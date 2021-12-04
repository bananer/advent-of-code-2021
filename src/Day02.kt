fun main() {
    fun part1(input: List<String>): Int {
        val result: Pair<Int, Int> = input
            .fold(Pair(0, 0)) { (position, depth), step ->
                val (direction, countStr) = step.split(" ")
                val count = Integer.parseInt(countStr)
                val pair = Pair(
                    when (direction) {
                        "forward" -> position + count
                        else -> position
                    },
                    when (direction) {
                        "up" -> depth - count
                        "down" -> depth + count
                        else -> depth
                    }
                )
                pair
            }
        return result.first * result.second
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val testOutput = part1(testInput)
    println("test output: $testOutput")
    check(testOutput == 150)

    val input = readInput("Day02")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
