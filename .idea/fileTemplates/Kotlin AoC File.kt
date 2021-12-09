fun main() {
    
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${AOC_DAY}_test")

    val testOutput1 = part1(testInput)
    println("test output1: ${DS}testOutput1")
    check(testOutput1 == -1)

    val testOutput2 = part2(testInput)
    println("test output2: ${DS}testOutput2")
    //check(testOutput2 == -1)

    val input = readInput("Day${AOC_DAY}")
    println("output part1: ${DS}{part1(input)}")
    println("output part2: ${DS}{part2(input)}")
}
