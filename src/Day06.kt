fun main() {
    fun part1(input: List<String>): Long {
        val spacesRegex = "\\s+".toRegex()
        val operations = input.last().split(spacesRegex)
        val numbers = input.subList(0, input.lastIndex).map { line ->
            line.trim().split(spacesRegex).map { it.toLong() }
        }
        val sums = LongArray(numbers.first().size) {
            numbers.first()[it]
        }
        for (i in 0..numbers.first().lastIndex) {
            for (j in 1..numbers.lastIndex) {
                if (operations[i] == "*") {
                    sums[i] *= numbers[j][i]
                } else {
                    sums[i] += numbers[j][i]
                }
            }
        }
        return sums.sum()
    }

    fun part2(input: List<String>): Long {
        val spacesRegex = "\\s+".toRegex()
        val operations = input.last().split(spacesRegex)
        var currentOperation = operations.lastIndex
        val numbers = mutableListOf<String>()
        var sum = 0L
        val maxLineLength = input.maxOf { it.length }
        for (col in maxLineLength - 1 downTo 0) {
            val current = buildString {
                for (row in 0 until input.lastIndex) {
                    append(input.getOrNull(row)?.getOrNull(col) ?: ' ')
                }
            }
            if (current.isBlank() || col == 0) {
                if (col == 0) numbers.add(current)
                sum += numbers.map { it.trim().toLong() }
                    .reduce { acc, lng -> if (operations[currentOperation] == "*") acc * lng else acc + lng }
                numbers.clear()
                currentOperation--
            } else {
                numbers.add(current)
            }
        }
        return sum
    }

    // Or read a large test input from the `src/Day06_test.txt` file:
    val testInput = readInput("Day06_test")
    println(part1(testInput))
    check(part1(testInput) == 4277556L)
    println(part2(testInput))
    check(part2(testInput) == 3263827L)

    // Read the input from the `src/Day06.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}