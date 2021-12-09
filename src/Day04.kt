import java.util.stream.IntStream

fun main() {

    class Field(
        val number: Int,
        var checked: Boolean = false,
    ) {
        override fun toString(): String = (if (checked) "X" else " ") + number
    }

    val boardSize = 5

    class Board(
        val fields: Array<Array<Field>>,
        var win: Boolean = false,
    ) {
        constructor(lines: List<String>) :
                this(lines
                    .also { assert(it.size == boardSize) }
                    .map { line ->
                        line.trim().split(Regex("\\s+"))
                            .also { assert(it.size == boardSize) }
                            .map {
                                Field(Integer.parseInt(it.trim()))
                            }.toTypedArray()
                    }.toTypedArray()
                )

        private fun checkWin() {
            for (x in 0 until boardSize) {
                if (IntStream.range(0, boardSize).allMatch { y ->
                        fields[x][y].checked
                    }) {
                    win = true
                    return
                }

            }
            for (y in 0 until boardSize) {
                if (IntStream.range(0, boardSize).allMatch { x ->
                        fields[x][y].checked
                    }) {
                    win = true
                    return
                }
            }
        }

        fun numberDrawn(number: Int) {
            for (fieldRow in fields) {
                for (field in fieldRow) {
                    if (field.number == number) {
                        field.checked = true
                        checkWin()
                        return
                    }
                }
            }
        }

        fun sumOfUnmarked(): Int {
            return fields.flatten().filter { !it.checked }.sumOf { it.number }
        }

        override fun toString(): String {
            return fields.map { row ->
                row.map { field ->
                    field.toString()
                }.joinToString(" ")
            }.joinToString("\n")
        }
    }

    fun part1(input: List<String>): Int {
        val numbers = input[0].split(",").map { Integer.parseInt(it) }

        var pos = 1
        val boards = mutableListOf<Board>()
        while (pos + boardSize < input.size) {
            boards.add(Board(input.subList(pos + 1, pos + boardSize + 1)))
            pos += boardSize + 1
        }

        //
        for (n in numbers) {
            for (b in boards) {
                b.numberDrawn(n)
                if (b.win) {
                    return b.sumOfUnmarked() * n
                }
            }
        }

        throw IllegalStateException("No board won")
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val testOutput = part1(testInput)
    println("test output: $testOutput")
    check(testOutput == 4512)

    val input = readInput("Day04")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
