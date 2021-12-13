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
        val result: Triple<Int, Int, Int> = input
            .fold(Triple(0, 0, 0)) { (position, depth, aim), step ->
                val (direction, countStr) = step.split(" ")
                val count = Integer.parseInt(countStr)
                Triple(
                    when (direction) {
                        "forward" -> position + count
                        else -> position
                    },
                    when (direction) {
                        "forward" -> depth + aim * count
                        else -> depth
                    },
                    when(direction) {
                        "down" -> aim + count
                        "up" -> aim - count
                        else -> aim
                    }
                )
            }
        return result.first * result.second
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val testOutput1 = part1(testInput)
    println("test output 1: $testOutput1")
    check(testOutput1 == 150)
    val testOutput2 = part2(testInput)
    println("test output: $testOutput2")
    check(testOutput2 == 900)

    val input = readInput("Day02")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
