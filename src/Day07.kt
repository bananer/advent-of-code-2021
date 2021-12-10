import kotlin.math.absoluteValue

fun main() {

    fun part1(input: List<String>): Int {
        val numbers = input[0].split(",")
            .map { it.toInt() }
            .sorted()

        // median
        val target = numbers[numbers.size / 2]

        return numbers.sumOf { (it - target).absoluteValue }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day07_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 37)

    val testOutput2 = part2(testInput)
    println("test output2: $testOutput2")
    //check(testOutput2 == -1)

    val input = readInput("Day07")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
