import kotlin.math.abs

fun main() {
    fun scan(input: List<String>, canToggle: Boolean): List<Pair<Int, Int>> {
        var enabled = true
        val pairs = mutableListOf<Pair<Int, Int>>()
        input.map { input ->

            var i = 0
            while (i < input.length) {
                if (canToggle && input.startsWith("do()", i)) {
                    enabled = true
                    i += 4

                    continue
                }

                if (canToggle && input.startsWith("don't()", i)) {
                    enabled = false
                    i += 7

                    continue
                }

                if (!enabled) {
                    i++

                    continue
                }

                if (input.startsWith("mul(", i)) {
                    val start = i + 4
                    var j = start
                    while (input[j] != ')') {
                        j++
                    }

                    val match = input.substring(start, j)

                    if (!"""^\d+,\d+$""".toRegex().matches(match)) {
                        i = start
                        continue
                    }

                    val digits = match.split(",").map(String::toInt)

                    val pair = Pair(digits[0], digits[1])
                    pairs.add(pair)

                    i = j + 1
                    continue
                }

                i++
            }
        }

        return pairs
    }

    fun part1(input: List<String>): Int {
        val pairs = scan(input, false)

        return pairs.sumOf { pair ->
            pair.first * pair.second
        }
    }

    fun part2(input: List<String>): Int {
       val pairs = scan(input, true)

        return pairs.sumOf { pair ->
            pair.first * pair.second
        }
    }

    val testInput1 = listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))".trimIndent())
    check(part1(testInput1) == 161)

    val testInput2 = listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))".trimIndent())
    check(part2(testInput2) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
