import java.util.*

fun main() {

    fun syntaxErrorScore(c: Char): Int = when(c) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> throw IllegalArgumentException()
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {
            val stack = Stack<Char>()

            for(c in it) {
                val valid = when(c) {
                    '(','[','{','<' -> {
                        stack.push(c)
                        true
                    }
                    ')' -> stack.pop() == '('
                    ']' -> stack.pop() == '['
                    '}' -> stack.pop() == '{'
                    '>' -> stack.pop() == '<'
                    else -> throw IllegalArgumentException()
                }
                if (!valid) {
                    return@sumOf syntaxErrorScore(c)
                }
            }

            // no syntax error
            return@sumOf 0
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 26397)

    val testOutput2 = part2(testInput)
    println("test output2: $testOutput2")
    //check(testOutput2 == -1)

    val input = readInput("Day10")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
