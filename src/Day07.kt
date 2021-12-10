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
        val numbers = input[0].split(",")
            .map { it.toInt() }

        val min = numbers.minOrNull()!!
        val max = numbers.maxOrNull()!!

        // actual crab movement cost
        fun gaussSum(n: Int) = (n * (n+1))/2

        // brute force, still efficient enough...
        return (min..max).minOf { target ->
            numbers.sumOf { gaussSum((it - target).absoluteValue) }
        }
    }

    val testInput = readInput("Day07_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 37)

    val testOutput2 = part2(testInput)
    println("test output2: $testOutput2")
    check(testOutput2 == 168)

    val input = readInput("Day07")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
