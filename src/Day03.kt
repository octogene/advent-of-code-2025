import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        for (bank in input) {
            val values = bank.map { it.digitToInt() }
            val max = values.max()
            val maxIndex = values.indexOf(max)
            val joltage = if (maxIndex == values.lastIndex) {
                values.subList(0, maxIndex).max() * 10 + max
            } else {
                max * 10 + values.subList(values.indexOf(max) + 1, values.size).max()
            }
            total += joltage
        }
        return total
    }

    fun part2(input: List<String>): Long {
        var total = 0L
        for (bank in input) {
            val digits = bank.map { it.digitToInt() }
            val selected = mutableListOf<Int>()
            var lastIndex = -1

            repeat(12) {
                val remaining = 12 - selected.size
                val maxSearchEnd = digits.size - remaining

                val nextIndex =
                    (lastIndex + 1..maxSearchEnd).maxByOrNull { digits[it] } ?: (lastIndex + 1)
                selected.add(nextIndex)
                lastIndex = nextIndex
            }

            val joltage = selected.map { digits[it] }.joinToString("").toLong()
            println("For $bank -> $joltage")
            total += joltage
        }
        return total
    }


    // Or read a large test input from the `src/Day03_test.txt` file:
    val testInput = readInput("Day03_test")
    println(part1(testInput))
    check(part1(testInput) == 357)
    println(part2(testInput))
    check(part2(testInput) == 3121910778619)

    // Read the input from the `src/Day03_test.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

