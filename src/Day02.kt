import kotlin.math.abs

fun main() {
    fun parse(input: List<String>): List<List<Int>> {
        return input.map { it.split(" ").map(String::toInt) }
    }

    fun safe(report: List<Int>): Boolean {
        return report.windowed(2).all { (a, b) -> abs(a - b) in 1..3 }
                && (report.sorted() == report || report.sorted().reversed() == report)
    }

    fun part1(input: List<String>): Int {
        val reports = parse(input)

        return reports.count(::safe)
    }

    fun part2(input: List<String>): Int {
        val reports = parse(input)

        return reports
            .count {
                safe(it) || (0..it.size).any { index ->
                    safe(it.take(index) + it.drop(index + 1))
                }
            }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
