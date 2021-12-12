import java.util.*

fun main() {

    fun syntaxErrorScore(c: Char): Int = when (c) {
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


    fun autoCompleteScore(s: String): Long {
        var score = 0L
        s.forEach { c ->
            score *= 5L
            score += when (c) {
                ')' -> 1L
                ']' -> 2L
                '}' -> 3L
                '>' -> 4L
                else -> throw IllegalArgumentException()
            }
        }
        return score
    }


    fun part2(input: List<String>): Long {
        val completions = input.map {
            val stack = Stack<Char>()

            for (c in it) {
                val valid = when (c) {
                    '(', '[', '{', '<' -> {
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
                    return@map ""
                }
            }

            return@map stack.reversed()
                .map { c ->
                    when (c) {
                        '(' -> ')'
                        '[' -> ']'
                        '{' -> '}'
                        '<' -> '>'
                        else -> throw IllegalStateException()
                    }
                }
                .joinToString("")
        }

        val completionsScores = completions
            .filter { it.isNotEmpty() }
            .map { autoCompleteScore(it) }
            .sorted()

        return completionsScores[completionsScores.size / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 26397)

    val testOutput2 = part2(testInput)
    println("test output2: $testOutput2")
    check(testOutput2 == 288957L)

    val input = readInput("Day10")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
