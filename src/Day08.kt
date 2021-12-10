fun main() {


    /*
    digit   abcdefg num num-unique
    0       xxx xxx 6
    1         x  x  2   x
    2       x xxx x 5
    3       x xx xx 5
    4        xxx x  4   x
    5       xx x xx 5
    6       xx xxxx 6
    7       x x  x  3   x
    8       xxxxxxx 7   x
    9       xxxx xx 6
     */

    fun parseInput(input: List<String>): List<Pair<List<String>, List<String>>> {
        return input.map { line ->
            val (signalPatterns, outputValue) = line.split("|").map { it.trim().split(" ") }
            Pair(signalPatterns, outputValue)
        }
    }

    fun part1(input: List<String>): Int {
        val uniqueSegmentNumbers = setOf(2, 4, 3, 7)
        return parseInput(input).sumOf { line ->
            line.second.count {
                uniqueSegmentNumbers.contains(it.length)
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 26)

    val testOutput2 = part2(testInput)
    println("test output2: $testOutput2")
    //check(testOutput2 == -1)

    val input = readInput("Day08")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
