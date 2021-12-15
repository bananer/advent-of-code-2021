fun main() {

    class Cave(
        val name: String,
        val connections: MutableSet<Cave> = mutableSetOf()
    ) {

        val isStart
            get() = name == "start"

        val isEnd
            get() = name == "end"

        val isSmall
            get() = !isStart && !isEnd && name.lowercase() == name

        val isLarge
            get() = !isStart && !isEnd && name.uppercase() == name

        override fun equals(other: Any?): Boolean {
            return (this === other)
                    || (other as Cave).name == name
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }

        override fun toString(): String {
            return "Cave($name)"
        }

    }

    fun parseInput(input: List<String>): Map<String, Cave> {
        val result = mutableMapOf<String, Cave>()

        fun cave(name: String): Cave = result.computeIfAbsent(name) {
            Cave(name)
        }

        input.forEach { line ->
            val (nameFrom, nameTo) = line.split("-")
            val from = cave(nameFrom)
            val to = cave(nameTo)
            from.connections.add(to)
            to.connections.add(from)
        }

        return result
    }

    fun paths(head: List<Cave>, mayRevisitSingleSmallCave: Boolean): Set<List<Cave>> {
        val currentCave = head.last()

        if (currentCave.isEnd) {
            return setOf(head)
        }

        return currentCave.connections
            .filter {
                if (it.isLarge || it.isEnd) {
                    // can always enter large cave and end cave
                    true
                }
                else if(it.isStart) {
                    // never return to start cave
                    false
                }
                else if (it.isSmall) {
                    if (head.contains(it)) {
                        // about to visit a small cave a second time
                        if (!mayRevisitSingleSmallCave) {
                            false
                        }
                        else {
                            // only proceed if we have not visited a small cave twice
                            head
                                .filter { c -> c.isSmall }
                                .let { caves ->
                                    caves.toSet().size == caves.size
                                }
                        }
                    }
                    else {
                        // did not visit this small cave yet
                        true
                    }
                }
                else {
                    throw IllegalStateException()
                }

            }
            .flatMap {
                paths(head.plus(it), mayRevisitSingleSmallCave)
            }
            .toSet()
    }

    fun part1(input: List<String>): Int {
        val caves = parseInput(input)
        val paths = paths(listOf(caves["start"]!!), false)
        return paths.size
    }

    fun part2(input: List<String>): Int {
        val caves = parseInput(input)
        val paths = paths(listOf(caves["start"]!!), true)

        return paths.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput0 = readInput("Day12_test0")
    val testInput1 = readInput("Day12_test1")
    val testInput2 = readInput("Day12_test2")

    check(part1(testInput0) == 10)
    check(part1(testInput1) == 19)
    check(part1(testInput2) == 226)

    check(part2(testInput0) == 36)
    check(part2(testInput1) == 103)
    check(part2(testInput2) == 3509)


    val input = readInput("Day12")
    println("output part1: ${part1(input)}")
    println("output part2: ${part2(input)}")
}
