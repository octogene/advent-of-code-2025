fun main() {
    fun part1(input: List<String>): Int {
        val ranges = mutableListOf<LongRange>()
        val ingredients = mutableListOf<Long>()
        var ingredientSection = false
        for (line in input) {
            if (line.isBlank()) {
                ingredientSection = true
                continue
            }
            if (ingredientSection) {
                ingredients.add(line.toLong())
            } else {
                line.split("-").let { (start, end) ->
                    ranges.add(start.toLong()..end.toLong())
                }
            }
        }
        return ingredients.count { ingredient -> ranges.any { ingredient in it } }
    }

    fun part2(input: List<String>): Long {
        var count = 0L
        val ranges = mutableListOf<Pair<Long, Long>>()
        for (line in input) {
            if (line.isBlank()) break
            val range = line.split('-')
            ranges.add(range[0].toLong() to range[1].toLong())
        }
        ranges.sortBy { it.first }

        var lastEnd = Long.MIN_VALUE
        ranges.forEach { (start, end) ->
            if (start > lastEnd) {
                count += (end - start) + 1
            } else if (end > lastEnd) {
                count += end - lastEnd
            }
            lastEnd = maxOf(lastEnd, end)
        }
        return count
    }

    // Or read a large test input from the `src/Day05_test.txt` file:
    val testInput = readInput("Day05_test")
    println(part1(testInput))
    check(part1(testInput) == 3)
    println(part2(testInput))
    check(part2(testInput) == 14L)

    // Read the input from the `src/Day05.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}