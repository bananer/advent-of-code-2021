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
        val numbers = input.map { it.toInt() }

        return (3 until numbers.size).count { i ->
            val prev = numbers.subList(i - 3, i).sum()
            val curr = numbers.subList(i - 2, i + 1).sum()
            curr > prev
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val testOutput1 = part1(testInput)
    check(testOutput1 == 7)
    val testOutput2 = part2(testInput)
    check(testOutput2 == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
