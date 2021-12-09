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
                if ((0 until boardSize).all { y ->
                        fields[x][y].checked
                    }) {
                    win = true
                    return
                }

            }
            for (y in 0 until boardSize) {
                if ((0 until boardSize).all { x ->
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

    fun parseInput(input: List<String>): Pair<List<Int>, List<Board>> {
        val numbers = input[0].split(",").map { Integer.parseInt(it) }

        var pos = 1
        val boards = mutableListOf<Board>()
        while (pos + boardSize < input.size) {
            boards.add(Board(input.subList(pos + 1, pos + boardSize + 1)))
            pos += boardSize + 1
        }

        return Pair(numbers, boards)
    }

    fun part1(input: List<String>): Int {
        val (numbers, boards) = parseInput(input)

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
        val (numbers, boards) = parseInput(input)

        val boardsLeft = boards.toMutableList()

        for (n in numbers) {
            for (b in boardsLeft.toList()) {
                b.numberDrawn(n)
                if (b.win) {
                    if (boardsLeft.size == 1) {
                        return b.sumOfUnmarked() * n
                    }
                    boardsLeft.remove(b)
                }
            }
        }

        throw IllegalStateException("No board won")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val testOutput1 = part1(testInput)
    check(testOutput1 == 4512)
    println("test output1: $testOutput1")
    val testOutput2 = part2(testInput)
    println("test output2: $testOutput2")
    check(testOutput2 == 1924)

    val input = readInput("Day04")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
