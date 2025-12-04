fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        var position = 50
        input.forEach { rotation ->
            val distance = rotation.substring(1).toInt()
            position = if (rotation.first() == 'R') {
                (position + distance) % 100
            } else {
                (position - distance) % 100
            }
            if (position == 0) count++
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        var position = 50
        input.forEach { rotation ->
            val direction = if (rotation.first() == 'R') 1 else -1
            val distance = rotation.substring(1).toInt()
            repeat(distance) {
                position = (position + direction) % 100
                if (position == 0) count++
            }
        }
        return count
    }


    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01_test.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
