typealias Rules = Map<Pair<Char, Char>, Char>

fun Rules.get(c1: Char, c2: Char): Char? = get(Pair(c1, c2))

fun main() {

    fun parseInput(input: List<String>): Pair<String, Rules> {
        check(input[1].isEmpty())
        val rules = mutableMapOf<Pair<Char, Char>, Char>()
        input.subList(2, input.size).forEach {
            check(it.substring(2, 6) == " -> ")
            rules[Pair(it[0], it[1])] = it[6]
        }
        return Pair(input[0], rules)
    }

    fun apply(template: String, rules: Rules): String {
        val chars = mutableListOf<Char>()
        var i = 0

        while (i < template.length - 1) {
            chars.add(template[i])
            val insert = rules.get(template[i], template[i+1])
            if (insert != null) {
                chars.add(insert)
            }
            i++
        }

        chars.add(template.last())

        return String(chars.toCharArray())
    }

    fun countChars(str: String): Map<Char, Int> {
        val res = mutableMapOf<Char, Int>()
        for (c in str) {
            res[c] = (res[c] ?: 0) + 1
        }
        return res
    }

    fun part1(input: List<String>): Int {
        val (templ, rules) = parseInput(input)

        var str = templ
        (0 until 10).forEach {
            str = apply(str, rules)
        }

        val charCounts = countChars(str)
        return charCounts.maxOf { it.value } - charCounts.minOf { it.value }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 1588)

    val testOutput2 = part2(testInput)
    println("test output2: $testOutput2")
    //check(testOutput2 == -1)

    val input = readInput("Day14")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
