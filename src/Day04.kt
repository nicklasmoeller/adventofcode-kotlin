fun List<String>.charAt(location: Pair<Int, Int>, fallback: Char): Char {
    val (x, y) = location

    if (y < 0 || y >= this.size) {
        return fallback
    }

    val line = this[y]

    if (x < 0 || x >= line.length) {
        return fallback
    }

    return line[x]
}

fun main() {
    fun part1(input: List<String>): Int {
        val directions = listOf(
            Pair(1,  1), Pair(0,  1), Pair(-1,  1),
            Pair(1,  0),              Pair(-1,  0),
            Pair(1, -1), Pair(0, -1), Pair(-1, -1)
        )

        return input.mapIndexed() { y, line ->
            line.mapIndexed X@ { x, char ->
                if (char != 'X') {
                    return@X 0
                }

                val origin = Pair(x, y)

                directions.map { dir ->
                    val (dx, dy) = dir
                    val (x, y) = origin

                    (1..3).map {
                        val position = Pair(x + dx * it, y + dy * it)

                        input.charAt(position, '.')
                    }.joinToString("")
                }.count {
                    it == "MAS"
                }
            }.sum()
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.mapIndexed() { y, line ->
            line.mapIndexed() X@ { x, char ->
                if (char != 'A') {
                    return@X 0
                }

                val origin = Pair(x, y)

                val d1 = listOf(
                    Pair(origin.first + 1, origin.second + 1),
                    Pair(origin.first - 1, origin.second - 1)
                )
                val d2 = listOf(
                    Pair(origin.first + 1, origin.second - 1),
                    Pair(origin.first - 1, origin.second + 1)
                )

                if (listOf(
                    d1, d2
                ).map { diagonal ->
                    diagonal.map { position ->
                        input.charAt(position, '.')
                    }.sorted().joinToString("")
                }.all {
                    it == "MS"
                }) 1 else 0
            }.sum()
        }.sum()
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
