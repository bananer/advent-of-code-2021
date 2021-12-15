data class Dot(
    val x: Int,
    val y: Int,
)

enum class FoldAxis { X, Y }

data class FoldInstruction(
    val axis: FoldAxis,
    val coordinate: Int,
)

fun main() {

    fun parseInput(input: List<String>): Pair<Set<Dot>, List<FoldInstruction>> {
        val dots = mutableSetOf<Dot>()
        val foldInstructions = mutableListOf<FoldInstruction>()

        var i = 0

        while (i < input.size && input[i].isNotEmpty()) {
            val (x, y) = input[i].split(",")
            dots.add(Dot(x.toInt(), y.toInt()))
            i++
        }

        // empty line
        i++

        while (i < input.size) {
            val l = input[i]
            check(l.startsWith("fold along "))
            val axis = FoldAxis.valueOf(l[11].toString().uppercase())
            val coordinate = l.substring(13).toInt()

            foldInstructions.add(FoldInstruction(axis, coordinate))
            i++
        }

        return Pair(dots, foldInstructions)
    }

    fun fold(dots: Set<Dot>, instruction: FoldInstruction): Set<Dot> {
        return dots.map {
            when (instruction.axis) {
                FoldAxis.Y -> {
                    if (it.y > instruction.coordinate) {
                        val dy = (it.y - instruction.coordinate)
                        Dot(it.x, it.y - 2 * dy)
                    } else {
                        it
                    }
                }
                FoldAxis.X -> {
                    if (it.x > instruction.coordinate) {
                        val dx = (it.x - instruction.coordinate)
                        Dot(it.x - 2 * dx, it.y)
                    } else {
                        it
                    }
                }
            }
        }.toSet()
    }

    fun part1(input: List<String>): Int {
        val (dots, foldInstructions) = parseInput(input)

        return fold(dots, foldInstructions.first()).size
    }

    fun part2(input: List<String>): String {
        val (startDots, foldInstructions) = parseInput(input)

        var dots = startDots
        foldInstructions.forEach {
            dots = fold(dots, it)
        }

        // print it
        val h = dots.maxOf { it.y } + 1
        val w = dots.maxOf { it.x } + 1

        return (0 until h).map { y ->
            (0 until w).map { x ->
                if (dots.contains(Dot(x, y))) {
                    '#'
                } else {
                    '.'
                }
            }.joinToString("")
        }.joinToString("\n")

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")

    val testOutput1 = part1(testInput)
    println("test output1: $testOutput1")
    check(testOutput1 == 17)

    val testOutput2 = part2(testInput)
    println("test output2:\n$testOutput2")
    check(
        testOutput2 ==
                "#####\n" +
                "#...#\n" +
                "#...#\n" +
                "#...#\n" +
                "#####"
    )

    val input = readInput("Day13")
    println("output part1: ${part1(input)}")
    println("output part2:\n${part2(input)}")
}
