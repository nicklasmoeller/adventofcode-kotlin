import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val left = input.map { it.split("   ")[0].toInt() }.sorted()
        val right = input.map { it.split("   ")[1].toInt() }.sorted()

        return left.zip(right).sumOf { (l, r) -> abs(l - r) }
    }

    fun part2(input: List<String>): Int {
        val left = input.map { it.split("   ")[0].toInt() }.sorted()
        val right = input.map { it.split("   ")[1].toInt() }.sorted()

        val rightCounts = right.groupingBy { it }.eachCount()

        return left.sumOf { it * (rightCounts[it] ?: 0) }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
