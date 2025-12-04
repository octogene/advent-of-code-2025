fun main() {
    fun part1(input: List<String>): Int {
        return getAccessibleRolls(input).size
    }

    fun part2(input: List<String>): Int {
        var accessibleRolls = 0
        var previouslyAccessibleRolls = emptyList<Pair<Int, Int>>()
        val rolls = input.toMutableList()
        while (true) {
            val accessibleRollsPositions = getAccessibleRolls(rolls)
            accessibleRollsPositions.groupBy { it.first }.forEach { (i, pairs) ->
                val row: String = rolls[i]
                val sb = StringBuilder(row)
                pairs.forEach { (_, col) ->
                    sb[col] = '.'
                }
                rolls[i] = sb.toString()
            }
            if (previouslyAccessibleRolls == accessibleRollsPositions) break
            accessibleRolls += accessibleRollsPositions.size
            previouslyAccessibleRolls = accessibleRollsPositions
        }
        return accessibleRolls
    }

    // Or read a large test input from the `src/Day04_test.txt` file:
    val testInput = readInput("Day04_test")
    println(part1(testInput))
    check(part1(testInput) == 13)
    println(part2(testInput))
    check(part2(testInput) == 43)

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

private fun getAccessibleRolls(input: List<String>): List<Pair<Int, Int>> {
    val accessibleRolls = mutableListOf<Pair<Int, Int>>()
    for (row in input.indices) {
        for (col in input[row].indices) {
            if (input[row][col] != '@') continue
            var adjacentRollsCount = 0
            val positions = getPositions(row, col)
            for (position in positions) {
                val (row, col) = position
                input.getOrNull(row)?.getOrNull(col)?.let {
                    if (it == '@') adjacentRollsCount++
                }
            }
            if (adjacentRollsCount < 4) {
                accessibleRolls.add(Pair(row, col))
            }
        }
    }
    return accessibleRolls
}

private fun getPositions(
    row: Int,
    col: Int
): List<Pair<Int, Int>> {
    val positions = listOf(
        Pair(row - 1, col),
        Pair(row + 1, col),
        Pair(row, col - 1),
        Pair(row, col + 1),
        Pair(row - 1, col - 1),
        Pair(row - 1, col + 1),
        Pair(row + 1, col + 1),
        Pair(row + 1, col - 1)
    )
    return positions
}